package br.com.bruno.tabelafipe.service;

import br.com.bruno.tabelafipe.model.Veiculo;
import br.com.bruno.tabelafipe.repository.VeiculoRepository;
import br.com.bruno.tabelafipe.service.dto.VeiculoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public List<VeiculoResponseDTO> getAllVeiculos() {

        return veiculoRepository.findAll()
                .stream()
                .map(veiculo -> new VeiculoResponseDTO(veiculo.getTipo(), veiculo.getMarca().getNome(), veiculo.getModelo(), veiculo.getAno(), veiculo.getValor(), veiculo.getCombustivel()))
                .toList();

    }
}
