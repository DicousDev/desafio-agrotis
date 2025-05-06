package br.com.agrotis.desafio.service;

import br.com.agrotis.desafio.dto.in.CadastroLaboratorioDTO;
import br.com.agrotis.desafio.dto.in.CadastroPropriedadeDTO;
import br.com.agrotis.desafio.dto.out.LaboratorioDTO;
import br.com.agrotis.desafio.dto.out.PropriedadeDTO;
import br.com.agrotis.desafio.model.Propriedade;
import br.com.agrotis.desafio.repository.PropriedadeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import support.provider.PropriedadeProvider;

import java.util.Optional;

public class PropriedadeServiceTest {

    @Mock
    private PropriedadeRepository repository;

    @InjectMocks
    private PropriedadeService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveEncontrarPropriedade() {
        var propriedade = Optional.of(PropriedadeProvider.padrao().build());
        Mockito.when(repository.findById(18L)).thenReturn(propriedade);
        var propriedadeCadastrada = service.pesquisarPeloId(18L);
        Assertions.assertThat(propriedadeCadastrada.isPresent()).isTrue();
        Assertions.assertThat(propriedadeCadastrada.get().getNome()).isEqualTo("Propriedade Dexter");
    }

    @Test
    public void naoDeveEncontrarPropriedade() {
        Optional<Propriedade> propriedade = Optional.empty();
        Mockito.when(repository.findById(18L)).thenReturn(propriedade);
        var propriedadeCadastrada = service.pesquisarPeloId(18L);
        Assertions.assertThat(propriedadeCadastrada.isEmpty()).isTrue();
    }

    @Test
    public void deveCadastrarPropriedade() {
        Propriedade propriedadeMock = PropriedadeProvider.padrao().nome("Dexter").build();
        CadastroPropriedadeDTO propriedade = CadastroPropriedadeDTO.builder()
                .nome("Dexter")
                .build();

        Mockito.when(repository.save(Mockito.any(Propriedade.class))).thenReturn(propriedadeMock);
        PropriedadeDTO resultado = service.cadastrar(propriedade);
        Assertions.assertThat(resultado.getNome()).isEqualTo("Dexter");
    }
}
