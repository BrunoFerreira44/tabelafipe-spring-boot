package br.com.bruno.tabelafipe.model;

import br.com.bruno.tabelafipe.service.dto.EspecificaoFinal;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String tipo;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "cod_marca")
    private Marca marca;
    private String modelo;
    private Integer ano;
    private BigDecimal valor;
    private String combustivel;

    public Veiculo(String tipo, EspecificaoFinal especificaoFinal, Marca marca) {
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = especificaoFinal.modelo();
        this.ano = this.convertToInteger(especificaoFinal.ano());
        this.valor = this.convertToBigDecimal(especificaoFinal.valor());
        this.combustivel = especificaoFinal.combustivel();
    }

    private Integer convertToInteger(String ano) {
        try {
            return Integer.parseInt(ano);
        } catch (NumberFormatException e) {
            return Integer.valueOf("0");
        }
    }

    private BigDecimal convertToBigDecimal(String valor) {

        if (valor == null) return new BigDecimal("0");

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
