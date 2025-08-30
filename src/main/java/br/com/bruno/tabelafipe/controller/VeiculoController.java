package br.com.bruno.tabelafipe.controller;

import br.com.bruno.tabelafipe.model.Veiculo;
import br.com.bruno.tabelafipe.repository.VeiculoRepository;
import br.com.bruno.tabelafipe.service.VeiculoService;
import br.com.bruno.tabelafipe.service.dto.VeiculoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    private final VeiculoService veiculoService;

    @GetMapping
    public ResponseEntity<List<VeiculoResponseDTO>> getTodosOsVeiculos() {
        System.out.println("Ping Get");
        return ResponseEntity.ok(veiculoService.getAllVeiculos());
    }

    @GetMapping("/teste")
    public String teste() {
        return "Teste concluido com sucesso";
    }
}
