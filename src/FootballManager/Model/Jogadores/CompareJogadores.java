package FootballManager.Model.Jogadores;

import java.util.Comparator;

public class CompareJogadores implements Comparator<Jogador> {
    @Override
    public int compare(Jogador o1, Jogador o2) {
        if(o1 instanceof GuardaRedes){
            if(o2 instanceof GuardaRedes)return 0;
            else return -1;
        }
        if(o1 instanceof Defesas){
            if(o2 instanceof GuardaRedes)return 1;
            if(o2 instanceof Defesas)return 0;
            else return -1;
        }
        if(o1 instanceof Laterais){
            if(o2 instanceof GuardaRedes)return 1;
            if(o2 instanceof Defesas)return 1;
            if(o2 instanceof Laterais)return 0;
            else return -1;
        }
        if(o1 instanceof Medios){
            if(o2 instanceof Avancados)return -1;
            if(o2 instanceof Medios)return 0;
            else return 1;
        }
        else{
            if (o2 instanceof Avancados)return 0;
            else return 1;
        }
    }
}
