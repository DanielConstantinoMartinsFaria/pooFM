import java.util.ArrayList;

public class Jogador{
    protected String posicao;
    protected String nacionalidade;
    protected double velocidade;
    protected double resistencia;
    protected double destreza;
    protected double impulsao;
    protected double cabeca;
    protected double remate;
    protected double passe;
    protected double ratingtotal;
    protected ArrayList<String> equipas;

    //Sub-Classes

    public class GuardaRedes extends Jogador{
        private double elasticidade;

        public void setElasticidade(double elasticidade){
            this.elasticidade=elasticidade;
        }

        public double getElasticidade(double elasticidade){
            return this.elasticidade;
        }

        public double calculaRatingTotal(){
            double valor = Jogador.this.velocidade *0.08;
            valor += Jogador.this.resistencia *0.05;
            valor += Jogador.this.destreza *0.15;
            valor += Jogador.this.impulsao *0.23;
            valor += Jogador.this.cabeca *0.02;
            valor += Jogador.this.remate *0.03;
            valor += Jogador.this.passe *0.12;
            valor += this.elasticidade *0.33 ;
            return valor;
        }
    }

    public class Defesas extends Jogador{
        public double calculaRatingTotal(){
            double valor = Jogador.this.velocidade *0.20;
            valor += Jogador.this.resistencia *0.25;
            valor += Jogador.this.destreza *0.10;
            valor += Jogador.this.impulsao *0.05;
            valor += Jogador.this.cabeca * 0.15;
            valor += Jogador.this.remate *0.05;
            valor += Jogador.this.passe *0.20;
            return valor;
        }
    }

    public class Medios extends Jogador{
        public double calculaRatingTotal(){
            double valor = Jogador.this.velocidade *0.22;
            valor += Jogador.this.resistencia *0.17;
            valor += Jogador.this.destreza *0.16;
            valor += Jogador.this.impulsao *0.03;
            valor += Jogador.this.cabeca *0.03;
            valor += Jogador.this.remate *0.13;
            valor += Jogador.this.passe *0.26;
            return valor;
        }
    }

    public class Avancados extends Jogador{
        public double calculaRatingTotal(){
            double valor = Jogador.this.velocidade *0.10;
            valor += Jogador.this.resistencia *0.05;
            valor += Jogador.this.destreza *0.13;
            valor += Jogador.this.impulsao *0.22;
            valor += Jogador.this.cabeca *0.22;
            valor += Jogador.this.remate *0.26;
            valor += Jogador.this.passe *0.02;
            return valor;
        }
    }

    public class Laterais extends Jogador{
        public double calculaRatingTotal(){
            double valor = Jogador.this.velocidade *0.28;
            valor += Jogador.this.resistencia *0.20;
            valor += Jogador.this.destreza *0.16;
            valor += Jogador.this.impulsao *0.02;
            valor += Jogador.this.cabeca *0.02;
            valor += Jogador.this.remate *0.10;
            valor += Jogador.this.passe *0.22;
            return valor;
        }
    }

    //Gets e Sets

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public double getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
    }

    public double getResistencia() {
        return this.resistencia;
    }

    public void setResistencia(double resistencia) {
        this.resistencia = resistencia;
    }

    public double getDestreza() {
        return destreza;
    }

    public void setDestreza(double destreza) {
        this.destreza = destreza;
    }

    public double getImpulsao() {
        return impulsao;
    }

    public void setImpulsao(double impulsao) {
        this.impulsao = impulsao;
    }

    public double getCabeca() {
        return cabeca;
    }

    public void setCabeca(double cabeca) {
        this.cabeca = cabeca;
    }

    public double getRemate() {
        return remate;
    }

    public void setRemate(double remate) {
        this.remate = remate;
    }

    public double getPasse() {
        return passe;
    }

    public void setPasse(double passe) {
        this.passe = passe;
    }

    public double getRatingtotal() {
        return ratingtotal;
    }

    public void setRatingtotal(double ratingtotal) {
        this.ratingtotal = ratingtotal;
    }

    public void setNacionalidade(String nacionalidade){
        this.nacionalidade=nacionalidade;
    }

    public String getNacionalidade(){
        return this.nacionalidade;
    }

    public void addEquipa(String equipa){
        this.equipas.add(equipa);
    }

    public void rmvEquipa(String equipa){
        this.equipas.removeIf(l -> l.equals(equipa));
    }
}