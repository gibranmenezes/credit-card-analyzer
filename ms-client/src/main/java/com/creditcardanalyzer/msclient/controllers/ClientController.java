package com.creditcardanalyzer.msclient.controllers;

import com.creditcardanalyzer.msclient.domain.client.Client;
import com.creditcardanalyzer.msclient.domain.client.ClientListingDto;
import com.creditcardanalyzer.msclient.domain.client.ClientRegisterDto;
import com.creditcardanalyzer.msclient.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
public class ClientController {

  private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientListingDto>> listAll() {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.listAll()); 
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Optional<Client>> getClientByCpf(@PathVariable String cpf) {
        var client = clientService.findClientByCpf(cpf);
        if (client.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(client);
    }
    @Transactional
    @PostMapping
    public ResponseEntity register(@RequestBody @Valid ClientRegisterDto data, UriComponentsBuilder uriBuilder) {
        var client = clientService.save(data);
        var uri = uriBuilder.path("/clients/{cpf}").buildAndExpand(client.getId()).toUri();

        return ResponseEntity.created(uri).body(new ClientListingDto(client));
    }
}
