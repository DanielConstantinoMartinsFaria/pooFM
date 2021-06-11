package FootballManager.Model.Players;


import FootballManager.Model.Exceptions.JogadorInvalidoException;

import java.util.ArrayList;

public class Avancados extends Jogador{
    private int desmarcacao;

    public Avancados(){
        super();
    }

    public Avancados(String nome,int numero,int velocidade,int resistencia,int destreza,int impulsao,int cabeca,int remate,int desmarcacao,int passe,ArrayList<String>equipas) throws JogadorInvalidoException {
        super(nome,numero,velocidade,resistencia,destreza,impulsao,cabeca,remate,passe,equipas);
        this.desmarcacao=desmarcacao;
    }

    public void setDesmarcacao(int desmarcacao){
        this.desmarcacao=desmarcacao;
    }

    public int getDesmarcacao(){
        return desmarcacao;
    }

    public Avancados(Avancados avancado){
        super(avancado);
    }

    public Avancados(Jogador jogador){
        super(jogador);
    }

    public Avancados clone(){
        return new Avancados(this);
    }

    public int calculaRatingTotal(){
        double valor = this.getVelocidade() *0.08;
        valor += this.getResistencia() *0.04;
        valor += this.getDestreza() *0.12;
        valor += this.getImpulsao() *0.19;
        valor += this.getCabeca() *0.19;
        valor += this.getRemate() *0.22;
        valor += this.getPasse() *0.01;
        valor += this.getDesmarcacao()*0.15;
        return (int)Math.round(valor);
    }

}