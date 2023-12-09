package com.creditcardanalyzer.mscreditcard.service;

import com.creditcardanalyzer.mscreditcard.domain.dtos.CardsByClientListResponse;
import com.creditcardanalyzer.mscreditcard.domain.models.ClientCard;
import com.creditcardanalyzer.mscreditcard.domain.models.CreditCard;
import com.creditcardanalyzer.mscreditcard.infra.repositories.ClientCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientCardService {

    private final ClientCardRepository repository;

    public List<CardsByClientListResponse> listCardsByCpf(String cpf) {
       return repository.findByCpf(cpf)
               .stream()
               .map(this::mapToResponse)
               .collect(Collectors.toList());
    }

    private CardsByClientListResponse mapToResponse(ClientCard clientCard) {
        CreditCard creditCard = clientCard.getCards();

        return new CardsByClientListResponse(
                clientCard.getName(),
                creditCard.getBrand().toString(),
                creditCard.getBaseLimit()
        );
    }
}
