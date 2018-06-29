/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Filme;
import java.io.Serializable;
import javax.ejb.Stateful;
import javax.persistence.Query;

/**
 *
 * @author alexv
 */
@Stateful
public class FilmeDAO<TIPO> extends DAOGenerico<Filme> implements Serializable {
    
     public FilmeDAO() {
        super();
        classePersistente = Filme.class;
        ordem = "nome";
        maximoObjetos = 3;
    }
}
