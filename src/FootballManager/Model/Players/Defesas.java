package FootballManager.Model.Players;

import FootballManager.Model.Exceptions.JogadorInvalidoException;

import java.util.ArrayList;

public class Defesas extends Jogador{
    private int corpo;

    public Defesas(){
        super();
    }

    public Defesas(String nome,int numero,int velocidade,int resistencia,int destreza,int impulsao,int cabeca,int remate,int passe,int corpo,ArrayList<String>equipas) throws JogadorInvalidoException {
        super(nome,numero,velocidade,resistencia,destreza,impulsao,cabeca,remate,passe,equipas);
        this.corpo=corpo;
    }

    public void setCorpo(int corpo){
        this.corpo=corpo;
    }

    public int getCorpo(){
        return corpo;
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
        double valor = this.getVelocidade() *0.15;
        valor += this.getResistencia() *0.15;
        valor += this.getDestreza() *0.05;
        valor += this.getImpulsao() *0.20;
        valor += this.getCabeca() * 0.05;
        valor += this.getRemate() *0.05;
        valor += this.getPasse() *0.15;
        valor += this.getCorpo() *0.20;
        return (int)Math.round(valor);
    }
}