package FootballManager.Model;

import FootballManager.Model.Auxiliares.ParInteiros;
import FootballManager.Model.Equipas.Equipa;
import FootballManager.Model.Exceptions.EquipaInvalidaException;
import FootballManager.Model.Exceptions.JogadorInexistenteException;
import FootballManager.Model.Exceptions.JogoInvalidoException;
import FootballManager.Model.Exceptions.TaticaInvalidaException;

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
        else return data.compareTo(o.data);
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

    public String prettyString(Equipa A, Equipa B) throws EquipaInvalidaException, JogadorInexistenteException {
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
            else throw new EquipaInvalidaException(B.getNome());
        }
        else throw new EquipaInvalidaException(A.getNome());
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

    private void resultadoFinal(Equipa ATeam,Equipa BTeam) throws TaticaInvalidaException {

        int rateA=ATeam.calculaRatingTotal();
        int rateB=BTeam.calculaRatingTotal();
        double chanceA;
        double chanceE;
        if(rateA>rateB){
            chanceA=(40.0+(ATeam.calculaRatingTotal()-BTeam.calculaRatingTotal())*1.2)/100.0;
            chanceE=(20.0-(ATeam.calculaRatingTotal()-BTeam.calculaRatingTotal())*0.4)/100.0;
        }
        else{
            chanceA=(40.0-(BTeam.calculaRatingTotal()-ATeam.calculaRatingTotal())*0.8)/100.0;
            chanceE=(20.0-(BTeam.calculaRatingTotal()-ATeam.calculaRatingTotal())*0.4)/100.0;
        }
        Random r = new Random();
        double result = r.nextDouble();

        if(result<chanceA){
            calculaGolos(0,r,ATeam,BTeam);
        }
        else if(result<(chanceA+chanceE)){
            calculaGolos(2,r,ATeam,BTeam);
        }
        else{
            calculaGolos(1,r,ATeam,BTeam);
        }
    }

    private void calculaGolos(int res,Random r,Equipa ATeam,Equipa BTeam) throws TaticaInvalidaException {
        int gA;
        int gB;
        double diffA=1.5+(ATeam.ataque()-BTeam.defesa()*1.2)/20;
        double diffB=1.5+(BTeam.ataque()-ATeam.defesa()*1.2)/20;
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

    public ParInteiros simulador(Equipa ATeam,Equipa BTeam) throws EquipaInvalidaException, JogoInvalidoException, TaticaInvalidaException {
        if(done)throw new JogoInvalidoException("Jogo já foi realizado");
        if(ATeam==null)throw new EquipaInvalidaException("Equipa A inexistente");
        if(BTeam==null)throw new EquipaInvalidaException("Equipa B inexistente");
        if(!ATeam.getNome().equals(this.ATeam))throw new EquipaInvalidaException("Equipa "+ATeam.getNome()+" nao pertence ao jogo");
        if(!BTeam.getNome().equals(this.BTeam))throw new EquipaInvalidaException("Equipa "+BTeam.getNome()+" nao pertence ao jogo");
        this.resultadoFinal(ATeam,BTeam);
        done=true;
        return resultado.clone();
    }

    public void addSub(int in,int out,boolean which){
        if(which)ATeamSubs.add(new ParInteiros(out,in));
        else BTeamSubs.add(new ParInteiros(out,in));
    }
}