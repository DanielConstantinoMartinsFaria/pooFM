package FootballManager;

import java.util.ArrayList;

public class Medios extends Jogador{
    private double controlo;

    public Medios(){
        super();
    }

    public Medios(String nome,String nacionalidade,double velocidade,double resistencia,double destreza, double impulsao,double cabeca,double remate,double passe,double controlo,ArrayList<String>equipas){
        super(nome,nacionalidade,velocidade,resistencia,destreza,impulsao,cabeca,remate,passe,equipas);
        this.controlo=controlo;
    }

    public Medios(Medios medio){
        super(medio);
    }

    public Medios clone(){
        return new Medios(this);
    }

    public double getControlo(){
        return controlo;
    }

    public void setRecuperacao(double controlo){
        this.controlo=controlo;
    }

    public int calculaRatingTotal(){
        double valor = this.getVelocidade() *0.19;
        valor += this.getResistencia() *0.15;
        valor += this.getDestreza() *0.13;
        valor += this.getImpulsao() *0.02;
        valor += this.getCabeca() *0.02;
        valor += this.getRemate() *0.12;
        valor += this.getPasse() *0.22;
        valor += this.getControlo() *0.15;
        return (int)Math.round(valor);
    }

    public boolean quimica(Jogador j){
        return (j instanceof Defesas || j instanceof Medios || j instanceof Avancados || j instanceof Laterais) && this.getNacionalidade().equals(j.getNacionalidade()) && !this.equals(j);
    }
}