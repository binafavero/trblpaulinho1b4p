package com.example.trabalhobimestrepaulinho1;

public class ItemPedido {

    private ItemVenda itemVenda;
    private int quantidade;

    public ItemPedido() {
    }

    public ItemVenda getItemVenda() {
        return itemVenda;
    }

    public void setItemVenda(ItemVenda itemVenda) {
        this.itemVenda = itemVenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
