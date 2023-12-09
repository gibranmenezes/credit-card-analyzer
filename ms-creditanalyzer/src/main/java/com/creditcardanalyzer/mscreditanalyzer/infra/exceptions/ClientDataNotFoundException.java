package com.creditcardanalyzer.mscreditanalyzer.infra.exceptions;

public class ClientDataNotFoundException extends Exception{
    public ClientDataNotFoundException(){
        super("Client Not Found for this CPF");
    }

}
