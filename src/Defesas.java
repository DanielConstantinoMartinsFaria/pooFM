public class Defesas extends Jogador{
    public Defesas(){
        super();
    }

    public int calculaRatingTotal(){
        double valor = this.getVelocidade() *0.20;
        valor += this.getResistencia() *0.25;
        valor += this.getDestreza() *0.10;
        valor += this.getImpulsao() *0.05;
        valor += this.getCabeca() * 0.15;
        valor += this.getRemate() *0.05;
        valor += this.getPasse() *0.20;
        return (int)Math.round(valor);
    }
}