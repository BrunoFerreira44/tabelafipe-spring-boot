package br.com.bruno.tabelafipe.app;

import br.com.bruno.tabelafipe.model.ListaDeVeiculos;
import br.com.bruno.tabelafipe.model.Veiculo;
import br.com.bruno.tabelafipe.service.HTTPResolver;
import br.com.bruno.tabelafipe.service.MapearParaRecords;
import br.com.bruno.tabelafipe.service.dto.Dados;
import br.com.bruno.tabelafipe.service.dto.EspecificaoFinal;
import br.com.bruno.tabelafipe.service.dto.Modelos;
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

    private final String URL = "https://parallelum.com.br/fipe/api/v1";
    private final HTTPResolver httpResolver;
    private final MapearParaRecords mapping;

    public void startApp(String buscaMarca, String buscaModelo) {

        ListaDeVeiculos veiculos = new ListaDeVeiculos();

        String listaDeMarcas = httpResolver.getApiResponse(URL + "/carros/marcas");
        List<Dados> marcas = mapping.mapearListaParaRecords(listaDeMarcas, Dados.class);

        marcas.stream().filter(marca -> marca.name().toUpperCase().contains(buscaMarca.toUpperCase())).forEach(marca -> {

            String listaDeModelos = httpResolver.getApiResponse(URL + "/carros/marcas/" + marca.id() + "/modelos");
            Modelos modelos = mapping.mapearParaRecords(listaDeModelos, Modelos.class);

            modelos.modelos().stream().filter(m -> m.name().toUpperCase().contains(buscaModelo.toUpperCase())).forEach( modelo -> {

                String listaDeAnosDoModelo = httpResolver.getApiResponse(URL + "/carros/marcas/" + marca.id() + "/modelos/" + modelo.id() + "/anos");
                List<Dados> anosDoModelo = mapping.mapearListaParaRecords(listaDeAnosDoModelo, Dados.class);

                anosDoModelo.forEach( ano -> {

                    String especificacoesFinais = httpResolver.getApiResponse(URL + "/carros/marcas/" + marca.id() + "/modelos/" + modelo.id() + "/anos/" + ano.id());
                    EspecificaoFinal fichaTecnica = mapping.mapearParaRecords(especificacoesFinais, EspecificaoFinal.class);

                    veiculos.addVeiculo(new Veiculo("Carro", fichaTecnica.marca(), fichaTecnica.modelo(), fichaTecnica.ano(), fichaTecnica.valor(), fichaTecnica.combustivel()));
                });
            });
        });

        System.out.println("\nMais baratos: ");
        veiculos.getMaisBaratos();

        System.out.println("\nMais caros: ");
        veiculos.getMaisCaros();

        System.out.println("\nMais antigos: ");
        veiculos.getMaisAntigos();

        System.out.println("\nMais novos: ");
        veiculos.getMaisNovos();

        System.out.println("\nMais baratos por ano: ");
        veiculos.getMaisBaratosPorAno();

        System.out.println("\nTodos: ");
        veiculos.getTodos();

        System.out.println("\nPrincipais estatísticas: ");
        veiculos.getSummaryStatistics();

    }
}
