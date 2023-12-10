package com.creditcardanalyzer.mscreditanalyzer.infra.resources;

import com.creditcardanalyzer.mscreditanalyzer.domain.model.CreditCard;
import com.creditcardanalyzer.mscreditanalyzer.domain.model.ClientCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscreditcard", path = "cards")
public interface CardResourceClient {

    @GetMapping(params = "cpf" )
    ResponseEntity<List<ClientCard>> getCardsByClientList(@RequestParam(value="cpf") String cpf);

    @GetMapping(params = {"income"})
   ResponseEntity<List<CreditCard>> getCardsByIncomeRange(@RequestParam(value = "income") Long income);
}
