package br.com.eversonclei.linguagens;

/**
 * Created by Everson on 16/02/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class LinguagemService {

    private SQLiteDatabase db;
    private CriaBanco banco;

    public LinguagemService(Context context) {
        banco = new CriaBanco(context);
    }

    public boolean salvar(Linguagem empresa) {
        ContentValues valores;
        long resultado = -1;

        db = banco.getWritableDatabase();
        valores = new ContentValues();

        valores.put(CriaBanco.NOME, empresa.getNome());
        valores.put(CriaBanco.CLASSIFICACAO, empresa.getClassificacao());


        if (empresa.getId() != null && empresa.getId() != 0){
            String where = CriaBanco.ID + " = " + empresa.getId();
            resultado = db.update(CriaBanco.TABELA, valores, where, null);
        } else {
            resultado = db.insert(CriaBanco.TABELA, null, valores);
        }

        db.close();
        return resultado != -1;
    }

    public boolean remover(Integer id){
        String where = CriaBanco.ID + " = " + id;
        db = banco.getReadableDatabase();
        int resultado = db.delete(CriaBanco.TABELA, where, null);
        db.close();
        return resultado != -1;
    }

    public List<Linguagem> buscar(){
        Cursor dados;
        List<Linguagem> linguagens = new ArrayList<>();
        String[] campos = {CriaBanco.ID, CriaBanco.NOME, CriaBanco.CLASSIFICACAO};

        db = banco.getReadableDatabase();
        dados = db.query(banco.TABELA, campos, null, null, null, null, null, null);

        if(dados!=null && dados.moveToFirst()){
            do {
                linguagens.add(new Linguagem(dados.getInt(0), dados.getString(1), dados.getString(2)));
            } while (dados.moveToNext());
        }

        db.close();
        return linguagens;
    }

    // Busca a linguagem pelo id
    public Linguagem buscar(Integer id) {
        Cursor dados;
        String[] campos =  {CriaBanco.ID, CriaBanco.NOME, CriaBanco.CLASSIFICACAO};
        db = banco.getReadableDatabase();
        dados = db.query(banco.TABELA, campos, "id=" + id, null, null, null, null, null);

        if (dados.getCount() > 0) {

            // Posicinoa no primeiro elemento do cursor
            dados.moveToFirst();

            Linguagem linguagem = new Linguagem();

            // Lê os dados
            linguagem.setNome(dados.getString(0));
            linguagem.setClassificacao(dados.getString(1));

            return linguagem;
        }

        return null;
    }

}
