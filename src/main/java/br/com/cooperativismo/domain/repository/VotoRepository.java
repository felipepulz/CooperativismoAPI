package br.com.cooperativismo.domain.repository;

import br.com.cooperativismo.domain.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, String>, JpaSpecificationExecutor<Voto> {

    long countByCpfAndIdPauta(String cpf, int idPauta);

}
