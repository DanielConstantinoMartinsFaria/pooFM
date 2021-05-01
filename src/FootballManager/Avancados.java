package FootballManager;


import java.util.ArrayList;

public class Avancados extends Jogador{
    public Avancados(){
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

    public Avancados(String nome,String nacionalidade,double velocidade,double resistencia,double destreza, double impulsao,double cabeca,double remate,double passe,ArrayList<String>equipas){
        super(nome,nacionalidade,velocidade,resistencia,destreza,impulsao,cabeca,remate,passe,equipas);
    }

    public Avancados(Avancados avancado){
        super(avancado);
    }

    public Avancados clone(){
        return new Avancados(this);
    }

    public int calculaRatingTotal(){
        double valor = this.getVelocidade() *0.10;
        valor += this.getResistencia() *0.05;
        valor += this.getDestreza() *0.13;
        valor += this.getImpulsao() *0.22;
        valor += this.getCabeca() *0.22;
        valor += this.getRemate() *0.26;
        valor += this.getPasse() *0.02;
        return (int)Math.round(valor);
    }

    public boolean quimica(Jogador j){
        return (j instanceof Medios || j instanceof Avancados) && this.getNacionalidade().equals(j.getNacionalidade()) && !this.equals(j);
    }
}