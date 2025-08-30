package br.com.bruno.tabelafipe.app;

import br.com.bruno.tabelafipe.model.ListaDeVeiculos;
import br.com.bruno.tabelafipe.model.Marca;
import br.com.bruno.tabelafipe.model.Veiculo;
import br.com.bruno.tabelafipe.repository.MarcaRepository;
import br.com.bruno.tabelafipe.repository.VeiculoRepository;
import br.com.bruno.tabelafipe.service.HTTPResolver;
import br.com.bruno.tabelafipe.service.MapearParaRecords;
import br.com.bruno.tabelafipe.service.dto.Dados;
import br.com.bruno.tabelafipe.service.dto.EspecificaoFinal;
import br.com.bruno.tabelafipe.service.dto.Modelos;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class App {

    private final VeiculoRepository veiculoRepository;
    private final MarcaRepository marcaRepository;

    private final String URL = "https://parallelum.com.br/fipe/api/v1";
    private final HTTPResolver httpResolver;
    private final MapearParaRecords mapping;

    public void startApp(String buscaMarca, String buscaModelo) {

        ListaDeVeiculos veiculos = new ListaDeVeiculos();

        String listaDeMarcas = httpResolver.getApiResponse(URL + "/carros/marcas");
        List<Dados> marcas = mapping.mapearListaParaRecords(listaDeMarcas, Dados.class);

        marcas.stream().filter(marca -> marca.name().toUpperCase().contains(buscaMarca.toUpperCase())).forEach(marca -> {

            Marca minhaMarca = new Marca(marca.name());
            marcaRepository.save(minhaMarca);

            String listaDeModelos = httpResolver.getApiResponse(URL + "/carros/marcas/" + marca.id() + "/modelos");
            Modelos modelos = mapping.mapearParaRecords(listaDeModelos, Modelos.class);

            modelos.modelos().stream().filter(m -> m.name().toUpperCase().contains(buscaModelo.toUpperCase())).forEach( modelo -> {

                String listaDeAnosDoModelo = httpResolver.getApiResponse(URL + "/carros/marcas/" + marca.id() + "/modelos/" + modelo.id() + "/anos");
                List<Dados> anosDoModelo = mapping.mapearListaParaRecords(listaDeAnosDoModelo, Dados.class);

                anosDoModelo.forEach( ano -> {

                    String especificacoesFinais = httpResolver.getApiResponse(URL + "/carros/marcas/" + marca.id() + "/modelos/" + modelo.id() + "/anos/" + ano.id());
                    EspecificaoFinal especificaoFinal = mapping.mapearParaRecords(especificacoesFinais, EspecificaoFinal.class);

                    Veiculo veiculo = new Veiculo("Carro", especificaoFinal, minhaMarca);
                    minhaMarca.addVeiculo(veiculo);
                    veiculoRepository.save(veiculo);

                    veiculos.addVeiculo(veiculo);
                });
            });
        });
    }

    public void getEstatisticasDb() {

        System.out.println("\nMais baratos: ");
        veiculoRepository.findTop5ByOrderByValor().forEach(System.out::println);

        System.out.println("\nMais caros: ");
        veiculoRepository.getMaisCaros(7).forEach(System.out::println);

        System.out.println("\nMais antigos: ");
        veiculoRepository.findTop5ByOrderByAno().forEach(System.out::println);

        System.out.println("\nMais novos: ");
        veiculoRepository.findTop5ByOrderByAnoDesc().forEach(System.out::println);

        System.out.println("\nMeu método genérico: ");
        veiculoRepository.meuMetodoGenerico().forEach(System.out::println);

//        System.out.println("\nMais baratos por ano: ");
//        veiculos.getMaisBaratosPorAno();
//
//        System.out.println("\nTodos: ");
//        veiculos.getTodos();
//
//        System.out.println("\nPrincipais estatísticas: ");
//        veiculos.getSummaryStatistics();
    }
}
