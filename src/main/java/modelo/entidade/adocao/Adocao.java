package modelo.entidade.adocao;

import modelo.entidade.evento.Evento;
import modelo.entidade.contato.Contato;

public class Adocao extends Evento {
    
    private Long idContato; 
    private Contato contato; 

    public Long getIdContato() {
        return idContato;
    }

    public void setIdContato(Long idContato) {
        this.idContato = idContato;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }
}
