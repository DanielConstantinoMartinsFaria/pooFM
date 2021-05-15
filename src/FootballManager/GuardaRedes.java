package FootballManager;
import java.util.ArrayList;

public class GuardaRedes extends Jogador{
    private double elasticidade;

    public GuardaRedes(){
        super();
    }

    public GuardaRedes(String nome,String nacionalidade,double velocidade,double resistencia,double destreza,double impulsao,double cabeca,double remate,double passe,double elasticidade,ArrayList<String> equipas) {
        super(nome,nacionalidade,velocidade,resistencia,destreza,impulsao,cabeca,remate,passe,equipas);
        this.elasticidade=elasticidade;
    }

    public GuardaRedes(GuardaRedes redes){
        super(redes);
        this.setElasticidade(redes.getElasticidade());
    }

    public GuardaRedes clone(){
        return new GuardaRedes(this);
    }

    public void setElasticidade(double elasticidade){
        this.elasticidade=elasticidade;
    }

    public double getElasticidade(){
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

    public boolean quimica(Jogador j){
        return (j instanceof Defesas) && (this.getNacionalidade().equals(j.getNacionalidade())) && (!this.equals(j));
    }
}