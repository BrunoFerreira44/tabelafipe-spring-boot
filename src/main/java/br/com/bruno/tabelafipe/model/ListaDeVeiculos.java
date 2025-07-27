package br.com.bruno.tabelafipe.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ListaDeVeiculos {

    private List<Veiculo> listaDeVeiculos = new ArrayList<>();

    public void addVeiculo (Veiculo veiculo) {
        this.listaDeVeiculos.add(veiculo);
    }

    public void getMaisBaratos() {

        this.listaDeVeiculos.stream()
                .sorted(Comparator.comparing(Veiculo::getValor))
                .limit(10)
                .forEach(System.out::println);
    }

    public void getMaisCaros() {

        this.listaDeVeiculos.stream()
                .sorted(Comparator.comparing(Veiculo::getValor).reversed())
                .limit(10)
                .forEach(System.out::println);
    }

    public void getMaisAntigos() {

        this.listaDeVeiculos.stream()
                .filter(veiculo -> veiculo.getAno() < 2100)
                .sorted(Comparator.comparing(Veiculo::getAno))
                .limit(10)
                .forEach(System.out::println);
    }

    public void getMaisNovos() {

        this.listaDeVeiculos.stream()
                .filter(veiculo -> veiculo.getAno() < 2100)
                .sorted(Comparator.comparing(Veiculo::getAno).reversed())
                .limit(10)
                .forEach(System.out::println);
    }

    public void getMaisBaratosPorAno() {

        Map<Integer, List<Veiculo>> mapMaisBaratosPorAno = this.listaDeVeiculos.stream()
                .collect(Collectors.groupingBy(Veiculo::getAno, Collectors.collectingAndThen(
                        Collectors.toList(),
                        lista -> lista.stream()
                                .sorted(Comparator.comparing(Veiculo::getValor))
                                .limit(3)
                                .toList())));

        mapMaisBaratosPorAno.forEach( (a, b) -> {
            System.out.println(a + " --> " + b);
        });
    }

    public void getTodos() {

        this.listaDeVeiculos.stream()
                .sorted(Comparator.comparing(Veiculo::getAno))
                .forEach(System.out::println);
    }

}
