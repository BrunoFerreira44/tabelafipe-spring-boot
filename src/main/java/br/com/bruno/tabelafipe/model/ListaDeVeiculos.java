package br.com.bruno.tabelafipe.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ListaDeVeiculos {

    private List<Veiculo> listaDeVeiculos = new ArrayList<>();
    Integer limitSize = 5;

    public void addVeiculo (Veiculo veiculo) {
        this.listaDeVeiculos.add(veiculo);
    }

    public void getMaisBaratos() {

        this.listaDeVeiculos.stream()
                .sorted(Comparator.comparing(Veiculo::getValor))
                .limit(limitSize)
                .forEach(System.out::println);
    }

    public void getMaisCaros() {

        this.listaDeVeiculos.stream()
                .sorted(Comparator.comparing(Veiculo::getValor).reversed())
                .limit(limitSize)
                .forEach(System.out::println);
    }

    public void getMaisAntigos() {

        this.listaDeVeiculos.stream()
                .filter(veiculo -> veiculo.getAno() < 2100)
                .sorted(Comparator.comparing(Veiculo::getAno))
                .limit(limitSize)
                .forEach(System.out::println);
    }

    public void getMaisNovos() {

        this.listaDeVeiculos.stream()
                .filter(veiculo -> veiculo.getAno() < 2100)
                .sorted(Comparator.comparing(Veiculo::getAno).reversed())
                .limit(limitSize)
                .forEach(System.out::println);
    }

    public void getMaisBaratosPorAno() {

        Map<Integer, Veiculo> mapMaisBaratosPorAno = this.listaDeVeiculos.stream()
                .collect(Collectors.groupingBy(
                        Veiculo::getAno,
                        Collectors.collectingAndThen(
                                Collectors.minBy(Comparator.comparing(Veiculo::getValor)), Optional::get)));

        mapMaisBaratosPorAno.forEach((ano, info) -> {
            System.out.println(ano + " --> " + info);
        });
    }

    public void getSummaryStatistics () {
        DoubleSummaryStatistics estatisticas = this.listaDeVeiculos.stream()
                .mapToDouble(value -> value.getValor().doubleValue())
                .summaryStatistics();

        System.out.println("Quantidade total de modelos analisados: " + estatisticas.getCount());
        System.out.println("Média de preços: R$" + estatisticas.getAverage());
        System.out.println("O mais caro: R$" + estatisticas.getMax());
        System.out.println("O mais barato: R$" + estatisticas.getMin());
    }

    public void getTodos() {

        this.listaDeVeiculos.stream()
                .sorted(Comparator.comparing(Veiculo::getAno))
                .forEach(System.out::println);
    }

}
