package FootballManager.Model.Players;


import java.util.ArrayList;

public class Avancados extends Jogador{

    public Avancados(){
        super();
    }

    public Avancados(String nome,int numero,int velocidade,int resistencia,int destreza,int impulsao,int cabeca,int remate,int passe,ArrayList<String>equipas){
        super(nome,numero,velocidade,resistencia,destreza,impulsao,cabeca,remate,passe,equipas);
    }

    public Avancados(Avancados avancado){
        super(avancado);
    }

    public Avancados clone(){
        return new Avancados(this);
    }

    public int calculaRatingTotal(){
        double valor = this.getVelocidade() *0.09;
        valor += this.getResistencia() *0.05;
        valor += this.getDestreza() *0.14;
        valor += this.getImpulsao() *0.22;
        valor += this.getCabeca() *0.22;
        valor += this.getRemate() *0.25;
        valor += this.getPasse() *0.03;
        return (int)Math.round(valor);
    }

}