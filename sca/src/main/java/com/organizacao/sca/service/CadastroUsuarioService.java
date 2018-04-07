package com.organizacao.sca.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.organizacao.sca.model.Usuario;
import com.organizacao.sca.repository.Usuarios;
import com.organizacao.sca.util.jpa.Transactional;

public class CadastroUsuarioService implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private Usuarios usuarios;

    @Transactional
    public Usuario salvar(Usuario usuario) throws NegocioException {
        System.out.println("Id de Usuário: "+usuario.getId());
        Usuario usuarioExistente = usuarios.porEmail(usuario.getEmail());
        if (usuarioExistente != null && !usuarioExistente.equals(usuario)) {
            throw new NegocioException("Já existe um usuário com o email informado");
        } else {
            return usuarios.guardar(usuario);
        }
    }
    
    /*@Transactional
    public Usuario salvar(Usuario usuario) {
        System.out.println("Id de Usuário: "+usuario.getId());
        Usuario usuarioExistente = usuarios.porNomeOuEmail(usuario.getNome(), usuario.getEmail());
        if (usuarioExistente != null && !usuarioExistente.equals(usuario) && usuario.getId() == null) {
            throw new NegocioException("Já existe um usuário com o nome e/ou email informado");
        } else {
            return usuarios.guardar(usuario);
        }
    }
    
     @Transactional
    public Usuario salvar(Usuario usuario) {
        System.out.println("Id de Usuário: "+usuario.getId());
        Usuario usuarioExistente = usuarios.porNomeOuEmail(usuario.getNome(), usuario.getEmail());
        Usuario usuarioID = usuarios.porId(usuario.getId());
        if (usuarioExistente != null && !usuarioExistente.equals(usuario)) {
            throw new NegocioException("Já existe um usuário com o nome e/ou email informado");
        } else {
            return usuarios.guardar(usuario);
        }
    }
    */
}