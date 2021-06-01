package FootballManager.Players;

import java.util.ArrayList;

public class Laterais extends Jogador{
    private int cruzamento;

    public Laterais(){
        super();
    }

    public Laterais(String nome,int numero,int velocidade,int resistencia,int destreza,int impulsao,int cabeca,int remate,int passe,int cruzamento,ArrayList<String>equipas){
        super(nome,numero,velocidade,resistencia,destreza,impulsao,cabeca,remate,passe,equipas);
        this.cruzamento=cruzamento;
    }

    public Laterais(Laterais lateral){
        super(lateral);
        this.cruzamento=lateral.getCruzamento();
    }

    public Laterais clone(){
        return new Laterais(this);
    }

    public int getCruzamento(){
        return this.cruzamento;
    }

    public void setCruzamento(int cruzamento){
        this.cruzamento=cruzamento;
    }

    public int calculaRatingTotal(){
        double valor = this.getVelocidade() *0.25;
        valor += this.getResistencia() *0.18;
        valor += this.getDestreza() *0.13;
        valor += this.getImpulsao() *0.02;
        valor += this.getCabeca() *0.02;
        valor += this.getRemate() *0.04;
        valor += this.getPasse() *0.12;
        valor += this.getCruzamento() *0.24;
        return (int)Math.round(valor);
    }
}