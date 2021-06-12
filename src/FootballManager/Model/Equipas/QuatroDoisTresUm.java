package FootballManager.Model.Equipas;

import FootballManager.Model.Eventos.*;
import FootballManager.Model.Exceptions.EventoInvalidoException;
import FootballManager.Model.Exceptions.TaticaInvalidaException;
import FootballManager.Model.Jogadores.*;

import java.util.Map;
import java.util.Random;

public class QuatroDoisTresUm extends Tatica{
    public QuatroDoisTresUm(){
        super();
    }

    public QuatroDoisTresUm(Tatica t){
        super(t);
    }

    @Override
    public Tatica clone() {
        return new QuatroDoisTresUm(this);
    }

    @Override
    public double ataque(Equipa team) {
        Integer[] titulares = this.getTitulares();
        Map<Integer, Jogador> jogadores = team.getJogadores();
        double atk = 0;
        double fator=0;

        Avancados ponta=new Avancados(jogadores.get(titulares[10]));
        atk+=ponta.calculaRatingTotal();
        fator++;


        Medios medEsq=new Medios(jogadores.get(titulares[9]));
        if(medEsq.isLado())atk+=medEsq.calculaRatingTotal()*0.8;
        else atk+=medEsq.calculaRatingTotal()*0.76;
        fator+=0.8;

        Medios medAtk=new Medios(jogadores.get(titulares[8]));
        if(medEsq.isCentral())atk+=medAtk.calculaRatingTotal();
        else atk+=medAtk.calculaRatingTotal()*0.95;
        fator++;

        Medios medDir=new Medios(jogadores.get(titulares[7]));
        if(medDir.isLado())atk+=medDir.calculaRatingTotal()*0.8;
        else atk+=medDir.calculaRatingTotal()*0.76;
        fator+=0.8;

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

        Medios medEsq=new Medios(jogadores.get(titulares[9]));
        if(medEsq.isLado())def+=medEsq.calculaRatingTotal()*0.4;
        else def+=medEsq.calculaRatingTotal()*0.19;
        fator+=0.2;

        Medios medDir=new Medios(jogadores.get(titulares[7]));
        if(medDir.isLado())def+=medDir.calculaRatingTotal()*0.2;
        else def+=medDir.calculaRatingTotal()*0.19;
        fator+=0.2;

        Medios medCen2=new Medios(jogadores.get(titulares[5]));
        if(medCen2.isCentral())def+=medCen2.calculaRatingTotal()*0.6;
        else def+=medCen2.calculaRatingTotal()*0.57;
        fator+=0.6;

        Medios medCen1=new Medios(jogadores.get(titulares[6]));
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
            case 7,9->{
                return (j instanceof Medios)||(!(j instanceof GuardaRedes)&&j.isLado());
            }
            case 8,10->{
                return ((j instanceof Avancados))||(!(j instanceof GuardaRedes)&&j.isCentral());
            }
            default ->{
                return false;
            }
        }
    }

    @Override
    public double ratioCruzamento() {
        return 0.4;
    }

    @Override
    public int randomPlayer(Ataque evento) throws EventoInvalidoException {
        Random r = new Random();
        int res;
        if(evento instanceof Canto){
            res=r.nextInt()%3;
            res=Math.abs(res);
            if(((Canto) evento).qualLado()){
                if(res==0)return this.getTitulares()[3];
                else if(res==1)return this.getTitulares()[5];
                else return this.getTitulares()[7];
            }
            else {
                if(res==0)return this.getTitulares()[4];
                else if(res==1)return this.getTitulares()[6];
                else return this.getTitulares()[9];
            }
        }
        else if(evento instanceof Remate){
            res=r.nextInt()%4;
            res=Math.abs(res);
            return this.getTitulares()[res+7];
        }
        else if(evento instanceof Livre){
            if(((Livre) evento).getDistancia()<23.5){
                boolean rand=r.nextBoolean();
                if(rand){
                    return this.getTitulares()[8];
                }
                else{
                    return this.getTitulares()[10];
                }
            }
            else {
                res=r.nextInt()%5;
                res=Math.abs(res);
                return this.getTitulares()[5+res];
            }
        }
        else if(evento instanceof Cruzamento){
            res=r.nextInt()%5;
            res=Math.abs(res);
            if(res<2)return this.getTitulares()[3+res];
            else return this.getTitulares()[5+res];
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
            case 8->{return "Medio Ofensivo";}
            case 9->{return "Medio Esquerdo";}
            case 10->{return "Ponta de Lanca";}
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
                "|" + String.format("%02d",titulares[3]) + "|                      |" + String.format("%02d",titulares[4]) + "|\n" +
                "        |" + String.format("%02d",titulares[5]) + "|      |" + String.format("%02d",titulares[6]) + "|\n" +
                "  |" + String.format("%02d",titulares[7]) + "|       |" + String.format("%02d",titulares[8]) +
                "|       |" + String.format("%02d",titulares[9]) + "|\n             |" + String.format("%02d",titulares[10]) + "|\n\n");
        for(Integer i:suplentes){
            if(i!=null)res.append("|").append(String.format("%02d",i)).append("|  ");
        }
        return res.toString()+"\n";
    }

    /*
                  |00|
             |01|      |02|
     |03|                      |04|
             |05|      |06|
       |07|       |08|       |09|
                  |10|
    */
}
