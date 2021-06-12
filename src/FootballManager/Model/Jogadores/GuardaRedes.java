package FootballManager.Model.Jogadores;
import FootballManager.Model.Exceptions.JogadorInvalidoException;

import java.util.ArrayList;

public class GuardaRedes extends Jogador{
    private int elasticidade;

    public GuardaRedes(){
        super();
    }

    public GuardaRedes(String nome,int numero,int velocidade,int resistencia,int destreza,int impulsao,int cabeca,int remate,int passe,int elasticidade,ArrayList<String> equipas) throws JogadorInvalidoException {
        super(nome,numero,velocidade,resistencia,destreza,impulsao,cabeca,remate,passe,equipas);
        this.elasticidade=elasticidade;
    }

    public GuardaRedes(GuardaRedes redes){
        super(redes);
        this.elasticidade=redes.getElasticidade();
    }

    public GuardaRedes(Jogador jogador){
        super(jogador);
        this.elasticidade=(int)Math.round((jogador.getImpulsao()+jogador.getDestreza())/2.4);
    }

    public GuardaRedes clone(){
        return new GuardaRedes(this);
    }

    public void setElasticidade(int elasticidade){
        this.elasticidade=elasticidade;
    }

    public int getElasticidade(){
        return this.elasticidade;
    }

    public int calculaRatingTotal(){
        double valor = this.getVelocidade() *0.20;
        valor += this.getResistencia() *0.02;
        valor += this.getDestreza() *0.19;
        valor += this.getImpulsao() *0.19;
        valor += this.getCabeca() *0.01;
        valor += this.getRemate() *0.02;
        valor += this.getPasse() *0.04;
        valor += this.getElasticidade() *0.33 ;
        return (int)Math.round(valor);
    }
}