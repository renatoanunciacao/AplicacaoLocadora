package br.edu.ifsul.controle;


import br.edu.ifsul.dao.FilmeDAO;
import br.edu.ifsul.dao.LocacaoDAO;
import br.edu.ifsul.modelo.Filme;
import br.edu.ifsul.modelo.Locacao;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author alexv
 */
@Named(value = "controleFilme")
@ViewScoped
public class ControleFilme implements Serializable {

    @EJB
    private FilmeDAO<Filme> dao;
    private Filme objeto;
    private Boolean editando;
    @EJB
    private LocacaoDAO<Locacao> daoLocacao;
    
     public ControleFilme(){
        editando = false;
    }
    
    public String listar(){
        editando = false;
        return "/privado/filme/listar?faces-redirect=true";
    }
    
    public void novo(){
        editando = true;
        setObjeto(new Filme());
    }
    
    public void alterar(Object id){
        try {
            objeto = dao.getObjectById(id);
            editando = true;
        } catch (Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: " + 
                    Util.getMensagemErro(e));
        } 
    }
    
    public void excluir(Object id){
        try {
            setObjeto(getDao().getObjectById(id));
            getDao().remover(getObjeto());
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e){
            Util.mensagemErro("Erro ao remover objeto: " + 
                    Util.getMensagemErro(e));
        }
    }
    
    public void salvar(){
        try {
            if (getObjeto().getCodigo()== null){
                getDao().persist(getObjeto());
            } else {
                getDao().merge(getObjeto());
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
            setEditando((Boolean) false);
        } catch(Exception e){
            Util.mensagemErro("Erro ao persistir objeto: " + 
                    Util.getMensagemErro(e));
        }
    }
    
    public FilmeDAO<Filme> getDao() {
        return dao;
    }

    public void setDao(FilmeDAO<Filme> dao) {
        this.dao = dao;
    }

    public Filme getObjeto() {
        return objeto;
    }

    public void setObjeto(Filme objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public LocacaoDAO<Locacao> getDaoLocacao() {
        return daoLocacao;
    }

    public void setDaoLocacao(LocacaoDAO<Locacao> daoLocacao) {
        this.daoLocacao = daoLocacao;
    }
   
}
