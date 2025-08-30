package br.com.bruno.tabelafipe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table
@NoArgsConstructor
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @OneToMany(mappedBy = "marca")
    private List<Veiculo> veiculos = new ArrayList<>();

    public Marca(String nome) {
        this.nome = nome;
    }

    public void addVeiculo(Veiculo veiculo) {
        this.veiculos.add(veiculo);
    }

    @Override
    public String toString() {
        return "Marca {" +
                "id = " + id +
                ", nome = '" + nome + '\'' +
                ", veiculos = " + veiculos +
                '}';
    }
}
