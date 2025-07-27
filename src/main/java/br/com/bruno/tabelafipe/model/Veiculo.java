package br.com.bruno.tabelafipe.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
public class Veiculo {

    private String tipo;
    private String marca;
    private String modelo;
    private Integer ano;
    private BigDecimal valor;
    private String combustivel;

    public Veiculo(String tipo, String marca, String modelo, String ano, String valor, String combustivel) {
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = this.convertToInteger(ano);
        this.valor = this.convertToBigDecimal(valor);
        this.combustivel = combustivel;
    }

    private Integer convertToInteger(String ano) {
        return Integer.parseInt(ano);
    }

    private BigDecimal convertToBigDecimal(String valor) {

        try {
            String valorFormatado = valor.replace("R$", "").trim();
            valorFormatado = valorFormatado.replace(".", "");
            valorFormatado = valorFormatado.replace(",", ".");
            return new BigDecimal(valorFormatado);
        } catch (NumberFormatException  e) {
            throw new IllegalArgumentException("Formato de valor inválido: " + valor, e);
        }

    }

    @Override
    public String toString() {
        return "Veiculo {" +
                "modelo = " + this.modelo +
                ", ano = " + this.ano +
                ", valor = R$" + this.valor +
                "}";
    }
}
