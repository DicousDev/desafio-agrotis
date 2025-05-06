package br.com.agrotis.desafio.converter;

import br.com.agrotis.desafio.dto.in.CadastroPessoaDTO;
import br.com.agrotis.desafio.dto.out.PessoaDTO;
import br.com.agrotis.desafio.model.Pessoa;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import support.provider.LaboratorioProvider;
import support.provider.PessoaProvider;
import support.provider.PropriedadeProvider;

import java.time.LocalDateTime;

public class PessoaConverterTest {

    private final PessoaConverter converter = new PessoaConverter();


    @Test
    protected void toModel() {
        CadastroPessoaDTO pessoa = new CadastroPessoaDTO("Dexter",
                LocalDateTime.MIN,
                LocalDateTime.MAX,
                "observacoes",
                5L,
                2L);

        Pessoa resultado = converter.toModel(pessoa)
                .propriedade(PropriedadeProvider.padrao().build())
                .laboratorio(LaboratorioProvider.padrao().build())
                .build();
        Assertions.assertThat(resultado.getNome()).isEqualTo("Dexter");
        Assertions.assertThat(resultado.getDataInicial()).isEqualTo(LocalDateTime.MIN);
        Assertions.assertThat(resultado.getDataFinal()).isEqualTo(LocalDateTime.MAX);
        Assertions.assertThat(resultado.getObservacoes()).isEqualTo(pessoa.getObservacoes());
    }

    @Test
    protected void toDTO() {
        Pessoa pessoa = PessoaProvider.padrao().build();
        PessoaDTO resultado = converter.toDTO(pessoa);
        Assertions.assertThat(resultado).hasNoNullFieldsOrProperties();
    }
}
