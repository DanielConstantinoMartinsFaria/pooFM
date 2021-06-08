package FootballManager.Model.Equipas.Taticas;

import FootballManager.Model.Equipas.Equipa;
import FootballManager.Model.Eventos.*;
import FootballManager.Model.Exceptions.EventoInvalidoException;
import FootballManager.Model.Exceptions.TaticaInvalidaException;
import FootballManager.Model.Players.*;

import java.util.Map;
import java.util.Random;

public class QuatroQuatroDois extends Tatica{

    /*

                  |00|

             |01|      |02|
     |03|                      |04|
             |05|      |06|
      |07|                    |08|

            |09|       |10|
     */

    public QuatroQuatroDois(){
        super();
    }

    public QuatroQuatroDois(Tatica t){
        super(t);
    }

    @Override
    public Tatica clone() {
        return new QuatroQuatroDois(this);
    }

    @Override
    public double ataque(Equipa team) {
        Integer[] titulares = this.getTitulares();
        Map<Integer, Jogador> jogadores = team.getJogadores();
        double atk = 0;
        double fator=0;

        Avancados exEsq=new Avancados(jogadores.get(titulares[10]));
        atk+=exEsq.calculaRatingTotal();
        fator++;

        Avancados exDir=new Avancados(jogadores.get(titulares[9]));
        atk+=exDir.calculaRatingTotal();
        fator++;

        Medios medEsq=new Medios(jogadores.get(titulares[8]));
        if(medEsq.isLado())atk+=medEsq.calculaRatingTotal()*0.6;
        else atk+=medEsq.calculaRatingTotal()*0.57;
        fator+=0.6;

        Medios medDir=new Medios(jogadores.get(titulares[7]));
        if(medDir.isLado())atk+=medDir.calculaRatingTotal()*0.6;
        else atk+=medDir.calculaRatingTotal()*0.57;
        fator+=0.6;

        Medios medCen1=new Medios(jogadores.get(titulares[5]));
        if(medCen1.isCentral())atk+=medCen1.calculaRatingTotal()*0.4;
        else atk+=medCen1.calculaRatingTotal()*0.38;
        fator+=0.4;

        Medios medCen2=new Medios(jogadores.get(titulares[6]));
        if(medCen2.isCentral())atk+=medCen2.calculaRatingTotal()*0.4;
        else atk+=medCen2.calculaRatingTotal()*0.38;
        fator+=0.4;

        Laterais latEsq=new Laterais(jogadores.get(titulares[4]));
        if(latEsq.isLado())atk+=latEsq.calculaRatingTotal()*0.4;
        else atk+=latEsq.calculaRatingTotal()*0.38;
        fator+=0.4;

        Laterais latDir=new Laterais(jogadores.get(titulares[3]));
        if(latDir.isLado())atk+=latDir.calculaRatingTotal()*0.4;
        else atk+=latDir.calculaRatingTotal()*0.38;
        fator+=0.4;

        return atk/fator;
    }

