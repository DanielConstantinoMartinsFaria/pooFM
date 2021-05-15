package FootballManager;

import java.util.ArrayList;

public class Defesas extends Jogador{
    private double corpo;

    public Defesas(){
        super();
        this.corpo=0.0;
    }

    public Defesas(String nome,String nacionalidade,double velocidade,double resistencia,double destreza, double impulsao,double cabeca,double remate,double passe,double corpo,ArrayList<String>equipas){
        super(nome,nacionalidade,velocidade,resistencia,destreza,impulsao,cabeca,remate,passe,equipas);
        this.corpo=corpo;
    }

    public Defesas(Defesas defesa){
        super(defesa);
    }

    public Defesas clone(){
        return new Defesas(this);
    }

    public double getCorpo() {
        return corpo;
    }

    public void setCorpo(double corpo){
        this.corpo=corpo;
    }

    public int calculaRatingTotal(){
        double valor = this.getVelocidade() *0.10;
        valor += this.getResistencia() *0.10;
        valor += this.getDestreza() *0.02;
        valor += this.getImpulsao() *0.25;
        valor += this.getCabeca() * 0.01;
        valor += this.getRemate() *0.02;
        valor += this.getPasse() *0.06;
        valor += this.getCorpo() *0.45;
        return (int)Math.round(valor);
    }

    public boolean quimica(Jogador j){
        return (j instanceof Defesas || j instanceof GuardaRedes || j instanceof Laterais || j instanceof Medios) && this.getNacionalidade().equals(j.getNacionalidade()) && !this.equals(j);
    }
}