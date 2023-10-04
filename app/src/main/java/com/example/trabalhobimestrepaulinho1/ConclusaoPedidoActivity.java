package com.example.trabalhobimestrepaulinho1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class ConclusaoPedidoActivity extends AppCompatActivity {

    private TextView tvClientes;

    private EditText edtQuantidadeParcelas;
    private RadioGroup rgFormaPgmt;
    private TextView tvParcelas;
    private TextView tvValorTotal;
    private ListView lvItensPedidos;
    private Button btFinalizar;
    private Pedido pedido;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conclusao_pedido);

        tvClientes = findViewById(R.id.tvClientes);
        rgFormaPgmt = findViewById(R.id.rgFormaPgmt);
        tvParcelas = findViewById(R.id.tvParcelas);
        tvValorTotal = findViewById(R.id.tvValorTotal);
        lvItensPedidos = findViewById(R.id.lvItensPedidos);
        btFinalizar = findViewById(R.id.btFinalizar);

        pedido = (Pedido) getIntent().getSerializableExtra("pedido");
        if (pedido != null){
            configurarCampos();
            configurarListaItensPedidos();
        }else{
            Toast.makeText(this, "PEDIDO INVALIDO",
                    Toast.LENGTH_LONG).show();
            finish();
        }

        btFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ConclusaoPedidoActivity.this,
                        "PEDIDO FINALIZADO COM SUCESSO",
                                Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    private void configurarListaItensPedidos() {
    }

    private void configurarCampos() {
        tvClientes.setText("Cliente: " + pedido.getCliente().getNome());
        rgFormaPgmt.setTextDirection(Integer.parseInt("Forma de Pagamento: "
                + pedido.getFormaPagamento().toString()));

        if (pedido.getFormaPagamento() == FormaPagamento.APRAZO){
            tvClientes.setVisibility(View.VISIBLE);
            tvParcelas.setText("Parcelas: " + pedido.getParcelas());
        } else {
            tvParcelas.setVisibility(View.GONE);
    }

        DecimalFormat df = new DecimalFormat("#.00");
        String valorTotal = df.format(calcularValorTotal());
        tvValorTotal.setText("Valor Total: R$ " + valorTotal);

}

    private double calcularValorTotal() {
        double valorTotal = 0;
        for (ItemPedido item : pedido.getItemPedidos()){
            valorTotal += item.getQuantidade() * item.getItemVenda().getValorUnitario();
        }
        return valorTotal;
    }
    }
