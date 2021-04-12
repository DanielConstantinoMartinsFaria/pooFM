import java.util.ArrayList;

public class Equipa {
    private String liga;
    private String nome;
    private ArrayList<Jogador> titulares;
    private ArrayList<Jogador> suplentes;
    private ArrayList<Jogador> reservas;
    private int numJogoadores;

    public int quimica(){
        int chemistry=0;
        for(Jogador j1:this.titulares){
            for(Jogador j2:this.titulares){
                if(j1.quimica(j2))chemistry+=1;
            }
        }
        return chemistry;
    }

    public int defesa(){
        int valor=0;
        for(Jogador j:this.titulares){
            if(j instanceof Jogador.GuardaRedes)valor+=((Jogador.GuardaRedes) j).calculaRatingTotal();
            else if(j instanceof Jogador.Defesas)valor+=((Jogador.Defesas) j).calculaRatingTotal();
            else if(j instanceof Jogador.Laterais)valor+=((Jogador.Laterais) j).calculaRatingTotal()*0.67;
            else if(j instanceof Jogador.Medios)valor+=((Jogador.Medios) j).calculaRatingTotal()*0.5;
        }
        return valor;
    }

    public int ataque(){
        int valor=0;
        for(Jogador j:this.titulares){
            if(j instanceof Jogador.Avancados)valor+=((Jogador.Avancados) j).calculaRatingTotal();
            else if(j instanceof Jogador.Medios)valor+=((Jogador.Medios) j).calculaRatingTotal()*0.5;
            else if(j instanceof Jogador.Laterais)valor+=((Jogador.Laterais) j).calculaRatingTotal()*0.33;
        }
        return valor;
    }

    public void addJogador(Jogador j,boolean suplente){
        if(!suplente&&titulares.size()<11)titulares.add(j);
        else if(suplentes.size()<7)suplentes.add(j);
        else reservas.add(j);
    }

    public void rmvJogador(Jogador j){
        this.reservas.removeIf(l -> l.equals(j));
        this.suplentes.removeIf(l -> l.equals(j));
        this.titulares.removeIf(l -> l.equals(j));
    }



}
