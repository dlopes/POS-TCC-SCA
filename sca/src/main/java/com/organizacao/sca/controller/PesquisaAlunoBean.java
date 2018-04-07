package com.organizacao.sca.controller;


import java.io.Serializable;
import java.util.List;

//import javax.faces.bean.ViewScoped;
import javax.faces.view.ViewScoped;//Bean cdi
import javax.inject.Inject;
import javax.inject.Named;
import com.organizacao.sca.model.Aluno;
import com.organizacao.sca.repository.Alunos;
import com.organizacao.sca.repository.filter.AlunoFilter;
import com.organizacao.sca.service.NegocioException;
import com.organizacao.sca.util.jsf.FacesUtil;
import java.util.Objects;

@Named
@ViewScoped
public class PesquisaAlunoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Inject
    private Alunos alunos;
    private AlunoFilter filtro;
    private List<Aluno>  alunosFiltrados;
    private Aluno alunoSelecionado;

    public PesquisaAlunoBean() {
        filtro = new AlunoFilter();
    }

    public void excluir() {
        try {
            alunos.remover(alunoSelecionado);
            alunosFiltrados.remove(alunoSelecionado);

            FacesUtil.addInfoMessage("Aluno " + alunoSelecionado.getNome()
                    + " excluído com sucesso.");
        } catch (NegocioException e) {
            FacesUtil.addInfoMessage(e.getMessage());
        }
    }

    public void pesquisar() {
        alunosFiltrados = alunos.filtrados(filtro);
        System.out.println(alunosFiltrados.isEmpty());
    }

    public AlunoFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(AlunoFilter filtro) {
        this.filtro = filtro;
    }

    public List<Aluno> getAlunosFiltrados() {
        return alunosFiltrados;
    }

    public void setAlunosFiltrados(List<Aluno> alunosFiltrados) {
        this.alunosFiltrados = alunosFiltrados;
    }

    public Aluno getAlunoSelecionado() {
        return alunoSelecionado;
    }

    public void setAlunoSelecionado(Aluno alunoSelecionado) {
        this.alunoSelecionado = alunoSelecionado;
    }

    
    
    
    
    

}
