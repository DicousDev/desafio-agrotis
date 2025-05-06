package support.provider;

import br.com.agrotis.desafio.model.Pessoa;

import java.time.LocalDateTime;

public class PessoaProvider {

    public static Pessoa.PessoaBuilder padrao() {
        return Pessoa.builder()
                .id(1L)
                .nome("Dexter")
                .dataInicial(LocalDateTime.of(2018, 9, 1, 0, 0, 0))
                .dataFinal(LocalDateTime.of(2025, 9, 1, 0, 0, 0))
                .observacoes("Dexter é uma série na netflix.")
                .propriedade(PropriedadeProvider.padrao().build())
                .laboratorio(LaboratorioProvider.padrao().build());
    }
}
