/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.ItensLocacao;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Renato
 */
@Stateful
public class ItensLocacaoDAO<TIPO> extends DAOGenerico<ItensLocacao> implements Serializable {

    public ItensLocacaoDAO() {
        super();
        classePersistente = ItensLocacao.class;
        ordem = "codigo";
        maximoObjetos = 3;
    }

}
