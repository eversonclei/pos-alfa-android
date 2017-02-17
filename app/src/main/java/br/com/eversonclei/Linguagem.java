package br.com.eversonclei.linguagens;

/**
 * Created by Everson on 16/02/2017.
 */

public class Linguagem {

    private Integer id;
    private String nome;
    private String classificacao;

    public Linguagem(Integer id, String nome, String classificacao) {
        this.id = id;
        this.nome = nome;
        this.classificacao = classificacao;
    }

    public Linguagem(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    @Override
    public String toString() {
        return this.id + " - " + this.nome + " : " + " (" + this.classificacao + ")";
    }

}
