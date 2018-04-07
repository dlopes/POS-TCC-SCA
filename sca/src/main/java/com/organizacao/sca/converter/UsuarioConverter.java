/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.organizacao.sca.converter;

import com.organizacao.sca.model.Usuario;
import com.organizacao.sca.repository.Usuarios;
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
@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {
    
    @Inject
    private Usuarios usuarios;
    
    /*public UsuarioConverter () {
        usuarios = CDIServiceLocator.getBean(Usuarios.class);
    }*/

    /*@Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        Usuario retorno = null;
        if (value != null) {
            Long id = new Long(value);
            retorno = usuarios.porId(id);
        }
        
        return retorno;
    }*/
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
            Usuario retorno = null;

            if (StringUtils.isNotEmpty(value)) {
                    retorno = this.usuarios.porId(new Long(value));
            }

            return retorno;
    }
    

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        if(value != null) {
            //return ((Usuario) value).getId().toString();           
            Usuario usuario = (Usuario) value;
            return usuario.getId() == null ? null : usuario.getId().toString();
        }
        return "";
    }
    
}
