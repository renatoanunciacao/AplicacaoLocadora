/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Autorizacao;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Renato
 */
@Stateful
public class AutorizacaoDAO<TIPO> extends DAOGenerico<Autorizacao> implements Serializable {

    public AutorizacaoDAO() {
        super();
        classePersistente = Autorizacao.class;
        ordem = "apelido";
        maximoObjetos = 3;
    }

}
