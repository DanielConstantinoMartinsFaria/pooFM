package FootballManager;

import java.util.ArrayList;

public class Laterais extends Jogador{
    public Laterais(){
        this.setNome("");
        this.setNacionalidade("");
        this.setVelocidade(0.0);
        this.setResistencia(0.0);
        this.setDestreza(0.0);
        this.setImpulsao(0.0);
        this.setCabeca(0.0);
        this.setRemate(0.0);
        this.setPasse(0.0);
        this.setEquipas(new ArrayList<>());
    }

    public Laterais(String nome,String nacionalidade,double velocidade,double resistencia,double destreza, double impulsao,double cabeca,double remate,double passe,ArrayList<String>equipas){
        super(nome,nacionalidade,velocidade,resistencia,destreza,impulsao,cabeca,remate,passe,equipas);
    }

    public Laterais(Laterais lateral){
        super(lateral);
    }

    public Laterais clone(){
        return new Laterais(this);
    }

    public int calculaRatingTotal(){
        double valor = this.getVelocidade() *0.28;
        valor += this.getResistencia() *0.20;
        valor += this.getDestreza() *0.16;
        valor += this.getImpulsao() *0.02;
        valor += this.getCabeca() *0.02;
        valor += this.getRemate() *0.10;
        valor += this.getPasse() *0.22;
        return (int)Math.round(valor);
    }

    public boolean quimica(Jogador j){
        return (j instanceof Defesas || j instanceof Medios) && this.getNacionalidade().equals(j.getNacionalidade()) && !this.equals(j);
    }
}