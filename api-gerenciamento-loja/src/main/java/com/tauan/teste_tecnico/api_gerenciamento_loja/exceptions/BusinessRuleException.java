package com.tauan.teste_tecnico.api_gerenciamento_loja.exceptions;

public class BusinessRuleException extends RuntimeException{
    public BusinessRuleException(String message) {
        super(message);
    }
    public BusinessRuleException(String message, Throwable cause){
        super(message, cause);
    }
}
