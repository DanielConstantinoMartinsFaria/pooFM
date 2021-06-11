package FootballManager.Model.Equipas.Taticas;

import FootballManager.Model.Equipas.Equipa;
import FootballManager.Model.Eventos.*;
import FootballManager.Model.Exceptions.EventoInvalidoException;
import FootballManager.Model.Exceptions.TaticaInvalidaException;
import FootballManager.Model.Players.*;

import java.util.Map;
import java.util.Random;

public class QuatroTresTres extends Tatica{
    /*
                  |00|
             |01|      |02|
     |03|         |05|         |04|
           |06|          |07|
       |08|                  |09|
                  |10|
     */

    public QuatroTresTres(){
        super();
    }

    public QuatroTresTres(Tatica t){
        super(t);
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
        if(exEsq.isLado()) atk+=exEsq.calculaRatingTotal();
        else atk+=exEsq.calculaRatingTotal()*0.95;
        fator++;

        Avancados exDir=new Avancados(jogadores.get(titulares[8]));
        if(exEsq.isLado())atk+=exDir.calculaRatingTotal();
        else atk+=exDir.calculaRatingTotal()*0.95;
        fator++;

        Avancados ponta=new Avancados(jogadores.get(titulares[10]));
        if(ponta.isCentral())atk+=ponta.calculaRatingTotal();
        else atk+=ponta.calculaRatingTotal()*0.95;
        fator++;

        Medios medEsq=new Medios(jogadores.get(titulares[7]));
        if(exEsq.isLado())atk+=medEsq.calculaRatingTotal()*0.6;
        else atk+=medEsq.calculaRatingTotal()*0.57;
        fator+=0.6;

        Medios medDir=new Medios(jogadores.get(titulares[6]));
        if(medDir.isLado())atk+=medDir.calculaRatingTotal()*0.6;
        else atk+=medDir.calculaRatingTotal()*0.57;
        fator+=0.6;

        Medios medCen=new Medios(jogadores.get(titulares[5]));
        if(medCen.isCentral())atk+=medCen.calculaRatingTotal()*0.4;
        else atk+=medCen.calculaRatingTotal()*0.38;
        fator+=0.4;

        Laterais latEsq=new Laterais(jogadores.get(titulares[4]));
        if(latEsq.isLado())atk+=latEsq.calculaRatingTotal()*0.4;
        else atk+=latEsq.calculaRatingTotal()*0.38;
        fator+=0.4;

        Laterais latDir=new Laterais(jogadores.get(titulares[3]));
        if(latEsq.isLado())atk+=latDir.calculaRatingTotal()*0.4;
        else atk+=latDir.calculaRatingTotal()*0.38;
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
        if(medEsq.isLado())def+=medEsq.calculaRatingTotal()*0.4;
        else def+=medEsq.calculaRatingTotal()*0.38;
        fator+=0.4;

        Medios medDir=new Medios(jogadores.get(titulares[6]));
        if(medDir.isLado())def+=medDir.calculaRatingTotal()*0.4;
        else def+=medDir.calculaRatingTotal()*0.38;
        fator+=0.4;

        Medios medCen=new Medios(jogadores.get(titulares[5]));
        if(medCen.isCentral())def+=medCen.calculaRatingTotal()*0.6;
        else def+=medCen.calculaRatingTotal()*0.57;
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
            case 5 ->{
                return (j instanceof Medios)||(!(j instanceof GuardaRedes)&&j.isCentral());
            }
            case 6,7->{
                return (j instanceof Medios)||(!(j instanceof GuardaRedes)&&j.isLado());
            }
            case 8,9->{
                return (j instanceof Avancados)||(!(j instanceof GuardaRedes)&&j.isLado());
            }
            case 10 ->{
                return (j instanceof Avancados)||(!(j instanceof GuardaRedes)&&j.isCentral());
            }
            default ->{
                return false;
            }
        }
    }

    @Override
    public double ratioCruzamento() {
        return 0.5;
    }

    @Override
    public int randomPlayer(Ataque evento) throws EventoInvalidoException {
        Random r = new Random();
        int res;
        if(evento instanceof Canto){
            res=r.nextInt()%3;
            res=Math.abs(res);
            if(((Canto) evento).qualLado()){
                if(res==0)return 3;
                else if(res==1)return 6;
                else return 8;
            }
            else {
                if(res==0)return 4;
                else if(res==1)return 7;
                else return 9;
            }
        }
        else if(evento instanceof Remate){
            res=r.nextInt()%5;
            res=Math.abs(res);
            return res+6;
        }
        else if(evento instanceof Livre){
            res=r.nextInt()%5;
            res=Math.abs(res);
            return res+6;
        }
        else if(evento instanceof Cruzamento){
            res=r.nextInt()%6;
            res=Math.abs(res);
            if(res<2)return 3+res;
            else return 4+res;
        }else throw new EventoInvalidoException();
    }

    @Override
    public String nomePosicao(int pos) throws TaticaInvalidaException {
        switch (pos){
            case 0->{return "Guarda-Redes";}
            case 1,2->{return "Defesa Central";}
            case 3->{return "Lateral Direito";}
            case 4->{return "Lateral Esquerdo";}
            case 5->{return "Trinco";}
            case 6->{return "Medio Direito";}
            case 7->{return "Medio Esquerdo";}
            case 8->{return "Extremo Direito";}
            case 9->{return "Extremo Esquerdo";}
            case 10->{return "Ponta de Lança";}
            default -> {throw new TaticaInvalidaException(); }
        }
    }

    @Override
    public String plantelTatica() {
        Integer[]titulares=this.getTitulares();
        Integer[]suplentes=this.getSuplentes();
        StringBuilder res= new StringBuilder(
                "             |" + String.format("%02d",titulares[0]) + "|\n" +
                "        |" + String.format("%02d",titulares[1]) + "|      |" + String.format("%02d",titulares[2]) + "|\n" +
                "|" + String.format("%02d",titulares[3]) + "|         |" + String.format("%02d",titulares[5]) + "|         |" + String.format("%02d",titulares[4]) + "|\n" +
                "      |" + String.format("%02d",titulares[6]) + "|          |" + String.format("%02d",titulares[7]) + "|\n" +
                "  |" + String.format("%02d",titulares[8]) + "|                  |" + String.format("%02d",titulares[9]) + "|\n" +
                "            |" + String.format("%02d",titulares[10]) + "|\n");
        for(Integer i:suplentes){
            if(i!=null)res.append("|").append(i).append("|  ");
        }
        return res.toString()+"\n";
    }
    /*
                  |00|
             |01|      |02|
     |03|         |05|         |04|
           |06|          |07|
       |08|                  |09|
                  |10|
     */

}
