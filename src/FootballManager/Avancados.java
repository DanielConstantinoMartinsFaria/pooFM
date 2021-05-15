package FootballManager;


import java.util.ArrayList;

public class Avancados extends Jogador{
    private double desmarcacao;

    public Avancados(){
        super();
        this.desmarcacao=0.0;
    }

    public Avancados(String nome,String nacionalidade,double velocidade,double resistencia,double destreza, double impulsao,double cabeca,double remate,double passe,double desmarcacao,ArrayList<String>equipas){
        super(nome,nacionalidade,velocidade,resistencia,destreza,impulsao,cabeca,remate,passe,equipas);
        this.desmarcacao=desmarcacao;
    }

    public Avancados(Avancados avancado){
        super(avancado);
    }

    public Avancados clone(){
        return new Avancados(this);
    }

    public double getDesmarcacao() {
        return desmarcacao;
    }

    public void setDesmarcacao(double desmarcacao){
        this.desmarcacao=desmarcacao;
    }

    public int calculaRatingTotal(){
        double valor = this.getVelocidade() *0.07;
        valor += this.getResistencia() *0.04;
        valor += this.getDestreza() *0.11;
        valor += this.getImpulsao() *0.18;
        valor += this.getCabeca() *0.18;
        valor += this.getRemate() *0.20;
        valor += this.getPasse() *0.02;
        valor += this.getDesmarcacao() *0.20;
        return (int)Math.round(valor);
    }

    public boolean quimica(Jogador j){
        return (j instanceof Medios || j instanceof Avancados || j instanceof Laterais) && this.getNacionalidade().equals(j.getNacionalidade()) && !this.equals(j);
    }
}