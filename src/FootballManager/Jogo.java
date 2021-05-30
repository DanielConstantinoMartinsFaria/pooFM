package FootballManager;

import FootballManager.Players.Jogador;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

public class Jogo{
    private Equipa ATeam;
    private Equipa BTeam;
    private ParInteiros resultado;
    private LocalDate data;

    public Jogo(){
        this.ATeam = new Equipa();
        this.BTeam = new Equipa();
        resultado=new ParInteiros();
        data= LocalDate.now();
    }

    public Jogo(Equipa ATeam, Equipa BTeam,LocalDate data){
        this.ATeam=ATeam;
        this.BTeam=BTeam;
        this.data=data;
        this.resultado=new ParInteiros();
    }

    public Jogo(Equipa ATeam, Equipa BTeam,LocalDate data,int gA,int gB){
        this.ATeam=ATeam;
        this.BTeam=BTeam;
        this.data=data;
        this.resultado=new ParInteiros(gA,gB);
    }

    public Jogo(Equipa ATeam, Equipa BTeam,LocalDate data,ParInteiros resultado){
        this.ATeam=ATeam;
        this.BTeam=BTeam;
        this.data=data;
        this.resultado=new ParInteiros(resultado);
    }

    public Jogo(Jogo j){
        this.ATeam=j.getATeam();
        this.BTeam=j.getBTeam();
        this.data=j.getData();
        this.resultado=j.getResultado();
    }

    public boolean equals(Object j){
        if(j==null)return false;
        if(j==this)return true;
        if(this.getClass()!=j.getClass())return false;
        Jogo novo = (Jogo)j;
        return novo.getATeam().equals(this.getATeam())
                && novo.getBTeam().equals(this.getBTeam())
                && novo.getData().equals(this.data)
                && novo.getResultado().equals(this.resultado);
    }

    public String toString(){
        StringBuilder sb= new StringBuilder();
        sb.append("Jogo:").append(this.getATeam().getNome()).append(",").append(this.getBTeam().getNome()).append(",");
        sb.append(this.resultado.toString()).append(",").append(data.toString()).append(",");
        String prefix="";
        for(Integer j:ATeam.getTitulares()){
            sb.append(prefix);
            prefix=",";
            sb.append(j);
        }
        for(Integer j:BTeam.getTitulares()){
            sb.append(prefix);
            prefix=",";
            sb.append(j);
        }
        return sb.toString();
    }

    public Jogo clone(){
        return new Jogo(this);
    }

    //Gets e Sets

    public Equipa getATeam() {
        return ATeam.clone();
    }

    public void setATeam(Equipa ATeam) {
        this.ATeam = ATeam.clone();
    }

    public Equipa getBTeam() {
        return BTeam.clone();
    }

    public void setBTeam(Equipa BTeam) {
        this.BTeam = BTeam.clone();
    }

    public ParInteiros getResultado(){
        return resultado.clone();
    }

    public void setResultado(ParInteiros resultado){
        this.resultado=resultado.clone();
    }

    public LocalDate getData(){
        return data;
    }

    public void setData(LocalDate data){
        this.data=data;
    }

    //

    //Tive a fazer aproximações e cheguei esta funções para calcular um possivel resultado final, podemos discutir
    //isto todos juntos se tiverem outras ideias
    public void resultadoFinal(){
        int rateA=ATeam.calculaRatingTotal();
        int rateB=BTeam.calculaRatingTotal();
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
        }
        else if(result<(chanceA+chanceE)){
            calculaGolos(2,r);
        }
        else{
            calculaGolos(1,r);
        }
    }

    private void calculaGolos(int res,Random r){
        int gA;
        int gB;
        double diffA=1.5+(this.ATeam.ataque()-this.BTeam.defesa()*1.2)/20;
        double diffB=1.5+(this.BTeam.ataque()-this.ATeam.defesa()*1.2)/20;
        if(diffA<0)diffA=0;
        if(diffB<0)diffB=0;
        if(res==0){
            do{
                gA=(int)Math.round(diffA+r.nextGaussian()*diffA);
                gB=(int)Math.round(diffB+r.nextGaussian()*diffB);
            }while(gA<=gB||gA<=0||gB<0);
        }
        if(res==1){
            do{
                gA=(int)Math.round(diffA+r.nextGaussian()*diffA);
                gB=(int)Math.round(diffB+r.nextGaussian()*diffB);
            }while(gB<=gA||gA<0||gB<=0);
        }
        else{
            do{
                gA=(int)Math.round(diffA+r.nextGaussian()*diffA);
                gB=(int)Math.round(diffB+r.nextGaussian()*diffB);
            }while (gB!=gA);
            gA=Math.round(gA+gB)/2;
            gB=gA;
        }
        this.resultado.setX(gA);
        this.resultado.setY(gB);
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
