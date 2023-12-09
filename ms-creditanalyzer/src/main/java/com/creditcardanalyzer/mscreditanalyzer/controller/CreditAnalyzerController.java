package com.creditcardanalyzer.mscreditanalyzer.controller;

import com.creditcardanalyzer.mscreditanalyzer.domain.model.ClientStatusResponse;
import com.creditcardanalyzer.mscreditanalyzer.infra.exceptions.ClientDataNotFoundException;
import com.creditcardanalyzer.mscreditanalyzer.infra.exceptions.ServicesComunicationErrorException;
import com.creditcardanalyzer.mscreditanalyzer.service.CreditAnalyzerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("credit-analisys")
@RequiredArgsConstructor
public class CreditAnalyzerController {

    private final CreditAnalyzerService creditAnalyzerService;

    @GetMapping
    public String status() {
        return "ok";
    }

    @GetMapping(value = "client-status", params = "cpf")
    public ResponseEntity clientStatusQuery(@RequestParam("cpf") String cpf){
        try {
            var clientStatus = creditAnalyzerService.getClientStatus(cpf);
            return ResponseEntity.ok(clientStatus);
        } catch (ClientDataNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (ServicesComunicationErrorException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }
}
