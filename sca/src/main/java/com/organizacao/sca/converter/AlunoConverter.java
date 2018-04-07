/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.organizacao.sca.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.organizacao.sca.model.Aluno;
import com.organizacao.sca.repository.Alunos;

/**
 *
 * @author Dilson
 */
@FacesConverter(forClass = Aluno.class)
public class AlunoConverter implements Converter {

    @Inject
    private Alunos alunos;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Aluno retorno = null;

        if (StringUtils.isNotEmpty(value)) {
            retorno = this.alunos.porId(new Long(value));
        }

        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value != null) {
            Aluno aluno = (Aluno) value;
            return aluno.getId() == null ? null : aluno.getId().toString();
        }
        return "";
    }

}
