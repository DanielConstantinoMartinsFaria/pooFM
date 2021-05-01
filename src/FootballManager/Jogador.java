package FootballManager;

import java.util.ArrayList;

public abstract class Jogador{
    private String nome;
    private String nacionalidade;
    private double velocidade;
    private double resistencia;
    private double destreza;
    private double impulsao;
    private double cabeca;
    private double remate;
    private double passe;
    private ArrayList<String> equipas;

    //Construtores e afins

    public Jogador(){
        this.nome="NULL";
        this.nacionalidade="NULL";
        this.velocidade=0.0;
        this.resistencia=0.0;
        this.destreza=0.0;
        this.impulsao=0.0;
        this.cabeca=0.0;
        this.remate=0.0;
        this.passe=0.0;
        this.equipas= new ArrayList<>();
    }

    public Jogador(String nome,String nacionalidade,double velocidade,double resistencia,double destreza,double impulsao,double cabeca,double remate,double passe,ArrayList<String> equipas){
        this.setNome(nome);
        this.setNacionalidade(nacionalidade);
        this.setVelocidade(velocidade);
        this.setResistencia(resistencia);
        this.setDestreza(destreza);
        this.setImpulsao(impulsao);
        this.setCabeca(cabeca);
        this.setRemate(remate);
        this.setPasse(passe);
        this.setEquipas(equipas);
    }

    public Jogador(Jogador j){
        this.setNome(j.getNome());
        this.setNacionalidade(j.getNacionalidade());
        this.setVelocidade(j.getVelocidade());
        this.setResistencia(j.getResistencia());
        this.setDestreza(j.getDestreza());
        this.setImpulsao(j.getImpulsao());
        this.setCabeca(j.getCabeca());
        this.setRemate(j.getRemate());
        this.setPasse(j.getPasse());
        this.setEquipas(j.getEquipas());
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
                (novo.getNome().equals(this.getNome())) &&
                (novo.getNacionalidade().equals(this.getNacionalidade()));
    }

    public String toString(){
        StringBuilder sb= new StringBuilder();
        sb.append("Nome:").append(this.getNome());
        sb.append("->Nacionalidade:").append(this.getNacionalidade());
        sb.append("\nStats:\nTot:|").append(this.calculaRatingTotal()).append("| Vel:|");
        sb.append(this.getVelocidade()).append("| Res:|");
        sb.append(this.getResistencia()).append("| Des:|");
        sb.append(this.getDestreza()).append("|\n Imp:|");
        sb.append(this.getImpulsao()).append("| Cab:|");
        sb.append(this.getCabeca()).append("| Rem:|");
        sb.append(this.getRemate()).append("| Pas:|");
        sb.append(this.getPasse()).append("|\nHistorial:");
        for(String s:this.equipas)sb.append(s).append("-");
        sb.deleteCharAt(sb.length()-1);
        sb.append("\n");
        return sb.toString();
    }

    //Gets e Sets

    public double getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
    }

    public double getResistencia() {
        return this.resistencia;
    }

    public void setResistencia(double resistencia) {
        this.resistencia = resistencia;
    }

    public double getDestreza() {
        return destreza;
    }

    public void setDestreza(double destreza) {
        this.destreza = destreza;
    }

    public double getImpulsao() {
        return impulsao;
    }

    public void setImpulsao(double impulsao) {
        this.impulsao = impulsao;
    }

    public double getCabeca() {
        return cabeca;
    }

    public void setCabeca(double cabeca) {
        this.cabeca = cabeca;
    }

    public double getRemate() {
        return remate;
    }

    public void setRemate(double remate) {
        this.remate = remate;
    }

    public double getPasse() {
        return passe;
    }

    public void setPasse(double passe) {
        this.passe = passe;
    }

    public void setNacionalidade(String nacionalidade){
        this.nacionalidade=nacionalidade;
    }

    public String getNacionalidade(){
        return this.nacionalidade;
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
        this.equipas=equipas;
    }

    public void addEquipa(String equipa){
        this.equipas.add(equipa);
    }

    public void rmvEquipa(String equipa){
        this.equipas.removeIf(l -> l.equals(equipa));
    }

    public abstract boolean quimica(Jogador j);

    public abstract int calculaRatingTotal();
}