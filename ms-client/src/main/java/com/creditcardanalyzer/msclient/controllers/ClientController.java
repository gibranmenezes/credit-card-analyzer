package com.creditcardanalyzer.msclient.controllers;

import com.creditcardanalyzer.msclient.domain.client.ClientListingDto;
import com.creditcardanalyzer.msclient.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientListingDto>> listAll() {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.listAll()); 
    }
}
