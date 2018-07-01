/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Locacao;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Renato
 */
@Stateful
public class LocacaoDAO<TIPO> extends DAOGenerico<Locacao> implements Serializable {

    public LocacaoDAO() {
        super();
        classePersistente = Locacao.class;
        ordem = "codigo";
        maximoObjetos = 3;
    }

    @Override
    public Locacao getObjectById(Object id) throws Exception {
        Locacao obj = em.find(Locacao.class, id);

        obj.getItensLocacao().size();
        return obj;
    }

}
