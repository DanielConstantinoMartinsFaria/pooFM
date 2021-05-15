package FootballManager;

import java.util.ArrayList;

public class Laterais extends Jogador{
    private double cruzamento;

    public Laterais(){
        super();
    }

    public Laterais(String nome,String nacionalidade,double velocidade,double resistencia,double destreza, double impulsao,double cabeca,double remate,double passe,double cruzamento,ArrayList<String>equipas){
        super(nome,nacionalidade,velocidade,resistencia,destreza,impulsao,cabeca,remate,passe,equipas);
        this.cruzamento=cruzamento;
    }

    public Laterais(Laterais lateral){
        super(lateral);
    }

    public Laterais clone(){
        return new Laterais(this);
    }

    public double getCruzamento(){
        return cruzamento;
    }

    public void setCruzamento(double cruzamento){
        this.cruzamento=cruzamento;
    }

    public int calculaRatingTotal(){
        double valor = this.getVelocidade() *0.25;
        valor += this.getResistencia() *0.18;
        valor += this.getDestreza() *0.13;
        valor += this.getImpulsao() *0.02;
        valor += this.getCabeca() *0.02;
        valor += this.getRemate() *0.08;
        valor += this.getPasse() *0.12;
        valor += this.getCruzamento() *0.20;
        return (int)Math.round(valor);
    }

    public boolean quimica(Jogador j){
        return (j instanceof Avancados || j instanceof Medios || j instanceof Defesas) && this.getNacionalidade().equals(j.getNacionalidade()) && !this.equals(j);
    }
}