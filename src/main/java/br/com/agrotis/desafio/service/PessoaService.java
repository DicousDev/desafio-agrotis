package br.com.agrotis.desafio.service;

import br.com.agrotis.desafio.converter.PessoaConverter;
import br.com.agrotis.desafio.dto.in.CadastroPessoaDTO;
import br.com.agrotis.desafio.dto.out.PessoaDTO;
import br.com.agrotis.desafio.exception.NaoEncontradoRuntimeException;
import br.com.agrotis.desafio.model.Laboratorio;
import br.com.agrotis.desafio.model.Pessoa;
import br.com.agrotis.desafio.model.Propriedade;
import br.com.agrotis.desafio.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final LaboratorioService laboratorioService;
    private final PropriedadeService propriedadeService;
    private final PessoaRepository repository;
    private final PessoaConverter converter;

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
