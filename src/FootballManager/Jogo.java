package FootballManager;

import java.util.Random;

public class Jogo{
    private Equipa ATeam;
    private Equipa BTeam;
    private int golosA;
    private int golosB;

    public Jogo(){
        this.ATeam = new Equipa();
        this.BTeam = new Equipa();
    }

    public Jogo(Equipa ATeam, Equipa BTeam){
        this.ATeam=ATeam;
        this.BTeam=BTeam;
    }

    public Jogo(Jogo j){
        this.setATeam(j.getATeam());
        this.setBTeam(j.getBTeam());
    }

    public boolean equals(Object j){
        if(j==null)return false;
        if(j==this)return true;
        if(this.getClass()!=j.getClass())return false;
        Jogo novo = (Jogo)j;
        return novo.getATeam().equals(this.getATeam()) && novo.getBTeam().equals(this.getBTeam());
    }

    public String toString(){
        StringBuilder sb= new StringBuilder();
        sb.append("Jogo:").append(this.getATeam().getNome()).append(",").append(this.getBTeam().getNome()).append(",");
        String prefix="";
        for(Jogador j:ATeam.getTitulares()){
            sb.append(prefix);
            prefix=",";
            sb.append(j.getNumero());
        }
        for(Jogador j:BTeam.getTitulares()){
            sb.append(prefix);
            prefix=",";
            sb.append(j.getNumero());
        }
        return sb.toString();
    }

    public Jogo clone(){
        return new Jogo(this);
    }

    //Gets e Sets

    public Equipa getATeam() {
        return new Equipa(this.ATeam);
    }

    public void setATeam(Equipa ATeam) {
        this.ATeam = ATeam;
    }

    public Equipa getBTeam() {
        return new Equipa(this.BTeam);
    }

    public void setBTeam(Equipa BTeam) {
        this.BTeam = BTeam;
    }

    public int getGolosA() {
        return golosA;
    }

    public void setGolosA(int golosA) {
        this.golosA = golosA;
    }

    public int getGolosB() {
        return golosB;
    }

    public void setGolosB(int golosB) {
        this.golosB = golosB;
    }

    //

    //Tive a fazer aproximações e cheguei esta funções para calcular um possivel resultado final, podemos discutir
    //isto todos juntos se tiverem outras ideias
    public int resultadoFinal(){
        int rateA=ATeam.calculaRatingTotal();
        int rateB=BTeam.calculaRatingTotal();
        if(rateA>rateB+50)return 0;
        else if(rateB>rateA+50)return 1;
        double chanceA;
        double chanceE;
        if(rateA>rateB){
            chanceA=(40.0+(this.ATeam.calculaRatingTotal()-this.BTeam.calculaRatingTotal())*1.2)/100.0;
            chanceE=(20.0-(this.ATeam.calculaRatingTotal()-this.BTeam.calculaRatingTotal())*0.4)/100.0;
        }
        else{
            chanceA=(40.0-(this.BTeam.calculaRatingTotal()-this.ATeam.calculaRatingTotal())*0.8)/100.0;
            chanceE=(20.0-(this.BTeam.calculaRatingTotal()-this.ATeam.calculaRatingTotal())*0.4)/100.0;
        }
        Random r = new Random();
        double result = r.nextDouble();

        if(result<chanceA){
            calculaGolos(0,r);
            return 0;
        }
        else if(result<chanceA+chanceE){
            calculaGolos(2,r);
            return 2;
        }
        else{
            calculaGolos(1,r);
            return 1;
        }
    }

    private void calculaGolos(int res,Random r){
        int gA=-1;
        int gB=-1;
        int diffA=((int)(2.5+(this.ATeam.ataque()-this.BTeam.defesa())/20));
        int diffB=((int)(2.5+(this.BTeam.ataque()-this.ATeam.defesa())/20));
        if(diffA<0)diffA=0;
        if(diffB<0)diffB=0;
        if(res==0){
            while(gA<=gB&&gA<=0&&gB<0){
                gA=diffA+(int)Math.round(r.nextGaussian()*diffA/4);
                gB=diffB+(int)Math.round(r.nextGaussian()*diffB/4);
            }
        }
        if(res==1){
            while(gB<=gA&&gA<=0&&gB<0){
                gA=diffA+(int)Math.round(r.nextGaussian()*diffA/4);
                gB=diffB+(int)Math.round(r.nextGaussian()*diffB/4);
            }
        }
        if(res==2){
            while(gB<=gA&&gA<=0&&gB<0){
                gA=diffA+(int)Math.round(r.nextGaussian()*diffA/4);
                gB=diffB+(int)Math.round(r.nextGaussian()*diffB/4);
            }
            gA=Math.round(gA+gB)/2;
            gB=gA;
        }
        this.golosA=gA;
        this.golosB=gB;
    }
}

        /*if(ATeam.calculaRatingTotal() > BTeam.calculaRatingTotal()) return 0;
        else if(ATeam.calculaRatingTotal() < BTeam.calculaRatingTotal()) return 1;
        else return 2;
        */

/*ideia a desenvolver para determinar quem ganha um jogo (Não definitiva):
    -Geramos um valor aleatório entre A e B ( A < B e estão entre 0 e 1)
    -Se o resultado for abaixo de 0.5 ganha a equipa A , senão ganha a B
    -Caso a equipa A seja melhor, o valor B fica mais baixo , sendo mais provavel A ganhar
    -Caso a equipa B seja melhor, o valor A fica mais alto , sendo mais provavel B ganhar
    -Quanto maior a diferença, mais os valores transitam para perto do 0.5 sem passar para a outra metade
 */
