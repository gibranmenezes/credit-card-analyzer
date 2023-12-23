package com.creditcardanalyzer.mscreditanalyzer.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class ClientStatus {
    private ClientData client;
    private List<CardClient> cards;

}
