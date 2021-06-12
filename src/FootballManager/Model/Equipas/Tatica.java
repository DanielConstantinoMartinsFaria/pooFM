package FootballManager.Model.Equipas;


import FootballManager.Model.Eventos.Ataque;
import FootballManager.Model.Exceptions.EventoInvalidoException;
import FootballManager.Model.Exceptions.TaticaInvalidaException;
import FootballManager.Model.Jogadores.*;

import java.io.Serializable;
import java.util.*;

public abstract class Tatica implements Serializable {
    private Integer[] titulares;
    private Integer[] suplentes;

    public Tatica(){
        titulares=new Integer[11];
        suplentes=new Integer[7];
    }

    public Tatica(Tatica t){
        this.suplentes=(t.getSuplentes());
        this.titulares=(t.getTitulares());
    }

    public abstract Tatica clone();

    protected void setSuplentes(Integer[] suplentes){
        this.suplentes=suplentes.clone();
    }

    protected void setTitulares(Integer[] titulares){
        this.titulares=titulares.clone();
    }

    public Integer[] getTitulares(){
        return titulares.clone();
    }

    public Integer[] getSuplentes(){
        return suplentes.clone();
    }

    public abstract double ataque(Equipa team);

    public abstract double defesa(Equipa team);

    public void setJogador(Jogador j,int pos,boolean titulares) throws TaticaInvalidaException {
        int in=j.getNumero();
        if(titulares){
            if(pos>10)throw new TaticaInvalidaException();
            if(this.compatible(j,pos)){
                this.titulares[pos] = in;
            }
            else throw new TaticaInvalidaException();
        }
        else {
            if(pos>6)throw new TaticaInvalidaException();
            this.suplentes[pos] = in;
        }
    }

    public void substituicao(Jogador in,Jogador out){
        int pos1=-1;
        for(int i=0;i<11&&(pos1==-1);i++){
            if(out.getNumero()==this.getTitulares()[i])pos1=i;
        }
        int pos2=-1;
        for(int i=0;i<7&&(pos2==-1);i++){
            if(in.getNumero()==this.getSuplentes()[i])pos2=i;
        }
        if(pos1!=-1&&pos2!=-1){
            try{
                if(compatible(in,pos1)){
                    this.setJogador(in,pos1,true);
                    this.setJogador(out,pos2,false);
                }
                else throw new TaticaInvalidaException(this.getClass().getSimpleName(),"tentou realizar uma substituicao incompativel");
            } catch (TaticaInvalidaException e) {
                e.printStackTrace();
            }
        }
    }

    public abstract boolean compatible(Jogador j, int pos);

    public abstract double ratioCruzamento();

    public void randomTatica(Equipa team) throws TaticaInvalidaException {
        Map<Integer,Jogador> jogadores= team.getJogadores();
        Set<Integer>usados=new TreeSet<>();
        List<Integer>lista;
        Random r=new Random();
        int rand;
        for(int i=0;i<11;i++){
            int n=0;
            lista=new ArrayList<>();
            for(Jogador j:jogadores.values()){
                if(this.compatible(j,i)&&!usados.contains(j.getNumero())){
                    n++;
                    lista.add(j.getNumero());
                }
            }
            if(n==0)throw new TaticaInvalidaException("Equipa incompativel com tatica");
            rand=lista.get(Math.abs(r.nextInt()%n));
            this.setJogador(jogadores.get(rand), i, true);
            usados.add(n);
        }
    }

    public abstract int randomPlayer(Ataque evento) throws EventoInvalidoException;

    public void printCompatible(Equipa team, int pos, Set<Integer> adicionados){
        for(Jogador j:team.getJogadores().values()){
            if(this.compatible(j,pos)&&!adicionados.contains(j.getNumero())) {
                System.out.println(j.prettyString());
            }
        }
    }

    public void printMissing(Equipa team){
        for(Jogador j:team.getJogadores().values()){
            if(Arrays.stream(titulares).noneMatch(a->a!=null&&a==j.getNumero()) && Arrays.stream(suplentes).noneMatch(a->a!=null&&a==j.getNumero())){
                System.out.println(j.prettyString());
            }
        }
    }

    public abstract String nomePosicao(int pos) throws TaticaInvalidaException;

    public abstract String plantelTatica();
}
