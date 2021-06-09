package FootballManager.Model;

import FootballManager.Model.Auxiliares.ParInteiros;
import FootballManager.Model.Equipas.Equipa;
import FootballManager.Model.Exceptions.*;
import FootballManager.Model.Players.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Estado implements Serializable{
    private Map<String, Equipa> equipas;
    private Set<Jogo> jogos;
    private Map<Integer,Jogador>freelancers;

    //Construtores

    public Estado(){
        equipas=new TreeMap<>();
        jogos=new TreeSet<>();
        freelancers=new TreeMap<>();
    }

    public Estado(Set<Equipa>equipas,Set<Jogo>jogos,Set<Jogador>freelancers){
        this.setEquipas(equipas);
        this.setJogos(jogos);
        this.setFreelancers(freelancers);
    }

    public Estado(Estado estado){
        this.equipas=estado.getEquipas().stream().collect(Collectors.toMap(Equipa :: getNome,j->j));
        this.jogos=estado.getJogos();
        this.freelancers=estado.getFreelancers().stream().collect(Collectors.toMap(Jogador::getNumero,j->j));
    }

    //Defaults

    public Estado clone(){
        return new Estado(this);
    }

    public String toString(){
        StringBuilder sb= new StringBuilder();
        for(Equipa e:equipas.values())sb.append(e).append("\n");
        for(Jogo j:jogos)sb.append(j).append("\n");
        return sb.toString();
    }

    //Getter & Setters

    public Set<Equipa> getEquipas(){
        return this.equipas.values().stream().map(Equipa::clone).collect(Collectors.toSet());
    }

    public Set<Jogo> getJogos(){
        return this.jogos.stream().map(Jogo::clone).collect(Collectors.toSet());
    }

    public Set<Jogador> getFreelancers(){
        return this.freelancers.values().stream().map(Jogador::clone).collect(Collectors.toSet());
    }

    public void setEquipas(Set<Equipa>equipas){
        if(equipas==null)this.equipas=new TreeMap<>();
        else this.equipas=equipas.stream().collect(Collectors.toMap(Equipa::getNome, Equipa::clone));
    }

    public void setJogos(Set<Jogo>jogos){
        if(jogos!=null) this.jogos = jogos.stream().map(Jogo::clone).collect(Collectors.toSet());
        else this.jogos=new TreeSet<>();

    }

    public void setFreelancers(Set<Jogador>freelancers){
        if(freelancers==null)this.freelancers= new TreeMap<>();
        else this.freelancers=freelancers.stream().collect(Collectors.toMap(Jogador::getNumero, Jogador::clone));
    }

    //Escrita

    public void printText(String pathname) throws FileNotFoundException {
        PrintWriter printer = new PrintWriter(pathname);
        printer.print(this.toString());
        printer.flush();
        printer.close();

    }

    public void printBinary(String pathname) throws IOException {
        FileOutputStream fileStream= new FileOutputStream(pathname);
        ObjectOutputStream outputStream = new ObjectOutputStream(fileStream);
        outputStream.writeObject(this);
        outputStream.flush();
        outputStream.close();
    }

    //Leitura

    public void readBinary(String pathname) throws IOException, ClassNotFoundException {
        FileInputStream fileStream= new FileInputStream(pathname);
        ObjectInputStream inputStream = new ObjectInputStream(fileStream);
        Estado novo=(Estado)inputStream.readObject();
        this.equipas=novo.equipas;
        this.jogos=novo.jogos;
        inputStream.close();
    }

    public List<String> lerFicheiro(String pathname) throws IOException {
        return Files.readAllLines(Paths.get(pathname), StandardCharsets.UTF_8);
    }

    public void readText(String pathname) throws IOException, EquipaInvalidaException, ExcessoJogadoresException {
        List<String> linhas = lerFicheiro(pathname);
        String[] linhaPartida;
        Equipa e=null;
        for (String linha : linhas) {
            linhaPartida = linha.split(":", 2);
            switch (linhaPartida[0]) {
                case "Equipa" -> {
                    if (e != null) equipas.put(e.getNome(), e);
                    e = new Equipa();
                    e.setNome(linhaPartida[1]);
                }
                case "Guarda-Redes" -> {
                    if (e == null) throw new EquipaInvalidaException();
                    GuardaRedes g = parseGuardaRedes(linhaPartida[1]);
                    e.addJogador(g);
                }
                case "Avancado" -> {
                    if (e == null) throw new EquipaInvalidaException();
                    Avancados a = parseAvancado(linhaPartida[1]);
                    e.addJogador(a);
                }
                case "Medio" -> {
                    if (e == null) throw new EquipaInvalidaException();
                    Medios m = parseMedio(linhaPartida[1]);
                    e.addJogador(m);
                }
                case "Defesa" -> {
                    if (e == null) throw new EquipaInvalidaException();
                    Defesas d = parseDefesa(linhaPartida[1]);
                    e.addJogador(d);
                }
                case "Lateral" -> {
                    if (e == null) throw new EquipaInvalidaException();
                    Laterais l = parseLateral(linhaPartida[1]);
                    e.addJogador(l);
                }
                case "Jogo" -> {
                    if (e != null) equipas.put(e.getNome(), e);
                    e=null;
                    Jogo j = parseJogo(linhaPartida[1]);
                    jogos.add(j);
                }
                default -> System.out.println("Linha invalida.");

            }
        }
        if(e!=null)equipas.put(e.getNome(),e);
    }

    public GuardaRedes parseGuardaRedes(String input){
        String[] campos=input.split(",");
        return new GuardaRedes(campos[0],Integer.parseInt(campos[1]),Integer.parseInt(campos[2]),Integer.parseInt(campos[3]),Integer.parseInt(campos[4]),Integer.parseInt(campos[5]),Integer.parseInt(campos[6]),Integer.parseInt(campos[7]),Integer.parseInt(campos[8]),Integer.parseInt(campos[9]),new ArrayList<>());
    }

    public Avancados parseAvancado(String input){
        String[] campos=input.split(",");
        return new Avancados(campos[0],Integer.parseInt(campos[1]),Integer.parseInt(campos[2]),Integer.parseInt(campos[3]),Integer.parseInt(campos[4]),Integer.parseInt(campos[5]),Integer.parseInt(campos[6]),Integer.parseInt(campos[7]),Integer.parseInt(campos[8]),new ArrayList<>());
    }

    public Defesas parseDefesa(String input){
        String[] campos=input.split(",");
        return new Defesas(campos[0],Integer.parseInt(campos[1]),Integer.parseInt(campos[2]),Integer.parseInt(campos[3]),Integer.parseInt(campos[4]),Integer.parseInt(campos[5]),Integer.parseInt(campos[6]),Integer.parseInt(campos[7]),Integer.parseInt(campos[8]),new ArrayList<>());
    }

    public Medios parseMedio(String input){
        String[] campos=input.split(",");
        return new Medios(campos[0],Integer.parseInt(campos[1]),Integer.parseInt(campos[2]),Integer.parseInt(campos[3]),Integer.parseInt(campos[4]),Integer.parseInt(campos[5]),Integer.parseInt(campos[6]),Integer.parseInt(campos[7]),Integer.parseInt(campos[8]),Integer.parseInt(campos[9]),new ArrayList<>());
    }

    public Laterais parseLateral(String input){
        String[] campos=input.split(",");
        return new Laterais(campos[0],Integer.parseInt(campos[1]),Integer.parseInt(campos[2]),Integer.parseInt(campos[3]),Integer.parseInt(campos[4]),Integer.parseInt(campos[5]),Integer.parseInt(campos[6]),Integer.parseInt(campos[7]),Integer.parseInt(campos[8]),Integer.parseInt(campos[9]),new ArrayList<>());
    }

    public Jogo parseJogo(String input){
        String[] campos=input.split(",");
        String e1=campos[0];
        String e2=campos[1];
        ParInteiros res= new ParInteiros(Integer.parseInt(campos[2]),Integer.parseInt(campos[3]));
        LocalDate data=LocalDate.parse(campos[4]);
        boolean team=true;
        Set<Integer> ATeam=new TreeSet<>();
        Set<Integer> BTeam=new TreeSet<>();
        Set<ParInteiros> ATeamSubs=new TreeSet<>();
        Set<ParInteiros> BTeamSubs=new TreeSet<>();
        for(int i=5;i<campos.length;i++){
            if(team){
                if(campos[i].contains("-")){
                    String[]par=campos[i].split("->");
                    ATeamSubs.add(new ParInteiros(Integer.parseInt(par[0]),Integer.parseInt(par[1])));
                }
                else{
                    if(ATeam.size()<11)ATeam.add(Integer.parseInt(campos[i]));
                    else {
                        team=false;
                        i--;
                    }
                }
            }
            else{
                if(campos[i].contains("-")){
                    String[]par=campos[i].split("->");
                    BTeamSubs.add(new ParInteiros(Integer.parseInt(par[0]),Integer.parseInt(par[1])));
                }
                else{
                    if(BTeam.size()<11)BTeam.add(Integer.parseInt(campos[i]));
                }
            }
        }
        Jogo novo= new Jogo(e1,e2,data);
        novo.setAPlantel(ATeam);
        novo.setATeamSubs(ATeamSubs);
        novo.setBPlantel(BTeam);
        novo.setBTeamSubs(BTeamSubs);
        novo.setResultado(res);
        novo.setDone(true);
        return novo;
    }

    //Manipulação

    public boolean addJogo(String A,String B,LocalDate data) throws EquipaInvalidaException {
        if(equipas.containsKey(A)){
            if(equipas.containsKey(B)){
                Jogo j = new Jogo(A,B,data);
                jogos.add(j);
                return true;
            }
            else throw new EquipaInvalidaException(B);
        }
        else throw new EquipaInvalidaException(A);
    }

    public boolean addJogo(Jogo j) throws EquipaInvalidaException {
        if(equipas.containsKey(j.getATeam())){
            if(equipas.containsKey(j.getBTeam())){
                jogos.add(j);
                return true;
            }
            else throw new EquipaInvalidaException("Equipa B inexistente");
        }
        else throw new EquipaInvalidaException("Equipa A inexistente");
    }

    public void addEquipa(Equipa e){
        if(equipas.containsKey(e.getNome())){
            equipas.replace(e.getNome(),e);
        }
        else equipas.put(e.getNome(),e.clone());
    }

    public void addJogador(Jogador j,String equipa) {
        equipas.get(equipa).addJogador(j.clone());
    }

    public void transferencia(int nCam,String saida,String destino) throws JogadorInvalidoException, EquipaInvalidaException, ExcessoJogadoresException {
        if(equipas.containsKey(saida)){
            if(equipas.containsKey(destino)){
                Equipa e1=equipas.get(saida);
                Equipa e2=equipas.get(destino);

                Jogador j=e1.getJogador(nCam);
                e1.rmvJogador(j);

                e2.addJogador(j);
            }
        }
        else throw new EquipaInvalidaException(saida," nao encontrada");
    }

    public Jogo getJogo(String ATeam,String BTeam,LocalDate data) throws JogoInvalidoException, EquipaInvalidaException {
        if(!equipas.containsKey(ATeam))throw new EquipaInvalidaException("Equipa "+ATeam+" nao encontrada");
        if(!equipas.containsKey(BTeam))throw new EquipaInvalidaException("Equipa "+BTeam+" nao encontrada");
        for(Jogo j:jogos){
            if(j.getData().equals(data)&&j.getATeam().equals(ATeam)&&j.getBTeam().equals(BTeam)){
                return j;
            }
        }
        throw new JogoInvalidoException("Jogo entre "+ATeam+" e "+BTeam+"no dia "+data+" não encontrado");
    }

    public Equipa getEquipa(String nome) throws EquipaInvalidaException {
        if(equipas.containsKey(nome))return equipas.get(nome).clone();
        else throw new EquipaInvalidaException("Equipa nao encontrada");
    }
}
