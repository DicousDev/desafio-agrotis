package br.com.agrotis.desafio.service;

import br.com.agrotis.desafio.dto.in.AtualizarLaboratorioDTO;
import br.com.agrotis.desafio.dto.in.CadastroLaboratorioDTO;
import br.com.agrotis.desafio.dto.out.LaboratorioComPessoasDTO;
import br.com.agrotis.desafio.dto.out.LaboratorioDTO;
import br.com.agrotis.desafio.dto.out.PessoaLaboratorioDTO;
import br.com.agrotis.desafio.exception.NaoEncontradoRuntimeException;
import br.com.agrotis.desafio.model.Laboratorio;
import br.com.agrotis.desafio.model.Pessoa;
import br.com.agrotis.desafio.repository.LaboratorioRepository;
import br.com.agrotis.desafio.repository.specification.LaboratorioSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LaboratorioService {

    private final LaboratorioRepository repository;

    public Page<LaboratorioDTO> pesquisarPorPagina(String nome, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Laboratorio> pageLaboratorio = repository.findAll(LaboratorioSpecification.filters(nome), pageable);

        List<LaboratorioDTO> laboratorioDto = pageLaboratorio.get().map(laboratorio -> LaboratorioDTO.builder()
                .id(laboratorio.getId())
                .nome(laboratorio.getNome())
                .build())
            .toList();

        return new PageImpl<>(laboratorioDto,
                pageLaboratorio.getPageable(),
                pageLaboratorio.getTotalElements());
    }

    public Optional<Laboratorio> pesquisarPeloId(Long id) {
        return repository.findById(id);
    }

    public LaboratorioComPessoasDTO pesquisarPeloIdFetchPessoas(Long id) {
        Laboratorio laboratorio = repository.findByIdFetchPessoas(id)
                .orElseThrow(() -> new NaoEncontradoRuntimeException("Laboratório [%s] não encontrado.".formatted(id)));

        Collection<Pessoa> pessoasDoLaboratorio = Objects.isNull(laboratorio.getPessoas()) ? Collections.emptyList() : laboratorio.getPessoas();
        return LaboratorioComPessoasDTO.builder()
                .id(laboratorio.getId())
                .nome(laboratorio.getNome())
                .pessoas(pessoasDoLaboratorio.stream().map(pessoa -> PessoaLaboratorioDTO.builder()
                                .id(pessoa.getId())
                                .nome(pessoa.getNome())
                                .dataInicial(pessoa.getDataInicial())
                                .dataFinal(pessoa.getDataFinal())
                                .observacoes(pessoa.getObservacoes())
                                .build())
                        .toList())
                .build();
    }

    @Transactional
    public LaboratorioDTO cadastrar(CadastroLaboratorioDTO laboratorio) {
        Laboratorio laboratorioSaved = repository.save(Laboratorio.builder()
                .nome(laboratorio.getNome())
                .build());
        return new LaboratorioDTO(laboratorioSaved.getId(), laboratorioSaved.getNome());
    }

    @Transactional
    public LaboratorioDTO atualizar(Long idLaboratorio, AtualizarLaboratorioDTO laboratorio) {

        Laboratorio laboratorioParaAtualizar = pesquisarPeloId(idLaboratorio)
                .orElseThrow(() -> new NaoEncontradoRuntimeException("Laboratório [%s] não encontrado.".formatted(idLaboratorio)));

        laboratorioParaAtualizar = laboratorioParaAtualizar.updateNome(laboratorio.getNome());
        repository.save(laboratorioParaAtualizar);
        return new LaboratorioDTO(laboratorioParaAtualizar.getId(), laboratorioParaAtualizar.getNome());
    }
}
