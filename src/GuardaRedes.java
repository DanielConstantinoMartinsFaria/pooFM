public class GuardaRedes extends Jogador{
    private double elasticidade;

    public GuardaRedes(){
        super();
        this.setElasticidade(0.0);
    }

    public GuardaRedes(String nome,String nacionalidade,String clubeAtual,double velocidade,double resistencia,double destreza,double impulsao,double cabeca,double remate,double passe,double elasticidade,ArrayList<String> equipas) {
        super(nome, nacionalidade, clubeAtual, velocidade, resistencia, destreza, impulsao, cabeca, remate, passe, equipas);
        this.setElasticidade(elasticidade);
    }

    public void setElasticidade(double elasticidade){
        this.elasticidade=elasticidade;
    }

    public double getElasticidade(){
        return this.elasticidade;
    }

    public int calculaRatingTotal(){
        double valor = this.getVelocidade() *0.04;
        valor += this.getResistencia() *0.05;
        valor += this.getDestreza() *0.19;
        valor += this.getImpulsao() *0.23;
        valor += this.getCabeca() *0.02;
        valor += this.getRemate() *0.03;
        valor += this.getPasse() *0.12;
        valor += this.getElasticidade() *0.33 ;
        return (int)Math.round(valor);
    }
}