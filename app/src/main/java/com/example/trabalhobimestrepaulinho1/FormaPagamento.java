package com.example.trabalhobimestrepaulinho1;

public enum FormaPagamento {
    AVISTA("À vista"),
    APRAZO("À prazo");

    private final String descricao;

    FormaPagamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
