package FootballManager.Model.Eventos;

import FootballManager.Model.Equipas.Equipa;
import FootballManager.Model.Exceptions.EventoInvalidoException;
import FootballManager.Model.Players.Avancados;
import FootballManager.Model.Players.Jogador;
import FootballManager.Model.Players.Laterais;

import java.util.Random;
import java.util.Set;

public class Livre extends Eventos{
    private float distancia;
    public Jogador marcador;

    public Livre(boolean equipaCasa,float distancia,Jogador marcador) throws EventoInvalidoException {
        super(equipaCasa);
        if(distancia<16.5)throw new EventoInvalidoException("Livre","distancia muito pequena");
        else if(distancia>100)throw new EventoInvalidoException("Livre","distancia muito grande");
        this.distancia=distancia;
        this.marcador=marcador.clone();
    }

    public boolean golo(Equipa Defensora, Equipa Atacante, Set<Integer> AtkTitulares, Set<Integer>DefTitulares){
        Random r=new Random();
        int boost=0;
        if(marcador instanceof Avancados)boost=marcador.getRemate()/10;
        else if(marcador instanceof Laterais)boost=((Laterais) marcador).getCruzamento()/20;
        if(Math.abs(10-Math.abs(distancia-20))<=10)boost+=10-Math.abs(distancia-20);
        double chanceGolo=(0.10+(Atacante.ataque(AtkTitulares)-Defensora.defesa(DefTitulares)+boost)/500.0);
        return r.nextDouble() < chanceGolo;
    }
}
