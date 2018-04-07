/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.organizacao.sca.controller;

import com.organizacao.sca.model.Aluno;
import com.organizacao.sca.service.CadastroAlunoService;
import com.organizacao.sca.service.NegocioException;
import com.organizacao.sca.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
//import javax.faces.bean.ViewScoped;
import javax.faces.view.ViewScoped;//Bean cdi
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Dilson
 */
@Named
@ViewScoped
public class CadastroMatriculaBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private CadastroAlunoService cadastroAlunoService;
    private Aluno aluno;

    public CadastroMatriculaBean() {
        limpar();
    }

    public void inicializar() {
        // if (FacesUtil.isNotPostback()) {
        if (this.aluno == null) {
            limpar();
        }
        System.out.print("Carregandp");
    }

    private void limpar() {
        aluno = new Aluno();
    }

    public void salvar() {
        try {
            this.aluno = cadastroAlunoService.salvar(this.aluno);
            limpar();

            FacesUtil.addInfoMessage("Aluno salvo com sucesso!");
        } catch (NegocioException e) {
            FacesUtil.addInfoMessage(e.getMessage());
        }
    }

    public boolean isEditando() {
        return this.aluno.getId() != null;
    }
    
    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    
 public List<SelectItem> getSexo(){
    List<SelectItem> sexo = new ArrayList<SelectItem>(2);
    sexo.add(new SelectItem("M", "Masculino"));
    sexo.add(new SelectItem("F", "Feminino"));


    return sexo;
 }
 
 public List<SelectItem> getGrau(){
    List<SelectItem> grau = new ArrayList<SelectItem>(2);
    grau.add(new SelectItem("1", "Fundamental"));
    grau.add(new SelectItem("2", "2º Grau"));
    grau.add(new SelectItem("3", "Superior"));
    grau.add(new SelectItem("4", "Pós Graduação"));
    grau.add(new SelectItem("5", "Mestrado"));
    grau.add(new SelectItem("6", "Doutorado"));
    return grau;
 }
  public List<SelectItem> getUf(){
    List<SelectItem> grau = new ArrayList<SelectItem>(2);
    grau.add(new SelectItem("MG", "Minas Gerais"));
    grau.add(new SelectItem("SP", "São Paulo"));
    grau.add(new SelectItem("RJ", "Rio de Janeiro"));
    grau.add(new SelectItem("BH", "Bahia"));
    grau.add(new SelectItem("RG", "Rio Grande do Sul"));
    grau.add(new SelectItem("SC", "Santa Catarina"));
    return grau;
 }

}
