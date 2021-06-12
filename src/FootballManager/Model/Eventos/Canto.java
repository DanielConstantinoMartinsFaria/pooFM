package FootballManager.Model.Eventos;

import FootballManager.Model.Equipas.Equipa;
import FootballManager.Model.Exceptions.TaticaInvalidaException;
import FootballManager.Model.Jogadores.Jogador;
import FootballManager.Model.Jogadores.Laterais;
import FootballManager.Model.Jogadores.Medios;

import java.util.Random;

public class Canto extends Ataque{
    private boolean lado;//False-> esquerda, True-> direita
    private Jogador marcador;

    public Canto(boolean lado) {
        super();
        this.lado=lado;
    }

    public Canto(boolean lado,Jogador marcador) {
        super();
        this.lado=lado;
        this.marcador=marcador.clone();
    }

    public void setMarcador(Jogador marcador){
        this.marcador=marcador.clone();
    }

    public String marcadorName(){
        return marcador.getNome();
    }

    public boolean qualLado(){
        return lado;
    }

    public boolean golo(Equipa Atacante, Equipa Defensora) throws TaticaInvalidaException {
        Random r=new Random();
        int boost=0;
        if(marcador instanceof Laterais)boost=((Laterais) marcador).getCruzamento()/10;
        else if(marcador instanceof Medios)boost=marcador.getPasse()/20;
        double chanceGolo=(0.05+(Atacante.ataque()-Defensora.defesa()+boost)/500.0)+0.05*r.nextGaussian();
        return r.nextDouble() < chanceGolo;
    }
}
