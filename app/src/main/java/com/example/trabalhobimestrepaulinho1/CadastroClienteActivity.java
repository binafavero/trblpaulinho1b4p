package com.example.trabalhobimestrepaulinho1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CadastroClienteActivity extends AppCompatActivity {

    private Button btSalvar;
    private EditText edNomeCliente;
    private EditText edCpfCliente;
    private TextView tvClientesCadastrados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);
        
        btSalvar =  findViewById(R.id.btSalvar);
        edNomeCliente = findViewById(R.id.edNomeCliente);
        edCpfCliente = findViewById(R.id.edCpfCliente);
        tvClientesCadastrados = findViewById(R.id.tvClientesCadastrados);
        
        atualizarListaClientes();
        
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarCliente();
            }
        });
    }

    private void salvarCliente() {
        if(edNomeCliente.getText().toString().isEmpty()){
            edNomeCliente.setError("INFORME O NOME DO CLIENTE!");
            return;
        }
        if (edCpfCliente.getText().toString().isEmpty()){
            edCpfCliente.setError("INFORME O CPF DO CLIENTE");
            return;
        }

   Cliente cliente = new Cliente();
    cliente.setNome(edNomeCliente.getText().toString());
    cliente.setCpf(edCpfCliente.getText().toString());

    Controller.getInstance().salvarCliente(cliente);

    Toast.makeText(CadastroClienteActivity.this,
        "CLIENTE CADASTRADO COM SUCESSO!",
        Toast.LENGTH_LONG).show();

        limparCampos();
   }

    private void atualizarListaClientes() {
        String texto="";
        for (Cliente cliente : Controller.getInstance().retornarCliente()){
            texto+= "ID: " + cliente.getId() + "\n" + "NOME: "  + cliente.getNome() + "\n" +
                "CPF: " + cliente.getCpf()+ "\n" + "--------------------------------\n";
        }
        tvClientesCadastrados.setText(texto);
    }




    private void limparCampos() {
        edNomeCliente.getText().clear();
        edCpfCliente.getText().clear();
    }


}
