package FootballManager;

import FootballManager.Exceptions.ExcessoJogadoresException;
import FootballManager.Exceptions.JogadorInexistenteException;
import FootballManager.Exceptions.JogadorInvalidoException;
import FootballManager.Players.*;

import java.util.*;
import java.util.stream.Collectors;

public class Equipa implements Comparable<Equipa>{
    private String nome;
    private Map<Integer, Jogador> jogadores;
    private Set<Integer> titulares;
    private Set<Integer> suplentes;

    //Construtores

    public Equipa() {
        this.nome = "";
        this.jogadores = new TreeMap<>();
        this.titulares = new TreeSet<>();
        this.suplentes = new TreeSet<>();
    }

    public Equipa(String nome, Map<Integer, Jogador> jogadores, Set<Integer> titulares, Set<Integer> suplentes) {
        this.setNome(nome);
        this.setJogadores(jogadores);
        this.setTitulares(titulares);
        this.setSuplentes(suplentes);
    }

    public Equipa(Equipa eq) {
        this.nome = eq.getNome();
        this.jogadores = eq.getJogadores();
        this.titulares = eq.getTitulares();
        this.suplentes = eq.getSuplentes();
    }

    //Equals, clone, etc...


    @Override
    public int compareTo(Equipa o) {
        return nome.compareTo(o.getNome());
    }

    public boolean equals(Object eq) {
        if (eq == null) return false;
        if (eq == this) return true;
        if (this.getClass() != eq.getClass()) return false;
        Equipa nova = (Equipa) eq;
        return nome.equals(nova.getNome())
                || titulares.equals(nova.getTitulares())
                || suplentes.equals(nova.getSuplentes());
    }

