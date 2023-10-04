package com.example.trabalhobimestrepaulinho1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class LancamentoPedidoActivity extends AppCompatActivity {

    private Spinner spClientes;
    private Spinner spItensVenda;
    private EditText edQuantidade;
    private Button btAddItem;
    private ListView lvItensPedidos;
    private RadioGroup rgFormaPgmt;
    private RadioButton rbAVista;
    private RadioButton rbAPrazo;
    private EditText edParcelas;
    private TextView tvParcelas;
    private TextView tvValorTotal;
    private int posicaoItemSelecionado = -1;
    private List<ItemVenda> listaItemVenda;
    private List<Cliente> listaClientes;
    private ArrayList<ItemPedido> itensPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancamento_pedido);

        spClientes = findViewById(R.id.spClientes);
        spItensVenda = findViewById(R.id.spItensVenda);
        edQuantidade = findViewById(R.id.edQuantidade);
        edParcelas = findViewById(R.id.edParcelas);
        tvParcelas = findViewById(R.id.tvParcelas);
        tvValorTotal = findViewById(R.id.tvValorTotal);
        rbAPrazo = findViewById(R.id.rbAPrazo);
        rbAVista = findViewById(R.id.rbAVista);
        rgFormaPgmt = findViewById(R.id.rgFormaPgmt);
        lvItensPedidos = findViewById(R.id.itensPedidos);
        btAddItem = findViewById(R.id.btAddItem);

        listaClientes = Controller.getInstance().retornarCliente();
        listaItemVenda = Controller.getInstance().retornarItemVenda();
        itensPedidos = new ArrayList<>();

        spinnerClientes();
        spinnerItens();

        spItensVenda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicao, long l) {
               posicaoItemSelecionado = posicao;
               if(posicao >= 0){
                   if (posicao > 0 ){

                   }
               }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adcItem();
            }
        });

        rgFormaPgmt.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int formaPgmt) {
                if (formaPgmt == R.id.rbAVista) {
                    edParcelas.setVisibility(View.GONE);
                    tvParcelas.setVisibility(View.GONE);
                } else if (formaPgmt == R.id.rbAPrazo) {
                    edParcelas.setVisibility(View.VISIBLE);
                    tvParcelas.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void spinnerItens() {
        listaItemVenda = Controller.getInstance().retornarItemVenda();
        String[] itemProduto = new String[listaItemVenda.size() + 1];
        itemProduto[0] = "Selecione um Produto";
        for(int i = 0; i < listaItemVenda.size(); i++){
            itemProduto[i + 1] = listaItemVenda.get(i).getDescItem();
        }

        ArrayAdapter<ItemVenda> adapterItensVenda = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, listaItemVenda);
        adapterItensVenda.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spItensVenda.setAdapter(adapterItensVenda);
    }

    private void spinnerClientes() {
        ArrayAdapter<Cliente> adpterClientes = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, listaClientes);
        adpterClientes.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spClientes.setAdapter(adpterClientes);
    }

    private void adcItem() {
        Cliente clienteSelecionado = (Cliente) spClientes.getSelectedItem();
        ItemVenda itemVendido = (ItemVenda) spItensVenda.getSelectedItem();
        String quantidadeError = edQuantidade.getText().toString();

        if (quantidadeError.isEmpty()) {
            edQuantidade.setError("É NECESSARIO INFORMAR A QUANTIDADE");
            return;
        }

        int quantidade = Integer.parseInt(quantidadeError);

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setItemVenda(itemVendido);
        itemPedido.setQuantidade(quantidade);
        itensPedidos.add(itemPedido);

        atualizarListaItensPedidos();

        double valorTotal = calcularValorTotal();
        tvValorTotal.setText("Valor Total do Pedido: R$" + valorTotal);

        spItensVenda.setSelection(0);
        edQuantidade.getText().clear();
    }

    private double calcularValorTotal() {
        double valorTotal = 0;
        for (ItemPedido item : itensPedidos) {
            valorTotal += item.getQuantidade() * item.getItemVenda().getVlUnitario();
        }
        return valorTotal;
    }

    private void atualizarListaItensPedidos() {
        ArrayList<String> listaItens = new ArrayList<>();
        double valorTotal = 0;

        for (ItemPedido itemPedido : itensPedidos) {
            ItemVenda item = itemPedido.getItemVenda();
            int quantidade = itemPedido.getQuantidade();
            double subtotal = quantidade * item.getVlUnitario();
            valorTotal += subtotal;
            listaItens.add(item.getCodItem() + " - " + item.getDescItem() + " x" + quantidade + ": R$" + subtotal);
        }

        ArrayAdapter<String> adapterItens = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, listaItens);

        lvItensPedidos.setAdapter(adapterItens);

        tvValorTotal.setText("Valor Total do Pedido: R$" + valorTotal);
    }

    public void finalizarPedido(View view) {
        Cliente clienteSelecionado = (Cliente) spClientes.getSelectedItem();

        int formaPgmtSelecionado = rgFormaPgmt.getCheckedRadioButtonId();
        FormaPagamento formaPagamento = (formaPgmtSelecionado == R.id.rbAVista)
                ? FormaPagamento.AVISTA : FormaPagamento.APRAZO;

        int parcelas = 0;
        if (formaPagamento == FormaPagamento.APRAZO) {
            String parcelasError = edParcelas.getText().toString();
            if (parcelasError.isEmpty()) {
                edParcelas.setError("NECESSARIO INFORMAR NUMERO DE PARCELAS");
                return;
            }
            parcelas = Integer.parseInt(parcelasError);
        }
        Pedido pedido = new Pedido();
        pedido.setCliente(clienteSelecionado);
        pedido.setItemPedidos(itensPedidos);
        pedido.setFormaPagamento(formaPagamento);
        pedido.setParcelas(parcelas);

        Controller.getInstance().salvarPedido(pedido);

        Toast.makeText(LancamentoPedidoActivity.this,
                "PEDIDO LANÇADO COM SUCESSO",
                Toast.LENGTH_LONG).show();
        this.finish();
    }
}
