package com.organizacao.sca.controller;

import com.organizacao.sca.model.Usuario;
import com.organizacao.sca.repository.Usuarios;
import com.organizacao.sca.repository.filter.UsuarioFilter;
import com.organizacao.sca.service.NegocioException;
import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.organizacao.sca.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaUsuariosBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private Usuarios usuarios;
    
    private UsuarioFilter filtro;
    private List<Usuario> usuariosFiltrados;
    private Usuario usuarioSelecionado;
    
     public PesquisaUsuariosBean() {
        filtro = new UsuarioFilter();
    }

    public void pesquisar() {
        usuariosFiltrados = usuarios.filtrados(filtro);
        System.out.println("oi");
    }
    
      public void excluir() {
        try {
            System.out.println("oi");
            usuarios.remover(usuarioSelecionado);
            usuariosFiltrados.remove(usuarioSelecionado);

            FacesUtil.addInfoMessage("Produto " + usuarioSelecionado.getNome()
                    + " excluído com sucesso.");
        } catch (NegocioException e) {
            FacesUtil.addInfoMessage(e.getMessage());
        }
    }

    public UsuarioFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(UsuarioFilter filtro) {
        this.filtro = filtro;
    }

    public List<Usuario> getUsuariosFiltrados() {
        return usuariosFiltrados;
    }

    public void setUsuariosFiltrados(List<Usuario> usuariosFiltrados) {
        this.usuariosFiltrados = usuariosFiltrados;
    }

    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }

   
}