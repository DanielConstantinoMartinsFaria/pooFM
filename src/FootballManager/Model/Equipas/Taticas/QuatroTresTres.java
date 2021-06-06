package FootballManager.Model.Equipas.Taticas;

import FootballManager.Model.Equipas.Equipa;
import FootballManager.Model.Players.*;

import java.util.Map;

public class QuatroTresTres extends Tatica{
    /*

                  |00|

             |01|      |02|
     |03|                      |04|
                  |05|
           |06|          |07|

       |08|                  |09|
                  |10|

     */

    public QuatroTresTres(){
        super();
    }

    public QuatroTresTres(QuatroTresTres qtt){
        super(qtt);
    }

    public Tatica clone() {
        return new QuatroTresTres(this);
    }

    public double ataque(Equipa team) {
        Integer[] titulares = this.getTitulares();
        Map<Integer, Jogador> jogadores = team.getJogadores();
        double atk = 0;
        double fator=0;

        Avancados exEsq=new Avancados(jogadores.get(titulares[9]));
        if(exEsq.isCentral()) atk+=exEsq.calculaRatingTotal()*0.95;
        else atk+=exEsq.calculaRatingTotal();
        fator++;

        Avancados exDir=new Avancados(jogadores.get(titulares[8]));
        if(exDir.isCentral())atk+=exDir.calculaRatingTotal()*0.95;
        else atk+=exDir.calculaRatingTotal();
        fator++;

        Avancados ponta=new Avancados(jogadores.get(titulares[10]));
        if(ponta.isCentral())atk+=ponta.calculaRatingTotal();
        else atk+=ponta.calculaRatingTotal()*0.95;
        fator++;

        Medios medEsq=new Medios(jogadores.get(titulares[7]));
        if(medEsq.isCentral())atk+=medEsq.calculaRatingTotal()*0.95*0.6;
        else atk+=medEsq.calculaRatingTotal()*0.6;
        fator+=0.6;

        Medios medDir=new Medios(jogadores.get(titulares[6]));
        if(medDir.isCentral())atk+=medDir.calculaRatingTotal()*0.95*0.6;
        else atk+=medDir.calculaRatingTotal()*0.6;
        fator+=0.6;

        Medios medCen=new Medios(jogadores.get(titulares[5]));
        if(medCen.isCentral())atk+=medCen.calculaRatingTotal()*0.4;
        else atk+=medCen.calculaRatingTotal()*0.95*0.4;
        fator+=0.4;

        Laterais latEsq=new Laterais(jogadores.get(titulares[4]));
        if(latEsq.isCentral())atk+=latEsq.calculaRatingTotal()*0.95*0.4;
        else atk+=latEsq.calculaRatingTotal()*0.4;
        fator+=0.4;

        Laterais latDir=new Laterais(jogadores.get(titulares[3]));
        if(latDir.isCentral())atk+=latDir.calculaRatingTotal()*0.95*0.4;
        else atk+=latDir.calculaRatingTotal()*0.4;
        fator+=0.4;

        return atk/fator;
    }

    public double defesa(Equipa team){
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
        if(guardaRedes.isCentral())def+=guardaRedes.calculaRatingTotal();
        else def+=guardaRedes.calculaRatingTotal()*0.95;
        fator++;

        Medios medEsq=new Medios(jogadores.get(titulares[7]));
        if(medEsq.isCentral())def+=medEsq.calculaRatingTotal()*0.95*0.4;
        else def+=medEsq.calculaRatingTotal()*0.4;
        fator+=0.4;

        Medios medDir=new Medios(jogadores.get(titulares[6]));
        if(medDir.isCentral())def+=medDir.calculaRatingTotal()*0.95*0.4;
        else def+=medDir.calculaRatingTotal()*0.4;
        fator+=0.4;

        Medios medCen=new Medios(jogadores.get(titulares[5]));
        if(medCen.isCentral())def+=medCen.calculaRatingTotal()*0.6;
        else def+=medCen.calculaRatingTotal()*0.95*0.6;
        fator+=0.6;

        Laterais latEsq=new Laterais(jogadores.get(titulares[4]));
        if(latEsq.isCentral())def+=latEsq.calculaRatingTotal()*0.95*0.6;
        else def+=latEsq.calculaRatingTotal()*0.6;
        fator+=0.6;

        Laterais latDir=new Laterais(jogadores.get(titulares[3]));
        if(latDir.isCentral())def+=latDir.calculaRatingTotal()*0.95*0.6;
        else def+=latDir.calculaRatingTotal()*0.6;
        fator+=0.6;

        return def/fator;
    }

}
