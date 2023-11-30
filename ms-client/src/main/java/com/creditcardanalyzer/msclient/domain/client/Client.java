package com.creditcardanalyzer.msclient.domain.client;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
public class Client implements Serializable {

    @Serial
    private static final long serialVersionUID = -3706110847702488877L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String cpf;
    private String email;
    private Integer age;

    public Client(ClientRegisterDto data){
        this.name = data.name();
        this.cpf = data.cpf();
        this.email = data.email();
        this.age = data.age();
    }

}
