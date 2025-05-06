package br.com.agrotis.desafio.service;

import br.com.agrotis.desafio.dto.in.CadastroPropriedadeDTO;
import br.com.agrotis.desafio.dto.out.PropriedadeDTO;
import br.com.agrotis.desafio.model.Propriedade;
import br.com.agrotis.desafio.repository.PropriedadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PropriedadeService {

    private final PropriedadeRepository repository;

    public Optional<Propriedade> pesquisarPeloId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public PropriedadeDTO cadastrar(CadastroPropriedadeDTO propriedade) {
        Propriedade propriedadeSaved = repository.save(Propriedade.builder()
                .nome(propriedade.getNome())
                .build());

        return new PropriedadeDTO(propriedadeSaved.getId(), propriedadeSaved.getNome());
    }
}
