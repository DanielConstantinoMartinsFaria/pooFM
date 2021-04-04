public class Jogador{
    private String posicao;
    private double velocidade;
    private double resistência;
    private double destreza;
    private double impulsão;
    private double cabeca;
    private double remate;
    private double passe;
    private double ratingtotal;


    public class GuardaRedes extends Jogador{
        private double elasticidade;

        public double calculaRatingTotal(){
            double valor = this.velocidade * peso;
            valor += this.resistencia * peso;
            valor += this.destreza * peso;
            valor += this.impulsao * peso;
            valor += this.cabeca * peso;
            valor += this.remate * peso;
            valor += this.passe * peso;
            valor += this.elasticidade * peso;
            return valor;
        }
    }

    public class Defesas extends Jogador{
        public double calculaRatingTotal(){
            double valor = this.velocidade * peso;
            valor += this.resistencia * peso;
            valor += this.destreza * peso;
            valor += this.impulsao * peso;
            valor += this.cabeca * peso;
            valor += this.remate * peso;
            valor += this.passe * peso;
            return valor;
        }

    }

    public class Medios extends Jogador{
        public double calculaRatingTotal(){
            double valor = this.velocidade * peso;
            valor += this.resistencia * peso;
            valor += this.destreza * peso;
            valor += this.impulsao * peso;
            valor += this.cabeca * peso;
            valor += this.remate * peso;
            valor += this.passe * peso;
            return valor;
        }

    }

    public class Avancados extends Jogador{
        public double calculaRatingTotal(){
            double valor = this.velocidade * peso;
            valor += this.resistencia * peso;
            valor += this.destreza * peso;
            valor += this.impulsao * peso;
            valor += this.cabeca * peso;
            valor += this.remate * peso;
            valor += this.passe * peso;
            return valor;
        }

    }

    public class Laterais extends Jogador{
        public double calculaRatingTotal(){
            double valor = this.velocidade * peso;
            valor += this.resistencia * peso;
            valor += this.destreza * peso;
            valor += this.impulsao * peso;
            valor += this.cabeca * peso;
            valor += this.remate * peso;
            valor += this.passe * peso;
            return valor;
        }

    }

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

    public double getResistência() {
        return resistência;
    }

    public void setResistência(double resistência) {
        this.resistência = resistência;
    }

    public double getDestreza() {
        return destreza;
    }

    public void setDestreza(double destreza) {
        this.destreza = destreza;
    }

    public double getImpulsão() {
        return impulsão;
    }

    public void setImpulsão(double impulsão) {
        this.impulsão = impulsão;
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
}