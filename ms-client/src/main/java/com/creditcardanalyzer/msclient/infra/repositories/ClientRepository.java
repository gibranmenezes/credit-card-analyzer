package com.creditcardanalyzer.msclient.infra.repositories;

import com.creditcardanalyzer.msclient.domain.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