    public Equipa clone() {
        return new Equipa(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Equipa:").append(this.getNome()).append("\n");
        String prefix = "";
        for (Integer i : this.titulares) {
            sb.append(prefix);
            prefix = "\n";
            sb.append(jogadores.get(i).toString());
        }
        for (Integer i : this.suplentes) {
            sb.append(prefix);
            prefix = "\n";
            sb.append(jogadores.get(i).toString());
        }
        return sb.toString();
    }

    //Gets e Sets

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public Map<Integer, Jogador> getJogadores() {
        return this.jogadores.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, a -> a.getValue().clone()));
    }

    public void setJogadores(Map<Integer, Jogador> jogadores) {
        this.jogadores = jogadores.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, a -> a.getValue().clone()));
    }

    public Set<Jogador> getTitularesSet() {
        return this.titulares.stream().map(a -> jogadores.get(a).clone()).collect(Collectors.toSet());
    }

    public Set<Integer> getTitulares() {
        return new TreeSet<>(this.titulares);
    }

    public void setTitulares(Set<Integer> titulares) {
        try{
            if(titulares.size()>11) throw new ExcessoJogadoresException("O numero de titulares ultrapassa os 11");
            this.titulares = titulares.stream()
                    .filter(a -> {
                        try {
                            if (!jogadores.containsKey(a))
                                throw new JogadorInexistenteException("Jogador N:" + a + " inexistente");
                            if (suplentes.contains(a)){
                                throw new JogadorInvalidoException("Jogador N:"+a+" ja presente nos suplentes");
                            }
                            return true;
                        } catch (JogadorInexistenteException | JogadorInvalidoException e1) {
                            e1.printStackTrace();
                            return false;
                        }
                    })
                    .collect(Collectors.toSet());
        }catch (ExcessoJogadoresException j){
            j.printStackTrace();
        }
    }

    public Set<Jogador> getSuplentesSet() {
        return this.suplentes.stream().map(a -> jogadores.get(a).clone()).collect(Collectors.toSet());
    }

    public Set<Integer> getSuplentes() {
        return new TreeSet<>(this.suplentes);
    }

    public void setSuplentes(Set<Integer> suplentes) {
        try{
            if(suplentes.size()>7)throw new ExcessoJogadoresException("O numero de suplentes ultrapassa os 7");
            this.suplentes = suplentes.stream()
                    .filter(a -> {
                        try {
                            if (!jogadores.containsKey(a))
                                throw new JogadorInexistenteException("Jogador N:" + a + " inexistente");
                            if (titulares.contains(a)){
                                throw new JogadorInvalidoException("Jogador N:"+a+" ja presente nos titulares");
                            }
                            return true;
                        } catch (JogadorInexistenteException | JogadorInvalidoException e) {
                            e.printStackTrace();
                            return false;
                        }
                    })
                    .collect(Collectors.toSet());
        }catch (ExcessoJogadoresException j){
            j.printStackTrace();
        }
    }

    public Jogador getJogador(int nCam) throws JogadorInexistenteException {
        if (!jogadores.containsKey(nCam)) throw new JogadorInexistenteException();
        else return jogadores.get(nCam).clone();
    }

    public boolean eTitular(int nCam){
        return titulares.contains(nCam);
    }

    public boolean eSuplente(int nCam){
        return suplentes.contains(nCam);
    }

    //

    public int numDefesas() {
        int res = 0;
        for (Jogador j : this.titulares.stream().map(a -> jogadores.get(a)).collect(Collectors.toSet()))
            if (j instanceof Defesas) res++;
        return res;
    }

    public int numAvancados() {
        int res = 0;
        for (Jogador j : this.titulares.stream().map(a -> jogadores.get(a)).collect(Collectors.toSet()))
            if (j instanceof Avancados) res++;
        return res;
    }

    public int numLaterais() {
        int res = 0;
        for (Jogador j : this.titulares.stream().map(a -> jogadores.get(a)).collect(Collectors.toSet()))
            if (j instanceof Laterais) res++;
        return res;
    }

    public int numMedios() {
        int res = 0;
        for (Jogador j : this.titulares.stream().map(a -> jogadores.get(a)).collect(Collectors.toSet()))
            if (j instanceof Medios) res++;
        return res;
    }

    public int numGuardaRedes() {
        int res = 0;
        for (Jogador j : this.titulares.stream().map(a -> jogadores.get(a)).collect(Collectors.toSet()))
            if (j instanceof GuardaRedes) res++;
        return res;
    }

    public int defesa() {
        double valor = 0;
        double def = this.numDefesas();
        double gk = this.numGuardaRedes();
        if (gk != 1) return 0;
        double lat = this.numLaterais();
        double med = this.numMedios();
        double total = gk + def + med * 0.5 + lat * 0.67;
        for (Jogador j : this.titulares.stream().map(a -> jogadores.get(a)).collect(Collectors.toSet())) {
            if (j instanceof GuardaRedes) valor += j.calculaRatingTotal();
            else if (j instanceof Defesas) valor += j.calculaRatingTotal();
            else if (j instanceof Laterais) valor += j.calculaRatingTotal() * 0.55;
            else if (j instanceof Medios) valor += j.calculaRatingTotal() * 0.45;
        }
        valor /= total;
        if (valor > 100) return 100;
        return (int) Math.round(valor);
    }

    public int ataque() {
        double valor = 0;
        if (this.numGuardaRedes() != 1) return 0;
        double atk = this.numAvancados();
        double med = this.numMedios();
        double lat = this.numLaterais();
        double total = atk + med * 0.5 + lat * 0.33;
        for (Jogador j : this.titulares.stream().map(a -> jogadores.get(a)).collect(Collectors.toSet())) {
            if (j instanceof Avancados) valor += j.calculaRatingTotal();
            else if (j instanceof Medios) valor += j.calculaRatingTotal() * 0.55;
            else if (j instanceof Laterais) valor += j.calculaRatingTotal() * 0.45;
        }
        valor /= total;
        if (valor > 100) return 100;
        return (int) Math.round(valor);
    }

    public int calculaRatingTotal() {
        return (int) ((this.ataque() * 0.45 + this.defesa() * 0.55) / 2.0);
    }

    public void addJogador(Jogador j) {
        if (j == null) return;
        jogadores.put(j.getNumero(), j.clone());
    }

    public boolean rmvJogador(Jogador j) {
        try {
            if (jogadores.remove(j.getNumero()) == null) {
                throw new JogadorInexistenteException("Jogador:" + j.getNome() + " N:" + j.getNumero() + " inexistente");
            } else{
                this.titulares.remove(j.getNumero());
                this.suplentes.remove(j.getNumero());
                return true;
            }
        } catch (JogadorInexistenteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void transferencia(Equipa eq, Jogador j) {
        if (this.rmvJogador(j)) eq.addJogador(j);
    }

    public boolean add2Titulares(int nCam){
        try{
            if(titulares.size()>=11)throw new ExcessoJogadoresException("Limite de 11 titulares atingido");
            if(!jogadores.containsKey(nCam))throw new JogadorInexistenteException("Jogodor N:"+nCam+" nao encontrado");
            suplentes.remove(nCam);
            titulares.add(nCam);
            return true;
        } catch (ExcessoJogadoresException | JogadorInexistenteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean add2Suplentes(int nCam){
        try{
            if(suplentes.size()>=7)throw new ExcessoJogadoresException("Limite de 11 titulares atingido");
            if(!jogadores.containsKey(nCam))throw new JogadorInexistenteException("Jogodor N:"+nCam+" nao encontrado");
            suplentes.add(nCam);
            titulares.remove(nCam);
            return true;
        } catch (ExcessoJogadoresException | JogadorInexistenteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean substituicao(int in, int out) {
        try {
            if (titulares.remove(out)&&jogadores.containsKey(out)) {
                if (suplentes.remove(in)&&jogadores.containsKey(in)) {
                    titulares.add(in);
                    suplentes.add(out);
                    return true;
                } else {
                    titulares.add(out);
                    throw new JogadorInexistenteException("Jogador N:" + in + " nao encontrado");
                }
            } else {
                throw new JogadorInexistenteException("Jogador N:" + out + " nao encontrado");
            }

        } catch (JogadorInexistenteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean swapBanco(int in,int out){
        try {
            if(jogadores.containsKey(out)&&suplentes.remove(out)){
                if(jogadores.containsKey(in)){
                    if(!titulares.contains(in)){
                        suplentes.add(in);
                        return true;
                    }
                    else throw new JogadorInvalidoException("Jogador N:"+in+" presente nos titulares");
                }
                else throw new JogadorInexistenteException("Jogador N:"+in+" nao encontrado");
            }
            else throw new JogadorInexistenteException("Jogador N:"+out+" nao econtrado");
        }
        catch (JogadorInexistenteException | JogadorInvalidoException e){
            e.printStackTrace();
            return false;
        }
    }

}