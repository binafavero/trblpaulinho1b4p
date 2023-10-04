package com.example.trabalhobimestrepaulinho1;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Controller extends AppCompatActivity {

    private static Controller instance;
    private ArrayList<Cliente> listaCliente;
    private ArrayList<ItemVenda> listaItemVenda;
    private ArrayList<Pedido> listaPedidos;

    // Construtor privado para garantir que apenas uma instância do Controller seja criada.
    private Controller() {
        listaCliente = new ArrayList<>();
        listaItemVenda = new ArrayList<>();
        listaPedidos = new ArrayList<>();
    }

    // Método para obter a única instância do Controller (usando o padrão Singleton).
    public static Controller getInstance() {
        if (instance == null) {
            return instance = new Controller();
        } else {
            return instance;
        }
    }

    /*public void salvarCliente(Cliente cliente) {
        listaCliente.add(cliente);
    }

    public ArrayList<Cliente> getListaCliente() {
        return listaCliente;
    }

    public void salvarItemVenda(ItemVenda itemVenda) {
        listaItemVenda.add(itemVenda);
    }

    public ArrayList<ItemVenda> getListaItemVenda() {
        return listaItemVenda;
    }

    public void salvarPedido(Pedido pedido) {
        listaPedidos.add(pedido);
    }

    public ArrayList<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    // Método para retornar a lista de itens de venda.
    public ArrayList<ItemVenda> retornarItensVenda() {
        return listaItemVenda;
    }

    // Método para retornar a lista de clientes.
    public ArrayList<Cliente> retornarClientes() {
        return listaCliente;
    }*/

    public void salvarCliente(Cliente cliente) { listaCliente.add(cliente); }

    public ArrayList<Cliente> retornarCliente(){ return listaCliente;}
    //----------------------------------------------------------------------------------------------
    public void salvarItemVenda(ItemVenda itemVenda) { listaItemVenda.add(itemVenda); }

    public ArrayList<ItemVenda> retornarItemVenda(){ return listaItemVenda;}
    //----------------------------------------------------------------------------------------------
    public void salvarPedido(Pedido pedido) { listaPedidos.add(pedido); }

    public ArrayList<Pedido> retornarItemPedido(){ return listaPedidos;}

}
