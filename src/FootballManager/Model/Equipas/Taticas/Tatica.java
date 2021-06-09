package FootballManager.Model.Equipas.Taticas;


import FootballManager.Model.Equipas.Equipa;
import FootballManager.Model.Eventos.Ataque;
import FootballManager.Model.Exceptions.EventoInvalidoException;
import FootballManager.Model.Exceptions.JogadorInexistenteException;
import FootballManager.Model.Exceptions.TaticaInvalidaException;
import FootballManager.Model.Players.*;

import java.util.Set;

public abstract class Tatica {
    private Integer[] titulares;
    private Integer[] suplentes;

    public Tatica(){
        titulares=new Integer[11];
        suplentes=new Integer[7];
    }

    public Tatica(Tatica t){
        this.suplentes=(t.getSuplentes());
        this.titulares=(t.getTitulares());
    }

    public abstract Tatica clone();

    protected void setSuplentes(Integer[] suplentes){
        this.suplentes=suplentes.clone();
    }

    protected void setTitulares(Integer[] titulares){
        this.titulares=titulares.clone();
    }

    public Integer[] getTitulares(){
        return titulares.clone();
    }

    public Integer[] getSuplentes(){
        return suplentes.clone();
    }

    public abstract double ataque(Equipa team);

    public abstract double defesa(Equipa team);

    public void setJogador(Jogador j,int pos,boolean titulares) throws TaticaInvalidaException, JogadorInexistenteException {
        int in=j.getNumero();
        if(titulares){
            if(pos>10)throw new TaticaInvalidaException();
            if(this.compatible(j,pos)){
                this.titulares[pos] = in;
            }
            else throw new TaticaInvalidaException();
        }
        else {
            if(pos>6)throw new TaticaInvalidaException();
            if(this.compatible(j,pos)){
                this.suplentes[pos] = in;
            }
        }
    }

    public void substituicao(Jogador in,Jogador out){
        int pos1=-1;
        for(int i=0;i<11&&(pos1==-1);i++){
            if(out.getNumero()==this.getTitulares()[i])pos1=i;
        }
        int pos2=-1;
        for(int i=0;i<7&&(pos2==-1);i++){
            if(in.getNumero()==this.getSuplentes()[i])pos2=i;
        }
        if(pos1!=-1&&pos2!=-1){
            try{
                if(compatible(in,pos1)){
                    this.setJogador(in,pos1,true);
                    this.setJogador(out,pos2,false);
                }
                else throw new TaticaInvalidaException("Quatro-Tres-Tres","tentou realizar uma substituicao incompativel");
            } catch (JogadorInexistenteException | TaticaInvalidaException e) {
                e.printStackTrace();
            }
        }
    }

    public abstract boolean compatible(Jogador j, int pos);

    public abstract double ratioCruzamento();

    public abstract int randomPlayer(Ataque evento) throws EventoInvalidoException;

    public void printCompatible(Equipa team, int pos, Set<Integer> adicionados){
        StringBuilder res= new StringBuilder();
        StringBuilder str;
        for(Jogador j:team.getJogadores().values()){
            if(this.compatible(j,pos)&&!adicionados.contains(j.getNumero())) {
                str=new StringBuilder();
                str.append(j.getClass().getSimpleName()).append(":").append(j.getNome())
                        .append(" |").append(j.getNumero()).append("|")
                        .append(" ".repeat(Math.max(0, 35 - str.length())))
                        .append("|").append(j.calculaRatingTotal()).append("|\n");
                res.append(str);
            }
        }
        System.out.println(res.toString());
    }

    public abstract String nomePosicao(int pos) throws TaticaInvalidaException;
}
