package com.creditcardanalyzer.mscreditcard.repository;

import com.creditcardanalyzer.mscreditcard.domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

    //@Query("select c from CreditCard c where c.income <= :income")
    List<CreditCard> findByIncomeLessThanEqual(BigDecimal income);
}
