package br.com.agrotis.desafio.service;

import br.com.agrotis.desafio.dto.in.CadastroLaboratorioDTO;
import br.com.agrotis.desafio.dto.out.LaboratorioDTO;
import br.com.agrotis.desafio.model.Laboratorio;
import br.com.agrotis.desafio.repository.LaboratorioRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import support.provider.LaboratorioProvider;

import java.util.Optional;

public class LaboratorioServiceTest {

    @Mock
    private LaboratorioRepository repository;

    @InjectMocks
    private LaboratorioService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    protected void deveEncontrarLaboratorio() {
        var laboratorio = Optional.of(LaboratorioProvider.padrao().build());
        Mockito.when(repository.findById(18L)).thenReturn(laboratorio);
        var laboratorioCadastrado = service.pesquisarPeloId(18L);
        Assertions.assertThat(laboratorioCadastrado.isPresent()).isTrue();
        Assertions.assertThat(laboratorioCadastrado.get().getNome()).isEqualTo("Laboratório Dexter");
    }

    @Test
    protected void naoDeveEncontrarLaboratorio() {
        Optional<Laboratorio> laboratorio = Optional.empty();
        Mockito.when(repository.findById(18L)).thenReturn(laboratorio);
        var laboratorioCadastrado = service.pesquisarPeloId(18L);
        Assertions.assertThat(laboratorioCadastrado.isEmpty()).isTrue();
    }

    @Test
    protected void deveCadastrarLaboratorio() {
        Laboratorio laboratorioMock = LaboratorioProvider.padrao().nome("Dexter").build();
        CadastroLaboratorioDTO laboratorio = CadastroLaboratorioDTO.builder()
                .nome("Dexter")
                .build();

        Mockito.when(repository.save(Mockito.any(Laboratorio.class))).thenReturn(laboratorioMock);
        LaboratorioDTO resultado = service.cadastrar(laboratorio);
        Assertions.assertThat(resultado.getNome()).isEqualTo("Dexter");
    }
}
