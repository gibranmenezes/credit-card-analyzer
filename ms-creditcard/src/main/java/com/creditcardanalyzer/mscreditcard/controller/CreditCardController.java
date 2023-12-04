package com.creditcardanalyzer.mscreditcard.controller;

import com.creditcardanalyzer.mscreditcard.domain.CreditCardListResponse;
import com.creditcardanalyzer.mscreditcard.domain.CreditCardSaveRequest;
import com.creditcardanalyzer.mscreditcard.service.CreditCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("cards")
public class CreditCardController {

    private final CreditCardService service;

    public CreditCardController(CreditCardService service) {
        this.service = service;
    }

    @GetMapping
    public String status() {
        return "ok";
    }

    @PostMapping
    public ResponseEntity register(@RequestBody CreditCardSaveRequest data, UriComponentsBuilder uriBuilder) {
        var card = service.save(data);
        var uri = uriBuilder.path("/cards/{id}").buildAndExpand(card.getId()).toUri();

        return ResponseEntity.created(uri).body(new CreditCardListResponse(card));
    }

    @GetMapping(params = {"income"})
    public ResponseEntity<List<CreditCardListResponse>> getCardsByIncomeRange(@RequestParam(value = "income") Long income) {
        List<CreditCardListResponse> cards = service.getCardsIncomeWithinCardLimit(income);
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getCardsIncomeWithinCardLimit(income));
    }
}
