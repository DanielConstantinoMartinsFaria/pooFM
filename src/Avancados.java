public class Avancados extends Jogador{
    public Avancados(){
        super();
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
}