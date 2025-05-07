package br.com.agrotis.desafio.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collection;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LaboratorioComPessoasDTO {

    private Long id;
    private String nome;
    private Collection<PessoaLaboratorioDTO> pessoas;
}
