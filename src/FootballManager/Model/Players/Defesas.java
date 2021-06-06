package FootballManager.Model.Players;

import java.util.ArrayList;

public class Defesas extends Jogador{


    public Defesas(){
        super();
    }

    public Defesas(String nome,int numero,int velocidade,int resistencia,int destreza,int impulsao,int cabeca,int remate,int passe,ArrayList<String>equipas){
        super(nome,numero,velocidade,resistencia,destreza,impulsao,cabeca,remate,passe,equipas);
    }

    public Defesas(Defesas defesa){
        super(defesa);
    }

    public Defesas(Jogador jogador){
        super(jogador);
    }

    public Defesas clone(){
        return new Defesas(this);
    }

    public int calculaRatingTotal(){
        double valor = this.getVelocidade() *0.20;
        valor += this.getResistencia() *0.15;
        valor += this.getDestreza() *0.05;
        valor += this.getImpulsao() *0.35;
        valor += this.getCabeca() * 0.05;
        valor += this.getRemate() *0.05;
        valor += this.getPasse() *0.15;
        return (int)Math.round(valor);
    }
}