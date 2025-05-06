package br.com.agrotis.desafio.service;

import br.com.agrotis.desafio.converter.PessoaConverter;
import br.com.agrotis.desafio.dto.in.CadastroPessoaDTO;
import br.com.agrotis.desafio.dto.out.PessoaDTO;
import br.com.agrotis.desafio.exception.NaoEncontradoRuntimeException;
import br.com.agrotis.desafio.model.Laboratorio;
import br.com.agrotis.desafio.model.Pessoa;
import br.com.agrotis.desafio.model.Propriedade;
import br.com.agrotis.desafio.repository.PessoaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import support.provider.LaboratorioProvider;
import support.provider.PessoaProvider;
import support.provider.PropriedadeProvider;

import java.time.LocalDateTime;
import java.util.Optional;

public class PessoaServiceTest {

    @Mock
    private LaboratorioService laboratorioService;
    @Mock
    private PropriedadeService propriedadeService;
    @Mock
    private PessoaRepository repository;
    private PessoaConverter converter;
    private PessoaService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        converter = new PessoaConverter();
        service = new PessoaService(laboratorioService, propriedadeService, repository, converter);
    }

    @Test
    protected void deveFalharNaTentativaDeCadastrarPessoaENaoEncontraPropriedade() {
        CadastroPessoaDTO pessoa = new CadastroPessoaDTO("Dexter",
                LocalDateTime.MIN,
                LocalDateTime.MAX,
                null,
                5L,
                2L);

        Optional<Propriedade> propriedadeMock = Optional.empty();
        Mockito.when(propriedadeService.pesquisarPeloId(5L)).thenReturn(propriedadeMock);
        Assertions.assertThatThrownBy(() -> service.cadastrar(pessoa))
                .isInstanceOf(NaoEncontradoRuntimeException.class)
                .hasMessage("Propriedade [5] não encontrada.");

    }

    @Test
    protected void deveFalharNaTentativaDeCadastrarPessoaENaoEncontraLaboratorio() {
        CadastroPessoaDTO pessoa = new CadastroPessoaDTO("Dexter",
                LocalDateTime.MIN,
                LocalDateTime.MAX,
                null,
                5L,
                2L);

        Optional<Propriedade> propriedadeMock = Optional.of(PropriedadeProvider.padrao().build());
        Optional<Laboratorio> laboratorioMock = Optional.empty();
        Mockito.when(propriedadeService.pesquisarPeloId(5L)).thenReturn(propriedadeMock);
        Mockito.when(laboratorioService.pesquisarPeloId(2L)).thenReturn(laboratorioMock);
        Assertions.assertThatThrownBy(() -> service.cadastrar(pessoa))
                .isInstanceOf(NaoEncontradoRuntimeException.class)
                .hasMessage("Laboratório [2] não encontrado.");
    }

    @Test
    protected void deveCadastrarPessoa() {
        CadastroPessoaDTO pessoa = new CadastroPessoaDTO("Dexter",
                LocalDateTime.MIN,
                LocalDateTime.MAX,
                null,
                5L,
                2L);

        Optional<Propriedade> propriedadeMock = Optional.of(PropriedadeProvider.padrao().build());
        Optional<Laboratorio> laboratorioMock = Optional.of(LaboratorioProvider.padrao().build());
        Pessoa pessoaMock = PessoaProvider.padrao()
                .propriedade(propriedadeMock.get())
                .laboratorio(laboratorioMock.get())
                .build();
        Mockito.when(propriedadeService.pesquisarPeloId(5L)).thenReturn(propriedadeMock);
        Mockito.when(laboratorioService.pesquisarPeloId(2L)).thenReturn(laboratorioMock);
        Mockito.when(repository.save(Mockito.any(Pessoa.class))).thenReturn(pessoaMock);
        PessoaDTO resultado = service.cadastrar(pessoa);
        Mockito.verify(repository).save(Mockito.any(Pessoa.class));
        Assertions.assertThat(resultado).hasNoNullFieldsOrProperties();
    }
}
