public class Laterais extends Jogador{
    public Laterais(){
        super();
    }

    public int calculaRatingTotal(){
        double valor = this.getVelocidade() *0.28;
        valor += this.getResistencia() *0.20;
        valor += this.getDestreza() *0.16;
        valor += this.getImpulsao() *0.02;
        valor += this.getCabeca() *0.02;
        valor += this.getRemate() *0.10;
        valor += this.getPasse() *0.22;
        return (int)Math.round(valor);
    }
}