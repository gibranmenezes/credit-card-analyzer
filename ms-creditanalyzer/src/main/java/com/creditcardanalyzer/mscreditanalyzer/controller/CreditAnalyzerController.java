package com.creditcardanalyzer.mscreditanalyzer.controller;

import com.creditcardanalyzer.mscreditanalyzer.domain.AnalysisDataRequest;
import com.creditcardanalyzer.mscreditanalyzer.domain.CardIssuingRequestData;
import com.creditcardanalyzer.mscreditanalyzer.infra.exceptions.CardIssuingRequestError;
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
    public ResponseEntity getClientStatus(@RequestParam("cpf") String cpf){
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
        try {
            var clientAnalysisResponse = creditAnalyzerService.doAnalysis(data.cpf(), data.income());
            return ResponseEntity.ok(clientAnalysisResponse);
        } catch (ClientDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ServicesComunicationErrorException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }

    }

    @PostMapping("cards-issuing")
    public ResponseEntity requestCard(@RequestBody CardIssuingRequestData data) {
        try {
            var protocolCardRequest = creditAnalyzerService.requestCardIssuing(data);
            return ResponseEntity.ok(protocolCardRequest);

        } catch (CardIssuingRequestError e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
