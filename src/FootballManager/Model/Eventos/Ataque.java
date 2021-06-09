package FootballManager.Model.Eventos;

import FootballManager.Model.Equipas.Equipa;
import FootballManager.Model.Exceptions.EventoInvalidoException;
import FootballManager.Model.Exceptions.JogadorInvalidoException;
import FootballManager.Model.Exceptions.TaticaInvalidaException;

import java.util.Random;

public class Ataque {
    private String acontecimento;
    public Ataque(){
        super();
        acontecimento="";
    }

    public String getAcontecimento(){
        return acontecimento;
    }

    public boolean golo(Equipa Atacante, Equipa Defensora) throws TaticaInvalidaException {
        Random r=new Random();
        double chance = 0.05+((Atacante.ataque()-Defensora.defesa())/100.0);
        try{
            if(r.nextDouble()<chance){
                if(r.nextDouble()<Atacante.chanceCruzamento()){
                    Cruzamento cruzamento = new Cruzamento();
                    cruzamento.setMarcador(Atacante.getJogador(Atacante.randomPlayer(cruzamento)));
                    acontecimento="Cruzamento";
                    return cruzamento.golo(Atacante, Defensora);
                }else{
                    Remate remate = new Remate();
                    remate.setMarcador(Atacante.getJogador(Atacante.randomPlayer(remate)));
                    acontecimento="Grande Oportunidade";
                    return remate.golo(Atacante,Defensora);
                }
            }
            else{
                if(r.nextDouble()<0.15){
                    if(r.nextBoolean()){
                        Canto canto = new Canto(r.nextBoolean());
                        canto.setMarcador(Atacante.getJogador(Atacante.randomPlayer(canto)));
                        acontecimento="Canto";
                        return canto.golo(Atacante,Defensora);
                    }
                    else {
                        double distancia=25+25*r.nextGaussian();
                        if(distancia<16.5)distancia=20+3.5*r.nextGaussian();
                        if(distancia<16.5)distancia=16.5;
                        Livre livre = new Livre((float) distancia);
                        livre.setMarcador(Atacante.getJogador(Atacante.randomPlayer(livre)));
                        acontecimento="Livre";
                        return livre.golo(Atacante,Defensora);
                    }
                }
                else{
                    if(r.nextDouble()<80)acontecimento="Tentativa de ataque";
                    else acontecimento="Boa Tentativa de ataque";
                    return false;
                }
            }
        } catch (EventoInvalidoException | JogadorInvalidoException e) {
            e.printStackTrace();
            return false;
        }
    }
}
