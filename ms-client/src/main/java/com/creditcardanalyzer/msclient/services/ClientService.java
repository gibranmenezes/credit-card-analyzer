package com.creditcardanalyzer.msclient.services;

import com.creditcardanalyzer.msclient.domain.client.Client;
import com.creditcardanalyzer.msclient.domain.client.dtos.ClientSaveResponse;
import com.creditcardanalyzer.msclient.domain.client.dtos.ClientSaveRequest;
import com.creditcardanalyzer.msclient.infra.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository repository;
    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public List<ClientSaveResponse> listAll(){
        return repository.findAll()
                .stream()
                .map(ClientSaveResponse::new)
                .toList();

    }

    public Optional<Client> findClientByCpf(String cpf){
        var client = repository.findByCpf(cpf).orElse(null);
        return repository.findByCpf(cpf);
    }

    public Client save(ClientSaveRequest data) {
        var client = new Client(data);
        return repository.save(client);
    }
}
