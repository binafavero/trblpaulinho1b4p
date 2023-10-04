package com.example.trabalhobimestrepaulinho1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trabalhobimestrepaulinho1.Controller;
import com.example.trabalhobimestrepaulinho1.ItemVenda;

public class CadastroItemVendaActivity extends AppCompatActivity {
    
    private Button btSalvarItem;
    private EditText edCodigoItem;
    private EditText edDescItem;
    private EditText edVlUni;
    private TextView tvItensCad;
    
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_item_venda);
        
        
        btSalvarItem = findViewById(R.id.btSalvarItem);
        edCodigoItem = findViewById(R.id.edCodItem);
        edDescItem = findViewById(R.id.edDescItem);
        edVlUni = findViewById(R.id.edVlUni);
        tvItensCad = findViewById(R.id.tvItensCad);
        
        atualizarListaItens();

        btSalvarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarItemVenda();
            }
        });
        
    }

    private void salvarItemVenda() {
        if (edCodigoItem.getText().toString().isEmpty()) {
            edCodigoItem.setError("INFORME O CODIGO DO ITEM");
            return;
        }
        if (edDescItem.getText().toString().isEmpty()) {
            edDescItem.setError("INSIRA A DESCRIÇAO DO ITEM");
            return;
        }
        if (edVlUni.getText().toString().isEmpty()) {
            edVlUni.setError("INSIRA O VALOR UNITARIO DESTE ITEM");
            return;
        }
        ItemVenda item = new ItemVenda();
        item.setCodItem(Integer.parseInt(edCodigoItem.getText().toString()));
        item.setDescItem(edDescItem.getText().toString());
        item.getValorUnitario();

        Controller.getInstance().salvarItemVenda(item);

        Toast.makeText(CadastroItemVendaActivity.this, "ITEM CADASTRADO",
                Toast.LENGTH_SHORT).show();

        limparCampos();
    }



    private void atualizarListaItens() {

     String texto="";
     for (ItemVenda item : Controller.getInstance().retornarItemVenda()){
         texto+= "CODIGO: " + item.getCodItem() + "\n" + "DESCRIÇÃO: "  + item.getDescItem() + "\n" +
                 "VALOR UNIDADE: " + item.getValorUnitario() +
                  "\n" + "--------------------------------\n";
            }
            tvItensCad.setText(texto);
        }
    private void limparCampos() {
        edCodigoItem.getText().clear();
        edDescItem.getText().clear();
        edVlUni.getText().clear();
    }

}
