package br.com.bruno.tabelafipe.repository;

import br.com.bruno.tabelafipe.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MarcaRepository extends JpaRepository<Marca, Long> {

    @Query("SELECT m FROM Marca m WHERE UPPER(m.nome) = UPPER(:nome)")
    Optional<Marca> findByNome(@Param("nome") String nome);
}
