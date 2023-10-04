package com.example.trabalhobimestrepaulinho1;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private Cliente cliente;
    private ArrayList<ItemPedido> itemPedidos;
    private FormaPagamento formaPagamento;
    private int parcelas;



    public Pedido() {

    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedido> getItemPedidos() {
        return itemPedidos;
    }

    public void setItemPedidos(ArrayList<ItemPedido> itemPedidos) {
        this.itemPedidos = itemPedidos;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }
    public Pedido(ArrayList<ItemPedido> itemPedidos) {
        this.itemPedidos = itemPedidos;
    }
}
