package com.creditcardanalyzer.mscreditcard.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "cards")
@Data
@NoArgsConstructor
public class CreditCard implements Serializable {

    @Serial
    private static final long serialVersionUID = -8274064706231283069L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private CardBrand brand;
    private BigDecimal income;
    private BigDecimal  baseLimit;

    public CreditCard(CreditCardSaveRequest data) {
        this.name = data.name();
        this.brand = data.brand();
        this.income = data.income();
        this.baseLimit = data.baseLimit();
    }
}
