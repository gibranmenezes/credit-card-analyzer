package com.creditcardanalyzer.mscreditcard.service;

import com.creditcardanalyzer.mscreditcard.domain.models.CreditCard;
import com.creditcardanalyzer.mscreditcard.domain.dtos.CreditCardListResponse;
import com.creditcardanalyzer.mscreditcard.domain.dtos.CreditCardSaveRequest;
import com.creditcardanalyzer.mscreditcard.infra.repositories.CreditCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditCardService {

    private final CreditCardRepository repository;

    @Transactional
    public CreditCard save(CreditCardSaveRequest data) {
        var creditCard = new CreditCard(data);
        return repository.save(creditCard);
    }

    public List<CreditCardListResponse> getCardsIncomeWithinCardLimit(Long income) {
        var incomeBigDecimal = BigDecimal.valueOf(income);
        List<CreditCardListResponse> cards = repository.findByIncomeLessThanEqual(incomeBigDecimal)
                .stream()
                .map(CreditCardListResponse::new)
                .toList();

        return cards;

    }

}
