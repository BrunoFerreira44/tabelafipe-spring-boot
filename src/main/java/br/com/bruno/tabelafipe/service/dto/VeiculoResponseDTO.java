package br.com.bruno.tabelafipe.service.dto;

import br.com.bruno.tabelafipe.model.Marca;
import jakarta.persistence.*;

import java.math.BigDecimal;

public record VeiculoResponseDTO(String tipo,
                                 String marca,
                                 String modelo,
                                 Integer ano,
                                 BigDecimal valor,
                                 String combustivel) {
}
