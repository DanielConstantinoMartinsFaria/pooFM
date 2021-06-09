package FootballManager.Model.Equipas.Taticas;

import FootballManager.Model.Equipas.Equipa;
import FootballManager.Model.Eventos.*;
import FootballManager.Model.Exceptions.EventoInvalidoException;
import FootballManager.Model.Exceptions.TaticaInvalidaException;
import FootballManager.Model.Players.*;

import java.util.Map;
import java.util.Random;

public class TresQuatroTres extends Tatica{
    /*
                  |00|
          |01|    |02|    |03|
             |04|      |05|
      |06|                   |07|
           |08|   |09|   |10|

     */

    public TresQuatroTres(){
        super();
    }

    public TresQuatroTres(Tatica t){
        super(t);
    }

    @Override
    public Tatica clone() {
        return new TresQuatroTres(this);
    }

    @Override
    public double ataque(Equipa team) {
        Integer[] titulares = this.getTitulares();
        Map<Integer, Jogador> jogadores = team.getJogadores();
        double atk = 0;
        double fator=0;

        Avancados exEsq=new Avancados(jogadores.get(titulares[10]));
        if(exEsq.isLado())atk+=exEsq.calculaRatingTotal();
        else atk+=exEsq.calculaRatingTotal()*0.95;
        fator++;

        Avancados exDir=new Avancados(jogadores.get(titulares[8]));
        if(exDir.isLado())atk+=exDir.calculaRatingTotal();
        else atk+=exDir.calculaRatingTotal()*0.95;
        fator++;

        Avancados ava=new Avancados(jogadores.get(titulares[9]));
        if(ava.isCentral())atk+=ava.calculaRatingTotal();
        else atk+=ava.calculaRatingTotal()*0.95;
        fator++;


        Medios medEsq=new Medios(jogadores.get(titulares[7]));
        if(medEsq.isLado())atk+=medEsq.calculaRatingTotal()*0.6;
        else atk+=medEsq.calculaRatingTotal()*0.57;
        fator+=0.6;

        Medios medDir=new Medios(jogadores.get(titulares[6]));
        if(medDir.isLado())atk+=medDir.calculaRatingTotal()*0.6;
        else atk+=medDir.calculaRatingTotal()*0.57;
        fator+=0.6;

        Medios medCen1=new Medios(jogadores.get(titulares[4]));
        if(medCen1.isCentral())atk+=medCen1.calculaRatingTotal()*0.2;
        else atk+=medCen1.calculaRatingTotal()*0.19;
        fator+=0.2;

        Medios medCen2=new Medios(jogadores.get(titulares[5]));
        if(medCen2.isCentral())atk+=medCen2.calculaRatingTotal()*0.2;
        else atk+=medCen2.calculaRatingTotal()*0.19;
        fator+=0.2;

        return atk/fator;
    }

    @Override
    public double defesa(Equipa team) {
        Integer[] titulares = this.getTitulares();
        Map<Integer, Jogador> jogadores = team.getJogadores();
        double def = 0;
        double fator=0;


        Defesas defCEsq=new Defesas(jogadores.get(titulares[3]));
        def+=defCEsq.calculaRatingTotal();
        fator++;

        Defesas defCDir=new Defesas(jogadores.get(titulares[3]));
        def+=defCDir.calculaRatingTotal();
        fator++;

        Defesas defCen=new Defesas(jogadores.get(titulares[3]));
        def+=defCen.calculaRatingTotal();
        fator++;

        Medios medEsq=new Medios(jogadores.get(titulares[7]));
        if(medEsq.isLado())def+=medEsq.calculaRatingTotal()*0.4;
        else def+=medEsq.calculaRatingTotal()*0.38;
        fator+=0.4;

        Medios medDir=new Medios(jogadores.get(titulares[6]));
        if(medDir.isLado())def+=medDir.calculaRatingTotal()*0.4;
        else def+=medDir.calculaRatingTotal()*0.38;
        fator+=0.4;

        Medios medCen1=new Medios(jogadores.get(titulares[4]));
        if(medCen1.isCentral())def+=medCen1.calculaRatingTotal()*0.8;
        else def+=medCen1.calculaRatingTotal()*0.76;
        fator+=0.8;

        Medios medCen2=new Medios(jogadores.get(titulares[5]));
        if(medCen2.isCentral())def+=medCen2.calculaRatingTotal()*0.8;
        else def+=medCen2.calculaRatingTotal()*0.76;
        fator+=0.8;

        return def/fator;
    }

