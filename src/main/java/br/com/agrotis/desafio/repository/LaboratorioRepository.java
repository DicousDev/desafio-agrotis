package br.com.agrotis.desafio.repository;

import br.com.agrotis.desafio.model.Laboratorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long>, JpaSpecificationExecutor<Laboratorio> {

    @Transactional(readOnly = true)
    @Query("SELECT l FROM Laboratorio l LEFT JOIN FETCH l.pessoas p WHERE l.id = :id")
    Optional<Laboratorio> findByIdFetchPessoas(@Param("id") Long id);

    @Transactional(readOnly = true)
    @Query("SELECT l FROM Laboratorio l WHERE lower(l.nome) LIKE %:nome%")
    Page<Laboratorio> findAll(@Param("nome") String nome, Pageable pageable);
}
