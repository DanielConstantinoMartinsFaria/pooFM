package FootballManager;

import java.util.ArrayList;

public class Equipa {
    private String nome;
    private ArrayList<Jogador> titulares;
    private ArrayList<Jogador> suplentes;
    private ArrayList<Jogador> reservas;
    //Construtores

    public Equipa(){
        this.nome="";
        this.titulares = new ArrayList<>();
        this.suplentes = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    public Equipa(String nome,ArrayList<Jogador>titulares,ArrayList<Jogador>suplentes,ArrayList<Jogador>reservas){
        this.setNome(nome);
        this.setTitulares(titulares);
        this.setSuplentes(suplentes);
        this.setReservas(reservas);
    }

    public Equipa(Equipa eq){
        this.nome=eq.getNome();
        this.titulares=eq.getTitulares();
        this.suplentes=eq.getSuplentes();
        this.reservas=eq.getReservas();
    }

    //Equals, clone, etc...

    public boolean equals(Object eq){
        if(eq==null)return false;
        if(eq==this)return true;
        if(this.getClass()!=eq.getClass())return false;
        Equipa nova = (Equipa) eq;
        return this.getNome().equals(nova.getNome()) &&
                this.getTitulares().equals(nova.getTitulares()) &&
                this.getSuplentes().equals(nova.getSuplentes()) &&
                this.getReservas().equals(nova.getTitulares());
    }

    public Equipa clone(){
        return new Equipa(this);
    }

    public String toString(){
        StringBuilder sb= new StringBuilder();
        sb.append("Equipa:").append(this.getNome()).append("\n");
        String prefix="";
        for(Jogador j:this.titulares){
            sb.append(prefix);
            prefix="\n";
            sb.append(j.toString());
        }
        for(Jogador j:this.suplentes){
            sb.append(prefix);
            prefix="\n";
            sb.append(j.toString());
        }
        for(Jogador j:this.reservas){
            sb.append(prefix);
            prefix="\n";
            sb.append(j.toString());
        }
        return sb.toString();
    }

    //Gets e Sets

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Jogador> getTitulares() {
        return new ArrayList<>(this.titulares);
    }

    public void setTitulares(ArrayList<Jogador> titulares) {
        this.titulares = titulares;
    }

    public ArrayList<Jogador> getSuplentes() {
        return new ArrayList<>(this.suplentes);
    }

    public void setSuplentes(ArrayList<Jogador> suplentes) {
        this.suplentes = suplentes;
    }

    public ArrayList<Jogador> getReservas() {
        return new ArrayList<>(this.reservas);
    }

    public void setReservas(ArrayList<Jogador> reservas) {
        this.reservas = reservas;
    }

    //

    public int numDefesas(){
        int res=0;
        for(Jogador j:this.titulares)if(j instanceof Defesas)res++;
        return res;
    }

    public int numAvancados(){
        int res=0;
        for(Jogador j:this.titulares)if(j instanceof Avancados)res++;
        return res;
    }

    public int numLaterais(){
        int res=0;
        for(Jogador j:this.titulares)if(j instanceof Laterais)res++;
        return res;
    }

    public int numMedios(){
        int res=0;
        for(Jogador j:this.titulares)if(j instanceof Medios)res++;
        return res;
    }

    public int numGuardaRedes(){
        int res=0;
        for(Jogador j:this.titulares)if(j instanceof GuardaRedes)res++;
        return res;
    }

    public int defesa(){
        double valor=0;
        double def= this.numDefesas();
        double gk=this.numGuardaRedes();
        if(gk!=1)return 0;
        double lat=this.numLaterais();
        double med=this.numMedios();
        double total=gk+def+med*0.5+lat*0.67;
        for(Jogador j:this.titulares){
            if(j instanceof GuardaRedes)valor+= j.calculaRatingTotal();
            else if(j instanceof Defesas)valor+= j.calculaRatingTotal();
            else if(j instanceof Laterais)valor+= j.calculaRatingTotal()*0.55;
            else if(j instanceof Medios)valor+= j.calculaRatingTotal()*0.45;
        }
        valor/=total;
        if(valor>100)return 100;
        return (int)Math.round(valor);
    }

    public int ataque(){
        double valor=0;
        if(this.numGuardaRedes()!=1)return 0;
        double atk=this.numAvancados();
        double med=this.numMedios();
        double lat=this.numLaterais();
        double total=atk+med*0.5+lat*0.33;
        for(Jogador j:this.titulares){
            if(j instanceof Avancados)valor+= j.calculaRatingTotal();
            else if(j instanceof Medios)valor+= j.calculaRatingTotal()*0.55;
            else if(j instanceof Laterais)valor+= j.calculaRatingTotal()*0.45;
        }
        valor/=total;
        if(valor>100)return 100;
        return (int)Math.round(valor);
    }

    public int calculaRatingTotal(){
        return (int)((this.ataque()+this.defesa())/2.0);
    }

    public void addJogador(Jogador j){
        if(titulares.size()<11)titulares.add(j);
        else if(suplentes.size()<7)suplentes.add(j);
        else reservas.add(j);
    }

    public boolean rmvJogador(Jogador j){
        return this.reservas.removeIf(l -> l.equals(j)) || this.suplentes.removeIf(l -> l.equals(j)) || this.titulares.removeIf(l -> l.equals(j));
    }

    public void transferencia(Equipa eq,Jogador j){
        if(this.rmvJogador(j))eq.addJogador(j);
    }

    public boolean substituicao(Jogador in, Jogador out){
        boolean success;
        if(success=(this.suplentes.removeIf(j ->j.equals(in)) && this.titulares.removeIf(j ->j.equals(out)))){
                suplentes.add(out);
                titulares.add(in);
        }
        return success;
    }

    public boolean swapBanco(Jogador in,Jogador out){
        boolean success;
        if(success=(this.suplentes.removeIf(j ->j.equals(out)) && this.reservas.removeIf(j ->j.equals(in)))){
            suplentes.add(in);
            reservas.add(out);
        }
        return success;
    }
}
