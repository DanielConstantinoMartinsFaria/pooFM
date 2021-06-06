package FootballManager.Model.Equipas.Taticas;


import FootballManager.Model.Equipas.Equipa;
import FootballManager.Model.Exceptions.JogadorInexistenteException;
import FootballManager.Model.Exceptions.TaticaInvalidaException;
import FootballManager.Model.Players.*;

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

    public void setJogador(int in,int pos,boolean titulares){
        if(titulares)this.titulares[pos]=in;
        else this.suplentes[pos]=in;
    }

    public void substituicao(int in,int out,Equipa team){
        int pos1=-1;
        for(int i=0;i<11&&(pos1==-1);i++){
            if(out==this.getTitulares()[i])pos1=i;
        }
        int pos2=-1;
        for(int i=0;i<7&&(pos2==-1);i++){
            if(in==this.getSuplentes()[i])pos2=i;
        }
        if(pos1!=-1&&pos2!=-1){
            try{
                Jogador inJ = team.getJogador(in);
                Jogador outJ = team.getJogador(out);
                if(compatible(inJ,outJ)){
                    this.setJogador(in,pos1,true);
                    this.setJogador(out,pos2,false);
                }
                else throw new TaticaInvalidaException("Quatro-Tres-Tres","tentou realizar uma substituicao incompativel");
            } catch (JogadorInexistenteException | TaticaInvalidaException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean compatible(Jogador j1, Jogador j2) {
        if(j1 instanceof GuardaRedes)return (j2 instanceof GuardaRedes);
        else if(j1 instanceof Defesas)return (j2 instanceof Defesas)||(j1.isCentral()==j2.isCentral());
        else if(j1 instanceof Laterais)return (j2 instanceof Laterais)||(j1.isCentral()==j2.isCentral());
        else if(j1 instanceof Medios)return (j2 instanceof Medios)||(j1.isCentral()==j2.isCentral());
        else if(j1 instanceof Avancados)return (j2 instanceof Avancados)||(j1.isCentral()==j2.isCentral());
        return false;
    }
}
