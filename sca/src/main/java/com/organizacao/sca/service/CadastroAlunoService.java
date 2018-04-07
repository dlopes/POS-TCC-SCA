/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.organizacao.sca.service;

import com.organizacao.sca.model.Aluno;
import com.organizacao.sca.repository.Alunos;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.inject.Inject;
import com.organizacao.sca.util.jpa.Transactional;

/**
 *
 * @author Dilson
 */
public class CadastroAlunoService implements Serializable {
    private static final long serialVersionUID = 1L;
    @Inject
    private Alunos alunos;
    
    @Transactional
    public Aluno salvar(Aluno aluno) throws NegocioException {
        Aluno clienteAluno = alunos.porEmail(aluno.getEmail());

        if (clienteAluno != null && !clienteAluno.equals(aluno)) {
            throw new NegocioException("Já existe um Aluno com o E-mail Cadastrado.");
        }
        String renda = aluno.getRenda().toString();
        aluno.setTelefone(aluno.getTelefone().replaceAll("[^0-9+]", ""));
        aluno.setCelular(aluno.getCelular().replaceAll("[^0-9+]", ""));
        aluno.setRg(aluno.getRg().replaceAll("[ ./-]", ""));
        renda = renda.replaceAll("[ ./-]","");
        renda = renda.replaceAll("[ ,/-]", ".");
        BigDecimal big = new BigDecimal(renda);
        aluno.setRenda(big);
        System.out.println(big);

        return alunos.guardar(aluno);
    }
    
}
