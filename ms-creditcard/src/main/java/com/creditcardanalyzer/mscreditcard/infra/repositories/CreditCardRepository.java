package com.creditcardanalyzer.mscreditcard.infra.repositories;

import com.creditcardanalyzer.mscreditcard.domain.models.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

    //@Query("select c from CreditCard c where c.income <= :income")
    List<CreditCard> findByIncomeLessThanEqual(BigDecimal income);
}
