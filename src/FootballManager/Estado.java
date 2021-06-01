package FootballManager;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Estado {
    private Set<Equipa> equipas;
    private Set<Jogo> jogos;

    public Estado(){
        equipas=new TreeSet<>();
        jogos=new TreeSet<>();
    }

    public Estado(Set<Equipa>equipas,Set<Jogo>jogos){
        this.setEquipas(equipas);
        this.setJogos(jogos);
    }

    public Estado(Estado estado){
        this.equipas=estado.getEquipas();
        this.jogos=estado.getJogos();
    }

    public Set<Equipa> getEquipas(){
        return this.equipas.stream().map(Equipa::clone).collect(Collectors.toSet());
    }

    public Set<Jogo> getJogos(){
        return this.jogos.stream().map(Jogo::clone).collect(Collectors.toSet());
    }

    public void setEquipas(Set<Equipa>equipas){
        this.equipas=equipas.stream().map(Equipa::clone).collect(Collectors.toSet());
    }

    public void setJogos(Set<Jogo>jogos){
        this.jogos=jogos.stream().map(Jogo::clone).collect(Collectors.toSet());
    }


}
