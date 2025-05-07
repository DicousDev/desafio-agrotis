package br.com.agrotis.desafio.service;

import br.com.agrotis.desafio.converter.PessoaConverter;
import br.com.agrotis.desafio.dto.filter.PessoaFilter;
import br.com.agrotis.desafio.dto.in.CadastroPessoaDTO;
import br.com.agrotis.desafio.dto.out.PessoaDTO;
import br.com.agrotis.desafio.exception.NaoEncontradoRuntimeException;
import br.com.agrotis.desafio.model.Laboratorio;
import br.com.agrotis.desafio.model.Pessoa;
import br.com.agrotis.desafio.model.Propriedade;
import br.com.agrotis.desafio.repository.PessoaRepository;
import br.com.agrotis.desafio.repository.specification.PessoaSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final LaboratorioService laboratorioService;
    private final PropriedadeService propriedadeService;
    private final PessoaRepository repository;
    private final PessoaConverter converter;

    public Page<PessoaDTO> pesquisarPessoas(PessoaFilter filter) {
        Pageable pageable = PageRequest.of(Objects.isNull(filter.getPage()) ? 0 : filter.getPage(),
                Objects.isNull(filter.getSize()) ? 30 : filter.getSize());

        Page<Pessoa> pessoas = repository.findAll(PessoaSpecification.filter(filter), pageable);
        List<PessoaDTO> pessoasList = pessoas.get().map(converter::toDTO).toList();
        return new PageImpl<>(pessoasList,
                pessoas.getPageable(),
                pessoas.getTotalElements());
    }

    @Transactional
    public PessoaDTO cadastrar(CadastroPessoaDTO pessoa) {

        Propriedade propriedade = propriedadeService.pesquisarPeloId(pessoa.getPropriedadeId())
                .orElseThrow(() -> new NaoEncontradoRuntimeException("Propriedade [%s] não encontrada.".formatted(pessoa.getPropriedadeId())));

        Laboratorio laboratorio = laboratorioService.pesquisarPeloId(pessoa.getLaboratorioId())
                .orElseThrow(() -> new NaoEncontradoRuntimeException("Laboratório [%s] não encontrado.".formatted(pessoa.getLaboratorioId())));

        Pessoa pessoaConvertida = converter.toModel(pessoa)
                .propriedade(propriedade)
                .laboratorio(laboratorio)
                .build();

        Pessoa pessoaSaved = repository.save(pessoaConvertida);
        return converter.toDTO(pessoaSaved);
    }
}
