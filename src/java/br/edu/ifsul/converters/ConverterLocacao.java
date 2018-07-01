package br.edu.ifsul.converters;

import br.edu.ifsul.modelo.Locacao;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Renato
 */
@FacesConverter(value = "converterLocacao")
public class ConverterLocacao implements Serializable, Converter {

    @PersistenceContext(unitName = "APLICACAOLOCADORA-WEBPU")
    private EntityManager em;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")) {
            return null;
        }
        return em.find(Locacao.class, string);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null) {
            return null;
        }
        Locacao obj = (Locacao) o;
        return obj.getCodigo().toString();
    }

}
