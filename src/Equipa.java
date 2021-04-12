import javax.swing.*;
import java.util.ArrayList;

public class Equipa {
    private String liga;
    private String nome;
    private ArrayList<Jogador> titulares;
    private ArrayList<Jogador> suplentes;
    private ArrayList<Jogador> reservas;

    public int quimica(){
        int chemistry=0;
        for(Jogador j1:this.titulares){
            for(Jogador j2:this.titulares){
                if(j1.quimica(j2))chemistry+=1;
            }
        }
        return chemistry;
    }

    public int numDefesas(){
        int res=0;
        for(Jogador j:this.titulares)if(j instanceof Jogador.Defesas)res++;
        return res;
    }

    public int numAvancados(){
        int res=0;
        for(Jogador j:this.titulares)if(j instanceof Jogador.Avancados)res++;
        return res;
    }

    public int numLaterais(){
        int res=0;
        for(Jogador j:this.titulares)if(j instanceof Jogador.Laterais)res++;
        return res;
    }

    public int numMedios(){
        int res=0;
        for(Jogador j:this.titulares)if(j instanceof Jogador.Medios)res++;
        return res;
    }

    public int numGuardaRedes(){
        int res=0;
        for(Jogador j:this.titulares)if(j instanceof Jogador.GuardaRedes)res++;
        return res;
    }

    public int defesa(){
        double valor=0;
        double def= this.numDefesas();
        double gk=this.numGuardaRedes();
        if(gk!=1)return 0;
        double lat=this.numLaterais();
        double med=this.numMedios();
        double total=gk+def+med*0.5+lat*0.67;
        for(Jogador j:this.titulares){
            if(j instanceof Jogador.GuardaRedes)valor+= j.calculaRatingTotal();
            else if(j instanceof Jogador.Defesas)valor+= j.calculaRatingTotal()*(def+2)/4;
            else if(j instanceof Jogador.Laterais)valor+= j.calculaRatingTotal()*0.67*(2-Math.abs(lat-2))/2;
            else if(j instanceof Jogador.Medios)valor+= j.calculaRatingTotal()*0.55;
        }
        valor/=total;
        if(valor>100)return 100;
        return (int)Math.round(valor);
    }

    public int ataque(){
        double valor=0;
        if(this.numGuardaRedes()!=1)return 0;
        double atk=this.numAvancados();
        double med=this.numMedios();
        double lat=this.numLaterais();
        double total=atk+med*0.5+lat*0.33;
        for(Jogador j:this.titulares){
            if(j instanceof Jogador.Avancados)valor+= j.calculaRatingTotal()*(atk+2)/4;
            else if(j instanceof Jogador.Medios)valor+= j.calculaRatingTotal()*0.55;
            else if(j instanceof Jogador.Laterais)valor+= j.calculaRatingTotal()*0.33*(2-Math.abs(lat-2))/2;
        }
        valor/=total;
        if(valor>100)return 100;
        return (int)Math.round(valor);
    }

    public int calculaRatingTotal(){
        return (int)((this.ataque()+this.defesa())/2.0);
    }

    public void addJogador(Jogador j){
        if(titulares.size()<11)titulares.add(j);
        else if(suplentes.size()<7)suplentes.add(j);
        else reservas.add(j);
    }

    public boolean rmvJogador(Jogador j){
        return this.reservas.removeIf(l -> l.equals(j)) || this.suplentes.removeIf(l -> l.equals(j)) || this.titulares.removeIf(l -> l.equals(j));
    }

    public void transferencia(Equipa eq,Jogador j){
        if(this.rmvJogador(j))eq.addJogador(j);
    }


}
