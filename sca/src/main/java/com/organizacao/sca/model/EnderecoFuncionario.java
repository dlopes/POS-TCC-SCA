/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.organizacao.sca.model;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Anjo_Grabiel
 */
@Entity
@DiscriminatorValue("FUNCIONARIO")
public class EnderecoFuncionario extends Endereco implements Serializable{
    private static final long serialVersionUID = 1L;
    private Funcionario funcionario;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
      
}
