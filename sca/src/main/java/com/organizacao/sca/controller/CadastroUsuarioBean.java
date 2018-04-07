/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.organizacao.sca.controller;

import com.organizacao.sca.model.Funcionario;
import com.organizacao.sca.model.Grupo;
import com.organizacao.sca.model.Usuario;
import com.organizacao.sca.repository.Funcionarios;
import com.organizacao.sca.repository.Grupos;
import com.organizacao.sca.repository.Usuarios;
import com.organizacao.sca.service.CadastroUsuarioService;
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
 * @author Anjo_Grabiel
 */
@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Usuario usuario;
    @Inject
    private Usuarios usuarios;
    @Inject
    private CadastroUsuarioService cadastroUsuarioService;
    private List<Grupo> gruposDisponiveis;
    @Inject
    private Grupos grupos;
    private Grupo novoGrupo;
    private Grupo removeGrupo;
    
    @Inject
    private Funcionarios funcionarios;

    public CadastroUsuarioBean() {
        limpar();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    private void limpar() {
        //setUsuario(new Usuario());
        usuario = new Usuario();
    }

    /* public boolean isEditando() {
     return this.usuario.getId() != null;
     }*/
    public boolean isEditando() {
        boolean resultado = false;
        if (this.usuario != null) {
            resultado = this.usuario.getId() != null;
        }
        return resultado;
    }

    /**
     * @return the novoGrupo
     */
    public Grupo getNovoGrupo() {
        return novoGrupo;
    }

    /**
     * @param novoGrupo the novoGrupo to set
     */
    public void setNovoGrupo(Grupo novoGrupo) {
        this.novoGrupo = novoGrupo;
    }

    public List<Grupo> getGruposDisponiveis() {
        return gruposDisponiveis;
    }

    public void setGruposDisponiveis(List<Grupo> gruposDisponiveis) {
        this.gruposDisponiveis = gruposDisponiveis;
    }

    public Grupo getRemoveGrupo() {
        return removeGrupo;
    }

    public void setRemoveGrupo(Grupo removeGrupo) {
        this.removeGrupo = removeGrupo;
    }

    public void salvar() {

        try {
            this.usuario = cadastroUsuarioService.salvar(usuario);
            limpar();
            FacesUtil.addInfoMessage("Usuário cadastrado com sucesso!");
        } catch (NegocioException e) {
            FacesUtil.addInfoMessage(e.getMessage());
        }

    }

    public void adicionarGrupo() {
        if (!this.usuario.getGrupos().contains(novoGrupo)) {
            if (novoGrupo != null) {
                this.usuario.getGrupos().add(novoGrupo);
                System.out.println(novoGrupo.getNome());
            } else {
                FacesUtil.addErrorMessage("Selecione um Grupo!");
            }
        } else {
            FacesUtil.addErrorMessage("Grupo já foi adicionado!");
        }

    }

    public void removerGrupo() { // remove da lista o grupo do usuario
        this.usuario.getGrupos().remove(removeGrupo);
    }

    /*public void inicializar() {
        if (FacesUtil.isNotPostback()) {
            if (this.usuario == null) {
                limpar();
            }
            System.out.println("inicializando o pre-render...");
            gruposDisponiveis = grupos.todos();
        }
    }*/
    public void inicializar() {
        //if (FacesUtil.isNotPostback()) {
        if (this.usuario == null) {
            limpar();
        }
        System.out.println("inicializando o pre-render...");
        gruposDisponiveis = grupos.todos();
    for(int i=0;i<gruposDisponiveis.size();i++){
    System.out.println(gruposDisponiveis.get(i).getNome());  
    }
        //}
            System.out.println("gruposDisponiveis: "+gruposDisponiveis.size());  

    }
    
    public List<Grupo> getRetGrupos() {
        gruposDisponiveis = grupos.todos();

        return gruposDisponiveis;
    }
    
   public List<Funcionario> completarFuncionario(String nome) {
      return this.funcionarios.porNome(nome);
  }
}
