package FootballManager.Model;

import FootballManager.Model.Auxiliares.ParInteiros;
import FootballManager.Model.Exceptions.EquipaInexistenteException;
import FootballManager.Model.Exceptions.ExcessoJogadoresException;
import FootballManager.Model.Exceptions.JogadorInexistenteException;
import FootballManager.Model.Exceptions.JogoInvalidoException;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Jogo implements Comparable<Jogo>, Serializable {
    private String ATeam;
    private String BTeam;
    private ParInteiros resultado;
    private LocalDate data;
    private boolean done;
    //
    private Set<Integer>APlantel;
    private Set<Integer>BPlantel;

    private Set<ParInteiros> ATeamSubs;
    private Set<ParInteiros> BTeamSubs;


    public Jogo(){
        ATeam = "";
        BTeam = "";
        resultado=new ParInteiros();
        data= LocalDate.now();
        APlantel = new TreeSet<>();
        BPlantel = new TreeSet<>();
        ATeamSubs = new TreeSet<>();
        BTeamSubs = new TreeSet<>();
        done=false;
    }

    public Jogo(String ATeam,String BTeam,LocalDate data){
        this.ATeam=ATeam;
        this.BTeam=BTeam;
        this.data=data;
        this.resultado=new ParInteiros();
        this.APlantel = new TreeSet<>();
        this.BPlantel = new TreeSet<>();
        this.ATeamSubs = new TreeSet<>();
        this.BTeamSubs = new TreeSet<>();
        this.done=false;
    }

    public Jogo(Jogo j){
        this.ATeam=j.getATeam();
        this.BTeam=j.getBTeam();
        this.data=j.getData();
        this.resultado=j.getResultado();
        this.APlantel=j.getAPlantel();
        this.BPlantel=j.getBPlantel();
        this.ATeamSubs=j.getATeamSubs();
        this.BTeamSubs=j.getBTeamSubs();
        this.done=j.done;
    }

    @Override
    public int compareTo(Jogo o) {
        if(data.compareTo(o.getData())==0){
            if(ATeam.compareTo(o.ATeam)==0){
                return BTeam.compareTo(o.BTeam);
            }
            else return ATeam.compareTo(o.ATeam);
        }
        else return BTeam.compareTo(o.BTeam);
    }

    public boolean equals(Object j){
        if(j==null)return false;
        if(j==this)return true;
        if(this.getClass()!=j.getClass())return false;
        Jogo novo = (Jogo)j;
        return novo.getATeam().equals(this.getATeam())
                && novo.getBTeam().equals(this.getBTeam())
                && novo.getData().equals(this.data)
                && novo.getResultado().equals(this.resultado)
                && this.ATeamSubs.containsAll(novo.getATeamSubs())
                && this.BTeamSubs.containsAll(novo.getBTeamSubs());
    }

    public String prettyString(Equipa A,Equipa B) throws EquipaInexistenteException, JogadorInexistenteException {
        if(A.getNome().equals(ATeam)){
            if(B.getNome().equals(BTeam)){
                StringBuilder sb= new StringBuilder();
                sb.append("Jogo:").append(this.getATeam());
                if(done)sb.append(" |").append(resultado.getX()).append("| ");
                sb.append("VS");
                if(done)sb.append(" |").append(resultado.getX()).append("| ");
                sb.append(this.BTeam).append(" - ").append(data).append("\n");
                sb.append(A.prettyString()).append("\n").append(B.prettyString());
                sb.append("\nSUBS:\n");
                for(ParInteiros p:ATeamSubs){
                    sb.append(A.getJogador(p.getX()).getNome()).append("->").append(A.getJogador(p.getY()).getNome()).append("\n");
                }
                for(ParInteiros p:BTeamSubs){
                    sb.append(B.getJogador(p.getX()).getNome()).append("->").append(B.getJogador(p.getY()).getNome()).append("\n");
                }
                return sb.toString();
            }
            else throw new EquipaInexistenteException(B.getNome());
        }
        else throw new EquipaInexistenteException(A.getNome());
    }

    public String toString(){
        StringBuilder sb= new StringBuilder();
        sb.append("Jogo:").append(this.getATeam()).append(",").append(this.getBTeam()).append(",");
        sb.append(this.resultado.toString()).append(",").append(data.toString()).append(",");
        String prefix="";
        for(Integer j:APlantel){
            sb.append(prefix);
            prefix=",";
            sb.append(j);
        }
        for(ParInteiros p:ATeamSubs){
            sb.append(prefix);
            prefix=",";
            sb.append(p.getX()).append("->").append(p.getY());
        }
        for(Integer j:BPlantel){
            sb.append(prefix);
            prefix=",";
            sb.append(j);
        }
        for(ParInteiros p:BTeamSubs){
            sb.append(prefix);
            prefix=",";
            sb.append(p.getX()).append("->").append(p.getY());
        }
        return sb.toString();
    }

    public Jogo clone(){
        return new Jogo(this);
    }

    //Gets e Sets

    public String getATeam() {
        return ATeam;
    }

    public void setATeam(String ATeam) {
        this.ATeam = ATeam;
    }

    public String getBTeam() {
        return BTeam;
    }

    public void setBTeam(String BTeam) {
        this.BTeam = BTeam;
    }

    public ParInteiros getResultado(){
        return resultado.clone();
    }

    public void setResultado(ParInteiros resultado) {
        this.resultado = resultado.clone();
    }

    public LocalDate getData(){
        return data;
    }

    public void setData(LocalDate data){
        this.data=data;
    }

    public Set<ParInteiros> getATeamSubs(){
        return this.ATeamSubs.stream().map(ParInteiros::clone).collect(Collectors.toSet());
    }

    public void setATeamSubs(Set<ParInteiros>subs){
        this.ATeamSubs=subs.stream().map(ParInteiros::clone).collect(Collectors.toSet());
    }

    public Set<ParInteiros> getBTeamSubs(){
        return this.BTeamSubs.stream().map(ParInteiros::clone).collect(Collectors.toSet());
    }

    public void setBTeamSubs(Set<ParInteiros>subs){
        this.BTeamSubs=subs.stream().map(ParInteiros::clone).collect(Collectors.toSet());
    }

    public Set<Integer> getAPlantel(){
        return new TreeSet<>(this.APlantel);
    }

    public void setAPlantel(Set<Integer> aPlantel){
        this.APlantel = new HashSet<>(aPlantel);
    }

    public Set<Integer> getBPlantel(){
        return new TreeSet<>(this.BPlantel);
    }

    public void setBPlantel(Set<Integer> bPlantel){
        this.BPlantel= new HashSet<>(bPlantel);
    }

    public boolean getDone(){
        return done;
    }

    public void setDone(boolean done){
        this.done=done;
    }

    //

    //Tive a fazer aproximações e cheguei esta funções para calcular um possivel resultado final, podemos discutir
    //isto todos juntos se tiverem outras ideias
    private void resultadoFinal(Equipa ATeam,Equipa BTeam,Set<Integer>ATitulares,Set<Integer>BTitulares){

        int rateA=ATeam.calculaRatingTotal(ATitulares);
        int rateB=BTeam.calculaRatingTotal(BTitulares);
        double chanceA;
        double chanceE;
        if(rateA>rateB){
            chanceA=(40.0+(ATeam.calculaRatingTotal(ATitulares)-BTeam.calculaRatingTotal(BTitulares))*1.2)/100.0;
            chanceE=(20.0-(ATeam.calculaRatingTotal(ATitulares)-BTeam.calculaRatingTotal(BTitulares))*0.4)/100.0;
        }
        else{
            chanceA=(40.0-(BTeam.calculaRatingTotal(BTitulares)-ATeam.calculaRatingTotal(ATitulares))*0.8)/100.0;
            chanceE=(20.0-(BTeam.calculaRatingTotal(BTitulares)-ATeam.calculaRatingTotal(ATitulares))*0.4)/100.0;
        }
        Random r = new Random();
        double result = r.nextDouble();

        if(result<chanceA){
            calculaGolos(0,r,ATeam,BTeam,ATitulares,BTitulares);
        }
        else if(result<(chanceA+chanceE)){
            calculaGolos(2,r,ATeam,BTeam,ATitulares,BTitulares);
        }
        else{
            calculaGolos(1,r,ATeam,BTeam,ATitulares,BTitulares);
        }
    }

    private void calculaGolos(int res,Random r,Equipa ATeam,Equipa BTeam,Set<Integer>ATitulares,Set<Integer>BTitulares){
        int gA;
        int gB;
        double diffA=1.5+(ATeam.ataque(ATitulares)-BTeam.defesa(BTitulares)*1.2)/20;
        double diffB=1.5+(BTeam.ataque(BTitulares)-ATeam.defesa(ATitulares)*1.2)/20;
        if(diffA<0)diffA=0;
        if(diffB<0)diffB=0;
        if(res==0){
            do{
                gA=(int)Math.round(diffA+r.nextGaussian()*diffA);
                gB=(int)Math.round(diffB+r.nextGaussian()*diffB);
            }while(gA<=gB||gA<=0||gB<0);
        }
        else if(res==1){
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

    public ParInteiros simulador(Equipa ATeam,Equipa BTeam,Set<Integer>ATitulares,Set<Integer>BTitulares) throws EquipaInexistenteException, JogoInvalidoException {
        //Este método irá chamar tanto o resultado final, como o simulador passo-a-passo, ainda por implementar

        if(done)throw new JogoInvalidoException("Jogo já foi realizado");
        if(ATeam==null)throw new EquipaInexistenteException("Equipa A inexistente");
        if(BTeam==null)throw new EquipaInexistenteException("Equipa B inexistente");
        if(!ATeam.getNome().equals(this.ATeam))throw new EquipaInexistenteException("Equipa "+ATeam.getNome()+" nao pertence ao jogo");
        if(!BTeam.getNome().equals(this.BTeam))throw new EquipaInexistenteException("Equipa "+BTeam.getNome()+" nao pertence ao jogo");
        this.resultadoFinal(ATeam,BTeam,ATitulares,BTitulares);
        done=true;
        return resultado.clone();
    }

}