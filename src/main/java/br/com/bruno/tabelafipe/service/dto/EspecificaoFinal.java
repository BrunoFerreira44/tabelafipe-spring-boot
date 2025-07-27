package br.com.bruno.tabelafipe.service.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EspecificaoFinal(@JsonAlias("Valor") String valor,
                               @JsonAlias("Marca") String marca,
                               @JsonAlias("Modelo") String modelo,
                               @JsonAlias("AnoModelo") String ano,
                               @JsonAlias("Combustivel") String combustivel) {
}
