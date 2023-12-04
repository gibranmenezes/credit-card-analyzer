package com.creditcardanalyzer.mscreditcard.repository;

import com.creditcardanalyzer.mscreditcard.domain.ClientCard;
import com.creditcardanalyzer.mscreditcard.domain.CardsByClientListResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientCardRepository extends JpaRepository<ClientCard, Long> {

    List<ClientCard> findByCpf(String cpf);
}
