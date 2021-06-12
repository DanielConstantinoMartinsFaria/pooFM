package FootballManager.Model.Jogadores;


import FootballManager.Model.Exceptions.JogadorInvalidoException;

import java.util.ArrayList;

public class Avancados extends Jogador{
    private int desmarcacao;

    public Avancados(){
        super();
    }

    public Avancados(String nome,int numero,int velocidade,int resistencia,int destreza,int impulsao,int cabeca,int remate,int passe,int desmarcacao,ArrayList<String>equipas) throws JogadorInvalidoException {
        super(nome,numero,velocidade,resistencia,destreza,impulsao,cabeca,remate,passe,equipas);
        this.desmarcacao=desmarcacao;
    }

    public Avancados(Avancados avancado){
        super(avancado);
        this.desmarcacao=avancado.desmarcacao;
    }

    public Avancados(Jogador jogador){
        super(jogador);
        desmarcacao=(int)Math.round(jogador.getVelocidade()*0.6+ jogador.getDestreza()*0.2+ jogador.getImpulsao()*0.2);
    }

    public Avancados clone(){
        return new Avancados(this);
    }

    public void setDesmarcacao(int desmarcacao){
        this.desmarcacao=desmarcacao;
    }

    public int getDesmarcacao(){
        return desmarcacao;
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