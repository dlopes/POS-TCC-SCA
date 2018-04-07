package com.organizacao.sca.repository.filter;

import java.io.Serializable;

public class AlunoFilter implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private String rg;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }
  
}