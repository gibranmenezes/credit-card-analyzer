package com.creditcardanalyzer.msclient.resource;

import com.creditcardanalyzer.msclient.domain.client.dtos.ClientDataResponse;
import com.creditcardanalyzer.msclient.domain.client.dtos.ClientSaveResponse;
import com.creditcardanalyzer.msclient.domain.client.dtos.ClientSaveRequest;
import com.creditcardanalyzer.msclient.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
public class ClientResouce {

  private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientSaveResponse>> listAll() {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.listAll()); 
    }

    @GetMapping(params = {"cpf"})
    public ResponseEntity getClientByCpf(@RequestParam("cpf") String cpf) {
        var client = clientService.findClientByCpf(cpf);
        if (client.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(new ClientDataResponse(client));
        }

    }
    @Transactional
    @PostMapping
    public ResponseEntity register(@RequestBody @Valid ClientSaveRequest data, UriComponentsBuilder uriBuilder) {
        var client = clientService.save(data);
        var uri = uriBuilder.path("/clients/{id}").buildAndExpand(client.getId()).toUri();

        return ResponseEntity.created(uri).body(new ClientSaveResponse(client));
    }
}
