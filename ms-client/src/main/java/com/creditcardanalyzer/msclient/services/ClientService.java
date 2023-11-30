package com.creditcardanalyzer.msclient.services;

import com.creditcardanalyzer.msclient.domain.client.Client;
import com.creditcardanalyzer.msclient.domain.client.ClientListingDto;
import com.creditcardanalyzer.msclient.domain.client.ClientRegisterDto;
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

    public List<ClientListingDto> listAll(){
        return repository.findAll()
                .stream()
                .map(ClientListingDto::new)
                .toList();

    }

    public Optional<Client> findClientByCpf(String cpf){
        return repository.findByCpf(cpf);
    }

    public Client save(ClientRegisterDto data) {
        var client = new Client(data);
        return repository.save(client);
    }
}
