/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.organizacao.sca.model;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 *
 * @author Anjo_Grabiel
 */
public class UsuarioValor implements Serializable{
    
    private Usuario usuario;
    private BigDecimal valor = BigDecimal.ZERO;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    
    
}
