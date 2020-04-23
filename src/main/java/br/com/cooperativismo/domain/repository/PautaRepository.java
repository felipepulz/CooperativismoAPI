package br.com.cooperativismo.domain.repository;

import br.com.cooperativismo.domain.entity.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, String>, JpaSpecificationExecutor<Pauta> {

    long countByNomePauta(String nomePauta);

    Pauta findPautaByNomePauta(String nomePauta);

    List<Pauta> findAllByDataFimPautaBeforeAndSituacaoEquals(LocalDateTime localDateTime, Pauta.Situacao situacao);

}