/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.organizacao.sca.repository;

import com.organizacao.sca.model.Funcionario;
import com.organizacao.sca.repository.Funcionarios;
import com.organizacao.sca.util.cdi.CDIServiceLocator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Anjo_Grabiel
 */
@FacesConverter(forClass = Funcionario.class)
public class FuncionarioConverter implements Converter {

    @Inject
    private Funcionarios funcionarios;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Funcionario retorno = null;

        if (StringUtils.isNotEmpty(value)) {
            retorno = this.funcionarios.porId(new Long(value));
        }

        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value != null) {
            Funcionario funcionario = (Funcionario) value;
            return funcionario.getId() == null ? null : funcionario.getId().toString();
        }
        return "";
    }

}
