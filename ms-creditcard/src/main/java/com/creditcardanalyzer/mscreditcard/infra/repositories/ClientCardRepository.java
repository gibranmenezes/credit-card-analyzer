package com.creditcardanalyzer.mscreditcard.infra.repositories;

import com.creditcardanalyzer.mscreditcard.domain.models.ClientCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientCardRepository extends JpaRepository<ClientCard, Long> {

    List<ClientCard> findByCpf(String cpf);
}
