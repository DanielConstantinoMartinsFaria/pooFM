package FootballManager.Model.Eventos;

import FootballManager.Model.Equipas.Equipa;
import FootballManager.Model.Exceptions.TaticaInvalidaException;
import FootballManager.Model.Players.Jogador;
import FootballManager.Model.Players.Laterais;

import java.util.Random;

public class Cruzamento extends Ataque{
    private Jogador marcador;

    public Cruzamento() {
        super();
    }

    public Cruzamento(Jogador marcador) {
        super();
        this.marcador=marcador.clone();
    }

    public void setMarcador(Jogador marcador){
        this.marcador=marcador.clone();
    }

    public String marcadorName(){
        return marcador.getNome();
    }

    public boolean golo(Equipa Atacante, Equipa Defensora) throws TaticaInvalidaException {
        Random r = new Random();
        int boost;
        if(marcador instanceof Laterais)boost= ((Laterais) marcador).getCruzamento();
        else boost = ((Jogador) marcador).getPasse()/2;
        double chance=0.05+((Atacante.ataque()-Defensora.defesa()+boost)/500.0)+0.05*r.nextGaussian();
        return r.nextDouble() < chance;
    }
}
