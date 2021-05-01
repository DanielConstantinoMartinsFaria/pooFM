package FootballManager;

import java.util.ArrayList;

public class Medios extends Jogador{
    public Medios(){
        this.setNome("");
        this.setNacionalidade("");
        this.setVelocidade(0.0);
        this.setResistencia(0.0);
        this.setDestreza(0.0);
        this.setImpulsao(0.0);
        this.setCabeca(0.0);
        this.setRemate(0.0);
        this.setPasse(0.0);
        this.setEquipas(new ArrayList<>());
    }

    public Medios(String nome,String nacionalidade,double velocidade,double resistencia,double destreza, double impulsao,double cabeca,double remate,double passe,ArrayList<String>equipas){
        super(nome,nacionalidade,velocidade,resistencia,destreza,impulsao,cabeca,remate,passe,equipas);
    }

    public Medios(Medios medio){
        super(medio);
    }

    public Medios clone(){
        return new Medios(this);
    }

    public int calculaRatingTotal(){
        double valor = this.getVelocidade() *0.22;
        valor += this.getResistencia() *0.17;
        valor += this.getDestreza() *0.16;
        valor += this.getImpulsao() *0.03;
        valor += this.getCabeca() *0.03;
        valor += this.getRemate() *0.13;
        valor += this.getPasse() *0.26;
        return (int)Math.round(valor);
    }

    public boolean quimica(Jogador j){
        return (j instanceof Defesas || j instanceof Medios || j instanceof Avancados || j instanceof Laterais) && this.getNacionalidade().equals(j.getNacionalidade()) && !this.equals(j);
    }
}