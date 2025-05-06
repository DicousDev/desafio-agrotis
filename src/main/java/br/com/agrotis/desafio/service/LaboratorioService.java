package br.com.agrotis.desafio.service;

import br.com.agrotis.desafio.dto.in.CadastroLaboratorioDTO;
import br.com.agrotis.desafio.dto.out.LaboratorioDTO;
import br.com.agrotis.desafio.model.Laboratorio;
import br.com.agrotis.desafio.repository.LaboratorioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LaboratorioService {

    private final LaboratorioRepository repository;

    public Optional<Laboratorio> pesquisarPeloId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public LaboratorioDTO cadastrar(CadastroLaboratorioDTO laboratorio) {
        Laboratorio laboratorioSaved = repository.save(Laboratorio.builder()
                .nome(laboratorio.getNome())
                .build());
        return new LaboratorioDTO(laboratorioSaved.getId(), laboratorioSaved.getNome());
    }
}
