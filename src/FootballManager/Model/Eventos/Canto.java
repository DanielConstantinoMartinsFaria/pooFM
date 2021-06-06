package FootballManager.Model.Eventos;

import FootballManager.Model.Equipas.Equipa;
import FootballManager.Model.Players.Jogador;
import FootballManager.Model.Players.Laterais;
import FootballManager.Model.Players.Medios;

import java.util.Random;
import java.util.Set;

public class Canto extends Eventos{
    private boolean lado;//False-> esquerda, True-> direita
    private Jogador marcador;

    public Canto(boolean equipaCasa,boolean lado,Jogador marcador) {
        super(equipaCasa);
        this.lado=lado;
        this.marcador=marcador;
    }

    public boolean golo(Equipa Defensora, Equipa Atacante,Set<Integer> AtkTitulares, Set<Integer>DefTitulares){
        Random r=new Random();
        int boost=0;
        if(marcador instanceof Laterais)boost=((Laterais) marcador).getCruzamento()/10;
        else if(marcador instanceof Medios)boost=marcador.getPasse()/20;
        double chanceGolo=(0.05+(Atacante.ataque(AtkTitulares)-Defensora.defesa(DefTitulares)+boost)/500.0);
        return r.nextDouble() < chanceGolo;
    }
}
