import java.util.ArrayList;

public class Jogador{
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

    public Jogador(String nome,String nacionalidade,String clubeAtual,double velocidade,double resistencia,double destreza,double impulsao,double cabeca,double remate,double passe,ArrayList<String> equipas){
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

    public Jogador clone(){
        return new Jogador(this);
    }

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

    //Sub-Classes

    public class GuardaRedes extends Jogador{
        private double elasticidade;

        public GuardaRedes(){
            super();
            this.setElasticidade(0.0);
        }

        public GuardaRedes(String nome,String nacionalidade,String clubeAtual,double velocidade,double resistencia,double destreza,double impulsao,double cabeca,double remate,double passe,double elasticidade,ArrayList<String> equipas) {
            super(nome, nacionalidade, clubeAtual, velocidade, resistencia, destreza, impulsao, cabeca, remate, passe, equipas);
            this.setElasticidade(elasticidade);
        }

        public void setElasticidade(double elasticidade){
            this.elasticidade=elasticidade;
        }

        public double getElasticidade(){
            return this.elasticidade;
        }

        public int calculaRatingTotal(){
            double valor = this.getVelocidade() *0.04;
            valor += this.getResistencia() *0.05;
            valor += this.getDestreza() *0.19;
            valor += this.getImpulsao() *0.23;
            valor += this.getCabeca() *0.02;
            valor += this.getRemate() *0.03;
            valor += this.getPasse() *0.12;
            valor += this.getElasticidade() *0.33 ;
            return (int)Math.round(valor);
        }
    }

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

    public class Avancados extends Jogador{
        public Avancados(){
            super();
        }

        public int calculaRatingTotal(){
            double valor = this.getVelocidade() *0.10;
            valor += this.getResistencia() *0.05;
            valor += this.getDestreza() *0.13;
            valor += this.getImpulsao() *0.22;
            valor += this.getCabeca() *0.22;
            valor += this.getRemate() *0.26;
            valor += this.getPasse() *0.02;
            return (int)Math.round(valor);
        }
    }

    public class Laterais extends Jogador{
        public Laterais(){
            super();
        }

        public int calculaRatingTotal(){
            double valor = this.getVelocidade() *0.28;
            valor += this.getResistencia() *0.20;
            valor += this.getDestreza() *0.16;
            valor += this.getImpulsao() *0.02;
            valor += this.getCabeca() *0.02;
            valor += this.getRemate() *0.10;
            valor += this.getPasse() *0.22;
            return (int)Math.round(valor);
        }
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

    public boolean quimica(Jogador j){
        if(this instanceof GuardaRedes)return j instanceof Defesas && this.getNacionalidade().equals(j.getNacionalidade()) && !this.equals(j);
        else if(this instanceof Defesas)return (j instanceof Defesas || j instanceof GuardaRedes || j instanceof Laterais || j instanceof Medios) && this.getNacionalidade().equals(j.getNacionalidade()) && !this.equals(j);
        else if(this instanceof Medios) return (j instanceof Defesas || j instanceof Medios || j instanceof Avancados || j instanceof Laterais) && this.getNacionalidade().equals(j.getNacionalidade()) && !this.equals(j);
        else if(this instanceof Avancados)return (j instanceof Medios || j instanceof Avancados) && this.getNacionalidade().equals(j.getNacionalidade()) && !this.equals(j);
        else if(this instanceof Laterais)return (j instanceof Defesas || j instanceof Medios) && this.getNacionalidade().equals(j.getNacionalidade()) && !this.equals(j);
        else return false;
    }

    public int calculaRatingTotal(){
        return 0;
    }
}