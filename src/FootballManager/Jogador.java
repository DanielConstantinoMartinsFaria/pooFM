package FootballManager;

import java.util.ArrayList;

public abstract class Jogador{
    private String nome;
    private int velocidade;
    private int resistencia;
    private int destreza;
    private int impulsao;
    private int cabeca;
    private int remate;
    private int passe;
    private int numero;
    private ArrayList<String> equipas;

    //Construtores e afins

    public Jogador(){
        this.nome="";
        this.velocidade=0;
        this.resistencia=0;
        this.destreza=0;
        this.impulsao=0;
        this.cabeca=0;
        this.remate=0;
        this.passe=0;
        this.numero=0;
        this.equipas= new ArrayList<>();
    }

    public Jogador(String nome,int numero,int velocidade,int resistencia,int destreza,int impulsao,int cabeca,int remate,int passe,ArrayList<String> equipas){
        this.setNome(nome);
        this.setVelocidade(velocidade);
        this.setResistencia(resistencia);
        this.setDestreza(destreza);
        this.setImpulsao(impulsao);
        this.setCabeca(cabeca);
        this.setRemate(remate);
        this.setPasse(passe);
        this.setEquipas(equipas);
        this.setNumero(numero);
    }

    public Jogador(Jogador j){
        this.setNome(j.getNome());
        this.setVelocidade(j.getVelocidade());
        this.setResistencia(j.getResistencia());
        this.setDestreza(j.getDestreza());
        this.setImpulsao(j.getImpulsao());
        this.setCabeca(j.getCabeca());
        this.setRemate(j.getRemate());
        this.setPasse(j.getPasse());
        this.setEquipas(j.getEquipas());
        this.setNumero(j.getNumero());
    }

    public abstract Jogador clone();

    public boolean equals(Object jogador){
        if(jogador==null)return false;
        if(jogador==this)return true;
        if(jogador.getClass()!=this.getClass())return false;
        Jogador novo = (Jogador) jogador;
        return (novo.getCabeca()==this.getCabeca()) &&
                (novo.getDestreza()==this.getDestreza()) &&
                (novo.getResistencia())==this.getResistencia() &&
                (novo.getImpulsao()==this.getImpulsao()) &&
                (novo.getPasse()==this.getPasse()) &&
                (novo.getRemate()==this.getRemate()) &&
                (novo.getVelocidade()==this.getVelocidade()) &&
                (novo.getNome().equals(this.getNome()));
    }

    public String toString(){
        StringBuilder sb= new StringBuilder();
        if(this instanceof Medios)sb.append("Medio:");
        else if(this instanceof Defesas)sb.append("Defesa:");
        else if(this instanceof Avancados)sb.append("Avancado:");
        else if(this instanceof Laterais)sb.append("Lateral:");
        else if(this instanceof GuardaRedes)sb.append("Guarda-Redes:");
        sb.append(this.getNome()).append(",");
        sb.append(this.getNumero()).append(",");
        sb.append(this.getVelocidade()).append(",");
        sb.append(this.getResistencia()).append(",");
        sb.append(this.getDestreza()).append(",");
        sb.append(this.getImpulsao()).append(",");
        sb.append(this.getCabeca()).append(",");
        sb.append(this.getRemate()).append(",");
        sb.append(this.getPasse());
        if(this instanceof Laterais) sb.append(",").append(((Laterais) this).getCruzamento());
        else if(this instanceof GuardaRedes) sb.append(",").append(((GuardaRedes) this).getElasticidade());
        else if(this instanceof Medios) sb.append(",").append(((Medios) this).getRecuperacao());
        return sb.toString();
    }

    //Gets e Sets

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public int getResistencia() {
        return this.resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public int getImpulsao() {
        return impulsao;
    }

    public void setImpulsao(int impulsao) {
        this.impulsao = impulsao;
    }

    public int getCabeca() {
        return cabeca;
    }

    public void setCabeca(int cabeca) {
        this.cabeca = cabeca;
    }

    public int getRemate() {
        return remate;
    }

    public void setRemate(int remate) {
        this.remate = remate;
    }

    public int getPasse() {
        return passe;
    }

    public void setPasse(int passe) {
        this.passe = passe;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<String> getEquipas(){
        return new ArrayList<>(this.equipas);
    }

    public void setEquipas(ArrayList<String> equipas){
        this.equipas= (ArrayList<String>) equipas.clone();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        if(numero<100&&numero>0)this.numero=numero;
        else;
    }

    public void addEquipa(String equipa){
        this.equipas.add(equipa);
    }

    public void rmvEquipa(String equipa){
        this.equipas.removeIf(l -> l.equals(equipa));
    }

    public abstract int calculaRatingTotal();
}