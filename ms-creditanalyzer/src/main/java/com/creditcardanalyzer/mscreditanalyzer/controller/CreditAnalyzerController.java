package com.creditcardanalyzer.mscreditanalyzer.controller;

import com.creditcardanalyzer.mscreditanalyzer.domain.AnalysisDataRequest;
import com.creditcardanalyzer.mscreditanalyzer.infra.exceptions.ClientDataNotFoundException;
import com.creditcardanalyzer.mscreditanalyzer.infra.exceptions.ServicesComunicationErrorException;
import com.creditcardanalyzer.mscreditanalyzer.service.CreditAnalyzerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity doAnalysis(@RequestBody AnalysisDataRequest data) {


    }
}