    @Override
    public boolean compatible(Jogador j, int pos) {
        switch (pos){
            case 0 ->{
                return (j instanceof GuardaRedes);
            }
            case 1, 2, 3 ->{
                return (j instanceof Defesas)||(j instanceof Laterais)||
                        (j instanceof Medios&&j.isCentral());
            }
            case 4,5 ->{
                return (j instanceof Medios)||(!(j instanceof GuardaRedes)&&j.isCentral());
            }
            case 6,7->{
                return (j instanceof Medios)||(!(j instanceof GuardaRedes)&&j.isLado());
            }
            case 8,10->{
                return (j instanceof Avancados)||(!(j instanceof GuardaRedes)&&j.isLado());
            }
            case 9->{
                return (j instanceof Avancados)||(!(j instanceof GuardaRedes)&&j.isCentral());
            }
            default ->{
                return false;
            }
        }
    }

    @Override
    public double ratioCruzamento() {
        return 0.6;
    }

    @Override
    public int randomPlayer(Ataque evento) throws EventoInvalidoException {
        Random r = new Random();
        int res;
        if(evento instanceof Canto){
            boolean qual=r.nextBoolean();
            if(((Canto) evento).qualLado()){
                if(qual)return this.getTitulares()[6];
                else return this.getTitulares()[8];
            }
            else {
                if(qual)return this.getTitulares()[7];
                else return this.getTitulares()[10];
            }
        }
        else if(evento instanceof Remate){
            res=r.nextInt()%5;
            return this.getTitulares()[res+6];
        }
        else if(evento instanceof Livre){

            if(((Livre) evento).getDistancia()<23.5){
                res=r.nextInt()%3;
                return this.getTitulares()[8+res];
            }
            else {
                res=r.nextInt()%2;
                return this.getTitulares()[6+res];
            }
        }
        else if(evento instanceof Cruzamento){
            res=r.nextInt()%2;
            return this.getTitulares()[6+res];
        }else throw new EventoInvalidoException();
    }

    @Override
    public String nomePosicao(int pos) throws TaticaInvalidaException {
        switch (pos){
            case 0->{return "Guarda-Redes";}
            case 1,2,3->{return "Defesa Central";}
            case 4,5->{return "Medio Centro";}
            case 6->{return "Medio Direito";}
            case 7->{return "Medio Esquerdo";}
            case 8->{return "Extremo Direito";}
            case 9->{return "Avancado Central";}
            case 10->{return "Extremo Esquerdo";}
            default -> {throw new TaticaInvalidaException(); }
        }
    }

    @Override
    public String plantelTatica() {
        Integer[]titulares=this.getTitulares();
        Integer[]suplentes=this.getSuplentes();
        StringBuilder res= new StringBuilder(
                "             |" + String.format("%02d",titulares[0]) + "|\n" +
                "     |" + String.format("%02d",titulares[1]) + "|    |" + String.format("%02d",titulares[2]) + "|    |" + String.format("%02d",titulares[3]) +"|\n"+
                "        |" + String.format("%02d",titulares[4]) + "|      |" + String.format("%02d",titulares[5]) + "|\n "+
                "|" + String.format("%02d",titulares[6]) + "|                   |" + String.format("%02d",titulares[7]) + "|\n"+
                "      |" + String.format("%02d",titulares[8]) + "|   |" + String.format("%02d",titulares[9]) + "|   |" + String.format("%02d",titulares[10]) + "|\n\n");
        for(Integer i:suplentes){
            if(i!=null)res.append("|").append(i).append("|  ");
        }
        return res.toString()+"\n";
    }
}

    /*
                  |00|
          |01|    |02|    |03|
             |04|      |05|
      |06|                   |07|
           |08|   |09|   |10|

     */