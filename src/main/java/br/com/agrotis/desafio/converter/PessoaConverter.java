package br.com.agrotis.desafio.converter;

import br.com.agrotis.desafio.dto.in.CadastroPessoaDTO;
import br.com.agrotis.desafio.dto.out.LaboratorioDTO;
import br.com.agrotis.desafio.dto.out.PessoaDTO;
import br.com.agrotis.desafio.dto.out.PropriedadeDTO;
import br.com.agrotis.desafio.model.Pessoa;
import org.springframework.stereotype.Component;

@Component
public class PessoaConverter {

    public Pessoa.PessoaBuilder toModel(CadastroPessoaDTO pessoa) {
        return Pessoa.builder()
                .nome(pessoa.getNome())
                .dataInicial(pessoa.getDataInicial())
                .dataFinal(pessoa.getDataFinal())
                .observacoes(pessoa.getObservacoes());
    }

    public PessoaDTO toDTO(Pessoa pessoa) {
        PropriedadeDTO propriedade = new PropriedadeDTO(pessoa.getPropriedade().getId(), pessoa.getPropriedade().getNome());
        LaboratorioDTO laboratorio = new LaboratorioDTO(pessoa.getLaboratorio().getId(), pessoa.getPropriedade().getNome());
        return PessoaDTO.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .dataInicial(pessoa.getDataInicial())
                .dataFinal(pessoa.getDataFinal())
                .observacoes(pessoa.getObservacoes())
                .propriedade(propriedade)
                .laboratorio(laboratorio)
                .build();
    }
}