    @Override
    public double defesa(Equipa team) {
        Integer[] titulares = this.getTitulares();
        Map<Integer, Jogador> jogadores = team.getJogadores();
        double def = 0;
        double fator=0;

        Defesas defEsq=new Defesas(jogadores.get(titulares[2]));
        if(defEsq.isCentral()) def+=defEsq.calculaRatingTotal();
        else def+=defEsq.calculaRatingTotal()*0.95;
        fator++;

        Defesas defDir=new Defesas(jogadores.get(titulares[1]));
        if(defDir.isCentral())def+=defDir.calculaRatingTotal();
        else def+=defDir.calculaRatingTotal()*0.95;
        fator++;

        GuardaRedes guardaRedes=new GuardaRedes(jogadores.get(titulares[0]));
        def+=guardaRedes.calculaRatingTotal();
        fator++;

        Medios medEsq=new Medios(jogadores.get(titulares[8]));
        if(medEsq.isLado())def+=medEsq.calculaRatingTotal()*0.4;
        else def+=medEsq.calculaRatingTotal()*0.38;
        fator+=0.4;

        Medios medDir=new Medios(jogadores.get(titulares[7]));
        if(medDir.isLado())def+=medDir.calculaRatingTotal()*0.4;
        else def+=medDir.calculaRatingTotal()*0.38;
        fator+=0.4;

        Medios medCen2=new Medios(jogadores.get(titulares[5]));
        if(medCen2.isCentral())def+=medCen2.calculaRatingTotal()*0.6;
        else def+=medCen2.calculaRatingTotal()*0.57;
        fator+=0.6;

        Medios medCen1=new Medios(jogadores.get(titulares[5]));
        if(medCen1.isCentral())def+=medCen1.calculaRatingTotal()*0.6;
        else def+=medCen1.calculaRatingTotal()*0.57;
        fator+=0.6;

        Laterais latEsq=new Laterais(jogadores.get(titulares[4]));
        if(latEsq.isLado())def+=latEsq.calculaRatingTotal()*0.6;
        else def+=latEsq.calculaRatingTotal()*0.57;
        fator+=0.6;

        Laterais latDir=new Laterais(jogadores.get(titulares[3]));
        if(latDir.isLado())def+=latDir.calculaRatingTotal()*0.6;
        else def+=latDir.calculaRatingTotal()*0.57;
        fator+=0.6;

        return def/fator;
    }

    @Override
    public boolean compatible(Jogador j, int pos) {
        switch (pos){
            case 0 ->{
                return (j instanceof GuardaRedes);
            }
            case 1, 2 ->{
                return (j instanceof Defesas)||(j instanceof Laterais)||
                        (j instanceof Medios&&j.isCentral());
            }
            case 3,4 ->{
                return (j instanceof Defesas)||(j instanceof Laterais)||
                        (j instanceof Medios&&j.isLado())||(j instanceof Avancados&&j.isLado());
            }
            case 5,6 ->{
                return (j instanceof Medios)||(!(j instanceof GuardaRedes)&&j.isCentral());
            }
            case 7,8->{
                return (j instanceof Medios)||(!(j instanceof GuardaRedes)&&j.isLado());
            }
            case 9,10->{
                return (!(j instanceof GuardaRedes));
            }
            default ->{
                return false;
            }
        }
    }

    @Override
    public double ratioCruzamento(){
        return 0.67;
    }

    @Override
    public int randomPlayer(Eventos evento) throws EventoInvalidoException {
        Random r = new Random();
        int res;
        if(evento instanceof Canto){
            res=r.nextInt()%3;
            if(((Canto) evento).qualLado()){
                if(res==0)return 3;
                else if(res==1)return 5;
                else return 7;
            }
            else {
                if(res==0)return 4;
                else if(res==1)return 6;
                else return 8;
            }
        }
        else if(evento instanceof Remate){
            res=r.nextInt()%4;
            return res+7;
        }
        else if(evento instanceof Livre){
            res=r.nextInt()%2;
            if(((Livre) evento).getDistancia()<23.5){
                return 9+res;
            }
            else {
                return 7+res;
            }
        }
        else if(evento instanceof Cruzamento){
            res=r.nextInt()%4;
            if(res<2)return 3+res;
            else return 5+res;
        }else throw new EventoInvalidoException();
    }

    @Override
    public String nomePosicao(int pos) throws TaticaInvalidaException {
        switch (pos){
            case 0->{return "Guarda-Redes";}
            case 1,2->{return "Defesa Central";}
            case 3->{return "Lateral Direito";}
            case 4->{return "Lateral Esquerdo";}
            case 5,6->{return "Medio Centro";}
            case 7->{return "Medio Direito";}
            case 8->{return "Medio Esquerdo";}
            case 9->{return "Extremo Direito";}
            case 10->{return "Extremo Esquerdo";}
            default -> {throw new TaticaInvalidaException(); }
        }
    }

}
