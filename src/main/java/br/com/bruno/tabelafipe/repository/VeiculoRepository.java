package br.com.bruno.tabelafipe.repository;

import br.com.bruno.tabelafipe.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    List<Veiculo> findByModeloContainingIgnoreCase(String modelo);
    List<Veiculo> findByModeloContainingIgnoreCaseAndValorLessThan(String modelo, Double valor);

    List<Veiculo> findTop7ByOrderByAnoDesc();

    List<Veiculo> findTop5ByOrderByValor();
    List<Veiculo> findTop5ByOrderByValorDesc();
    List<Veiculo> findTop5ByOrderByAno();
    List<Veiculo> findTop5ByOrderByAnoDesc();

    @Query("SELECT v from Veiculo v ORDER BY v.valor DESC LIMIT :qtd")
    List<Veiculo> getMaisCaros(Integer qtd);

    @Query("SELECT v from Veiculo v WHERE v.modelo ILIKE '%1.4%' ORDER BY v.valor ASC")
    List<Veiculo> meuMetodoGenerico();

    @Query("SELECT c from Veiculo c")
    List<Veiculo> getTodos();
}
