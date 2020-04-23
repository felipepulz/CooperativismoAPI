package br.com.cooperativismo.infrastructure.services;

import br.com.cooperativismo.application.error.NotValidException;
import br.com.cooperativismo.application.request.PautaRequest;
import br.com.cooperativismo.domain.repository.PautaRepository;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PautaServiceImplTest {

    private PautaServiceImpl pautaService;
    private PautaRepository pautaRepository;

    @Before
    public void initialize() {
        pautaRepository = mock(PautaRepository.class);
        pautaService = new PautaServiceImpl(pautaRepository);
    }

    @Test(expected = NotValidException.class)
    public void givenAnEmptyPautaNameShouldError() {
        pautaService.savePauta(new PautaRequest("","Alguma descrição",1));
    }

    @Test(expected = NotValidException.class)
    public void givenAnEmptyPautaDescriptionShouldError() {
        pautaService.savePauta(new PautaRequest("Algum nome","",1));
    }

    @Test(expected = NotValidException.class)
    public void givenAnPautaAlreadyRegisteredShouldReturnError() {
        when(pautaRepository.countByNomePauta("Algum nome")).thenReturn((long) 1);
        pautaService.savePauta(new PautaRequest("Algum nome","Alguma descricao",1));
    }

}
