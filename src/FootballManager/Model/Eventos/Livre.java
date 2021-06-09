package FootballManager.Model.Eventos;

import FootballManager.Model.Equipas.Equipa;
import FootballManager.Model.Exceptions.EventoInvalidoException;
import FootballManager.Model.Exceptions.TaticaInvalidaException;
import FootballManager.Model.Players.Avancados;
import FootballManager.Model.Players.Jogador;
import FootballManager.Model.Players.Laterais;

import java.util.Random;


public class Livre extends Ataque{
    private float distancia;
    private Jogador marcador;

    public Livre(float distancia) throws EventoInvalidoException {
        super();
        if(distancia<16.5)throw new EventoInvalidoException("Livre","distancia muito pequena");
        else if(distancia>105)throw new EventoInvalidoException("Livre","distancia muito grande");
        this.distancia=distancia;
    }

    public Livre(float distancia,Jogador marcador) throws EventoInvalidoException {
        super();
        if(distancia<16.5)throw new EventoInvalidoException("Livre","distancia muito pequena");
        else if(distancia>105)throw new EventoInvalidoException("Livre","distancia muito grande");
        this.distancia=distancia;
        this.marcador=marcador.clone();
    }

    public void setMarcador(Jogador marcador){
        this.marcador=marcador.clone();
    }

    public float getDistancia(){
        return distancia;
    }

    public boolean golo(Equipa Atacante, Equipa Defensora) throws TaticaInvalidaException {
        Random r=new Random();
        int boost=0;
        if(marcador instanceof Avancados)boost=marcador.getRemate()/10;
        else if(marcador instanceof Laterais)boost=((Laterais) marcador).getCruzamento()/20;
        boost+=10-Math.abs(distancia-20);
        double chanceGolo=(0.07+boost/2.0+(Atacante.ataque()-Defensora.defesa())/500.0);
        return r.nextDouble()*2 < chanceGolo;
    }
}
