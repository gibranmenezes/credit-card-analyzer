package com.creditcardanalyzer.msclient.services;

import com.creditcardanalyzer.msclient.domain.client.ClientListingDto;
import com.creditcardanalyzer.msclient.infra.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
