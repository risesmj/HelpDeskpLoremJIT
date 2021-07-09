package com.loremjit.centraldechamados.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.loremjit.centraldechamados.R;
import com.loremjit.centraldechamados.model.Login.Autenticacao;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsuario;
    private EditText etSenha;
    private Switch swManterConectado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        etUsuario = findViewById(R.id.etUsuario);
        etSenha = findViewById(R.id.etSenha);
        swManterConectado = findViewById(R.id.swManterConectado);
    }

    public void botaoEntrar(View v){
        Autenticacao autenticacao = new Autenticacao(etUsuario.getText().toString(),
                etSenha.getText().toString(),
                swManterConectado.isChecked());
        if(autenticacao.connect()) {
            setResult(RESULT_OK);
            finish();
        }else{
            Toast.makeText(this,getString(R.string.login_invalido),Toast.LENGTH_LONG).show();
        }
    }
}
