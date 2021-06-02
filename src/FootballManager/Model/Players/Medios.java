package FootballManager.Model.Players;

import java.util.ArrayList;

public class Medios extends Jogador{
    private int recuperacao;

    public Medios(){
        super();
    }

    public Medios(String nome,int numero,int velocidade,int resistencia,int destreza,int impulsao,int cabeca,int remate,int passe,int recuperacao,ArrayList<String>equipas){
        super(nome,numero,velocidade,resistencia,destreza,impulsao,cabeca,remate,passe,equipas);
        this.recuperacao =recuperacao;
    }

    public Medios(Medios medio){
        super(medio);
        this.recuperacao=medio.getRecuperacao();
    }

    public Medios clone(){
        return new Medios(this);
    }

    public int getRecuperacao(){
        return this.recuperacao;
    }

    public void setRecuperacao(int recuperacao){
        this.recuperacao = recuperacao;
    }

    public int calculaRatingTotal(){
        double valor = this.getVelocidade() *0.19;
        valor += this.getResistencia() *0.15;
        valor += this.getDestreza() *0.13;
        valor += this.getImpulsao() *0.02;
        valor += this.getCabeca() *0.02;
        valor += this.getRemate() *0.12;
        valor += this.getPasse() *0.22;
        valor += this.getRecuperacao() *0.15;
        return (int)Math.round(valor);
    }
}