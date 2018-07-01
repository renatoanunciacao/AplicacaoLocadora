/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AutorizacaoDAO;
import br.edu.ifsul.dao.FilmeDAO;
import br.edu.ifsul.dao.ItensLocacaoDAO;
import br.edu.ifsul.dao.LocacaoDAO;
import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.modelo.Autorizacao;
import br.edu.ifsul.modelo.Filme;
import br.edu.ifsul.modelo.ItensLocacao;
import br.edu.ifsul.modelo.Locacao;
import br.edu.ifsul.modelo.Pessoa;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author alexv
 */
@Named(value = "controleLocacao")
@ViewScoped
public class ControleLocacao implements Serializable {

    @EJB
    private LocacaoDAO<Locacao> dao;
    private Locacao objeto;
    private Boolean editando;
    @EJB
    private PessoaDAO<Pessoa> daoPessoa;
    private Pessoa pessoa;
    @EJB
    private FilmeDAO<Filme> daoFilme;
    private Filme filme;
    @EJB
    private ItensLocacaoDAO<ItensLocacao> daoItensLocacao;
    private ItensLocacao itensLocacao;
    private Boolean editandoItens;
    private Boolean novoItem;

    public ControleLocacao() {
        editando = false;
    }

    public String listar() {
        setEditando((Boolean) false);
        return "/privado/locacao/listar?faces-redirect=true";
    }

    public void novo() {
        setEditando((Boolean) true);
        setEditandoItens((Boolean) false);
        setObjeto(new Locacao());
    }

    public void alterar(Object id) {
        try {
            setObjeto(getDao().getObjectById(id));
            setFilme(getDaoFilme().getObjectById(id));
            setEditando((Boolean) true);
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: "
                    + Util.getMensagemErro(e));
        }
    }

    public void excluir(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remover(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro a remover objeto: " + Util.getMensagemErro(e));
        }
    }

    public void salvar() {
        try {
            if (objeto.getCodigo() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Sucesso ao persistir objeto");
            editando = false;
            editandoItens = false;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir: " + Util.getMensagemErro(e));
        }
    }

    public void novoFilme() {
        itensLocacao = new ItensLocacao();
        editandoItens = true;
        novoItem = true;
    }

    public void salvarFilme() {
        if (filme.getCodigo() == null) {
            if (novoItem) {
                     objeto.adicionarFilme(itensLocacao);
            }
        }
        editandoItens = false;
        Util.mensagemInformacao("Filme persistido com sucesso!");
    }

    public void alterarFilme(int index) {
        itensLocacao = objeto.getItensLocacao().get(index);
        editandoItens = true;
        novoItem = false;
    }

    public void excluirFilme(int index) {
        objeto.removerFilme(index);
        Util.mensagemInformacao("Filme removido com sucesso!");
    }

    public LocacaoDAO<Locacao> getDao() {
        return dao;
    }

    public void setDao(LocacaoDAO<Locacao> dao) {
        this.dao = dao;
    }

    public Locacao getObjeto() {
        return objeto;
    }

    public void setObjeto(Locacao objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public PessoaDAO<Pessoa> getDaoPessoa() {
        return daoPessoa;
    }

    public void setDaoPessoa(PessoaDAO<Pessoa> daoPessoa) {
        this.daoPessoa = daoPessoa;
    }

    public FilmeDAO<Filme> getDaoFilme() {
        return daoFilme;
    }

    public void setDaoFilme(FilmeDAO<Filme> daoFilme) {
        this.daoFilme = daoFilme;
    }


    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public ItensLocacaoDAO<ItensLocacao> getDaoItensLocacao() {
        return daoItensLocacao;
    }

    public void setDaoItensLocacao(ItensLocacaoDAO<ItensLocacao> daoItensLocacao) {
        this.daoItensLocacao = daoItensLocacao;
    }

    public ItensLocacao getItensLocacao() {
        return itensLocacao;
    }

    public void setItensLocacao(ItensLocacao itensLocacao) {
        this.itensLocacao = itensLocacao;
    }

    public Boolean getEditandoItens() {
        return editandoItens;
    }

    public void setEditandoItens(Boolean editandoItens) {
        this.editandoItens = editandoItens;
    }

    public Boolean getNovoItem() {
        return novoItem;
    }

    public void setNovoItem(Boolean novoItem) {
        this.novoItem = novoItem;
    }

}
