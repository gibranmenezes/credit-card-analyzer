package com.creditcardanalyzer.mscreditcard.resource;

import com.creditcardanalyzer.mscreditcard.domain.dtos.CardsByClientResponse;
import com.creditcardanalyzer.mscreditcard.domain.dtos.CreditCardListResponse;
import com.creditcardanalyzer.mscreditcard.domain.dtos.CreditCardSaveRequest;
import com.creditcardanalyzer.mscreditcard.service.ClientCardService;
import com.creditcardanalyzer.mscreditcard.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("cards")
@RequiredArgsConstructor
public class CreditCardResource {

    private final CreditCardService creditCardService;

    private final ClientCardService clientCardService;


    @GetMapping
    public String status() {
        return "ok";
    }

    @PostMapping
    public ResponseEntity register(@RequestBody CreditCardSaveRequest data, UriComponentsBuilder uriBuilder) {
        var card = creditCardService.save(data);
        var uri = uriBuilder.path("/cards/{id}").buildAndExpand(card.getId()).toUri();
        return ResponseEntity.created(uri).body(new CreditCardListResponse(card));
    }

    @GetMapping(params = {"income"})
    public ResponseEntity<List<CreditCardListResponse>> getCardsByIncomeRange(@RequestParam(value = "income") Long income) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(creditCardService.getCardsIncomeWithinCardLimit(income));
    }

    @GetMapping(params = "cpf" )
    public ResponseEntity<List<CardsByClientResponse>> getCardsByClient(@RequestParam(value="cpf") String cpf) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(clientCardService.listCardsByCpf(cpf));
    }
}