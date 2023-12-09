package com.creditcardanalyzer.mscreditcard.domain.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "client_cards")
@Data
@NoArgsConstructor
public class ClientCard implements Serializable {

    @Serial
    private static final long serialVersionUID = -1164763069747760840L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String cpf;
    private BigDecimal approvedLimit;
    @ManyToOne
    @JoinColumn(name="card_id")
    private CreditCard cards;
}
