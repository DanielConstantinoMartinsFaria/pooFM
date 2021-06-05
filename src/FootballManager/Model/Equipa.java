package FootballManager.Model;

import FootballManager.Model.Exceptions.JogadorInexistenteException;
import FootballManager.Model.Players.*;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class Equipa implements Comparable<Equipa>, Serializable {
    private String nome;
    private Map<Integer, Jogador> jogadores;
    private Set<Integer>defaultTitulares;
    private Set<Integer>defaultSuplentes;

    //Construtores

    public Equipa() {
        this.nome = "";
        this.jogadores = new TreeMap<>();
    }

    public Equipa(String nome, Map<Integer, Jogador> jogadores){
        this.setNome(nome);
        this.setJogadores(jogadores);
    }

    public Equipa(Equipa eq) {
        this.nome = eq.getNome();
        this.jogadores = eq.getJogadores();
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
        return nome.equals(nova.getNome());
    }

    public Equipa clone() {
        return new Equipa(this);
    }

    public String align(String input){
        int init=95-input.length();
        return "-".repeat(Math.max(0, init / 2))
                + input +
                "-".repeat(Math.max(0, 95 - (init / 2 + input.length())));
    }

    public String prettyString(){
        StringBuilder sb = new StringBuilder();
        sb.append(align(nome));
        for(Integer i:jogadores.keySet()){
            sb.append("\n");
            sb.append(jogadores.get(i).prettyString());
        }
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Equipa:").append(this.nome);
        for (Jogador i : this.jogadores.values()) {
            sb.append("\n");
            sb.append(i.toString());
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

    public Jogador getJogador(int nCam) throws JogadorInexistenteException {
        if (!jogadores.containsKey(nCam)) throw new JogadorInexistenteException();
        else return jogadores.get(nCam).clone();
    }

    public int numDefesas(Set<Integer> titulares) {
        int res = 0;
        for (Jogador j : titulares.stream().map(a -> jogadores.get(a)).collect(Collectors.toSet()))
            if (j instanceof Defesas) res++;
        return res;
    }

    public int numAvancados(Set<Integer> titulares) {
        int res = 0;
        for (Jogador j : titulares.stream().map(a -> jogadores.get(a)).collect(Collectors.toSet()))
            if (j instanceof Avancados) res++;
        return res;
    }

    public int numLaterais(Set<Integer> titulares) {
        int res = 0;
        for (Jogador j : titulares.stream().map(a -> jogadores.get(a)).collect(Collectors.toSet()))
            if (j instanceof Laterais) res++;
        return res;
    }

    public int numMedios(Set<Integer> titulares) {
        int res = 0;
        for (Jogador j : titulares.stream().map(a -> jogadores.get(a)).collect(Collectors.toSet()))
            if (j instanceof Medios) res++;
        return res;
    }

    public int numGuardaRedes(Set<Integer> titulares) {
        int res = 0;
        for (Jogador j : titulares.stream().map(a -> jogadores.get(a)).collect(Collectors.toSet()))
            if (j instanceof GuardaRedes) res++;
        return res;
    }

    public int defesa(Set<Integer> titulares) {
        double valor = 0;
        double def = this.numDefesas(titulares);
        double gk = this.numGuardaRedes(titulares);
        if (gk != 1) return 0;
        double lat = this.numLaterais(titulares);
        double med = this.numMedios(titulares);
        double total = gk + def + med * 0.5 + lat * 0.67;
        for (Jogador j : titulares.stream().map(a -> jogadores.get(a)).collect(Collectors.toSet())) {
            if (j instanceof GuardaRedes) valor += j.calculaRatingTotal();
            else if (j instanceof Defesas) valor += j.calculaRatingTotal();
            else if (j instanceof Laterais) valor += j.calculaRatingTotal() * 0.55;
            else if (j instanceof Medios) valor += j.calculaRatingTotal() * 0.45;
        }
        valor /= total;
        if (valor > 100) return 100;
        return (int) Math.round(valor);
    }

    public int ataque(Set<Integer> titulares) {
        double valor = 0;
        if (this.numGuardaRedes(titulares) != 1) return 0;
        double atk = this.numAvancados(titulares);
        double med = this.numMedios(titulares);
        double lat = this.numLaterais(titulares);
        double total = atk + med * 0.5 + lat * 0.33;
        for (Jogador j : titulares.stream().map(a -> jogadores.get(a)).collect(Collectors.toSet())) {
            if (j instanceof Avancados) valor += j.calculaRatingTotal();
            else if (j instanceof Medios) valor += j.calculaRatingTotal() * 0.55;
            else if (j instanceof Laterais) valor += j.calculaRatingTotal() * 0.45;
        }
        valor /= total;
        if (valor > 100) return 100;
        return (int) Math.round(valor);
    }

    public int calculaRatingTotal(Set<Integer> titulares) {
        return (int) ((this.ataque(titulares) + this.defesa(titulares)) / 2.0);
    }

    public void addJogador(Jogador j) {
        if (j == null) return;
        jogadores.put(j.getNumero(), j.clone());
    }

    public boolean rmvJogador(Jogador j) {
        try {
            if (jogadores.remove(j.getNumero()) == null) {
                throw new JogadorInexistenteException("Jogador:" + j.getNome() + " N:" + j.getNumero() + " inexistente");
            } else return true;

        } catch (JogadorInexistenteException e) {
            e.printStackTrace();
            return false;
        }
    }

}