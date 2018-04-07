/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.organizacao.sca.service;

import com.organizacao.sca.model.Funcionario;
import com.organizacao.sca.repository.Funcionarios;
import com.organizacao.sca.util.jpa.Transactional;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author Anjo_Grabiel
 */
public class CadastroFuncionarioService implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private Funcionarios funcionarios;

    @Transactional
    public Funcionario salvar(Funcionario funcionario) throws NegocioException {
        Funcionario clienteFuncioanrio = funcionarios.porEmail(funcionario.getEmail());

        if (clienteFuncioanrio != null && !clienteFuncioanrio.equals(funcionario)) {
            throw new NegocioException("Já existe um Funcioanrio com o E-mail Cadastrado.");
        }
        funcionario.setTelefone(funcionario.getTelefone().replaceAll("[^0-9+]", ""));
        funcionario.setCelular(funcionario.getCelular().replaceAll("[^0-9+]", ""));
        funcionario.setCpf(funcionario.getCpf().replaceAll("[ ./-]", ""));
        return funcionarios.guardar(funcionario);
    }
    
}
