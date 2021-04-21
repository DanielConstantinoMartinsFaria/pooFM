public class Medios extends Jogador{
    public Medios(){
        super();
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
}