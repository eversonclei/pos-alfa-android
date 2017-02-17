package br.com.eversonclei.linguagens;

/**
 * Created by Everson on 16/02/2017.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    private LinguagemService linguagensService;

    private EditText etId;
    private EditText etNome;
    private EditText etClassificacao;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        etId = (EditText) findViewById(R.id.etId);
        etNome = (EditText) findViewById(R.id.etNome);
        etClassificacao = (EditText) findViewById(R.id.etClassificao);
        tvResultado = (TextView) findViewById(R.id.tvResultado);

        linguagensService = new LinguagemService(getBaseContext());
    }

    public void carregar(View view) {
        Linguagem ling = linguagensService.buscar(Integer.parseInt(etId.getText().toString()));
        if (ling == null){
            Toast.makeText(getApplicationContext(), "Não Existe", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Carregando", Toast.LENGTH_SHORT).show();
        }
        etClassificacao.setText(ling.getClassificacao());
        etNome.setText(ling.getNome());
    }

    public void salvar(View view) {
        Toast.makeText(getApplicationContext(), "Salvar", Toast.LENGTH_SHORT).show();
        Linguagem al = new Linguagem();
        if (!etId.getText().toString().isEmpty()) {
            al.setId(Integer.parseInt(etId.getText().toString()));
        }
        al.setNome(etNome.getText().toString());
        al.setClassificacao(etClassificacao.getText().toString());
        linguagensService.salvar(al);
        etId.setText("");
        etClassificacao.setText("");
        etNome.setText("");
    }

    public void excluir(View view) {
        Toast.makeText(getApplicationContext(), "Excluindo", Toast.LENGTH_SHORT).show();
        linguagensService.remover(Integer.parseInt(etId.getText().toString()));
    }

    public void listar(View view) {
        Toast.makeText(getApplicationContext(), "Listando", Toast.LENGTH_SHORT).show();
        List<Linguagem> linguagens = linguagensService.buscar();
        tvResultado.setText(linguagens.toString());
    }

}
