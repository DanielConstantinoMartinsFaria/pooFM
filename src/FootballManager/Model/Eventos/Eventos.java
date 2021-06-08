package FootballManager.Model.Eventos;

import FootballManager.Model.Equipas.Equipa;
import FootballManager.Model.Exceptions.EventoInvalidoException;
import FootballManager.Model.Exceptions.JogadorInexistenteException;
import FootballManager.Model.Exceptions.TaticaInvalidaException;

public abstract class Eventos {

    public Eventos(){
    }

    public abstract boolean golo(Equipa Atacante,Equipa Defensora) throws TaticaInvalidaException, EventoInvalidoException, JogadorInexistenteException;
}
