package FootballManager.Model;

import FootballManager.Model.Auxiliares.ParInteiros;
import FootballManager.Model.Exceptions.EquipaInexistenteException;
import FootballManager.Model.Players.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Estado implements Serializable{
    private Set<Equipa> equipas;
    private Set<Jogo> jogos;

    public Estado(){
        equipas=new TreeSet<>();
        jogos=new TreeSet<>();
    }

    public Estado(Set<Equipa>equipas,Set<Jogo>jogos){
        this.setEquipas(equipas);
        this.setJogos(jogos);
    }

    public Estado(Estado estado){
        this.equipas=estado.getEquipas();
        this.jogos=estado.getJogos();
    }

    public Estado clone(){
        return new Estado(this);
    }

    public String toString(){
        StringBuilder sb= new StringBuilder();
        for(Equipa e:equipas)sb.append(e).append("\n");
        for(Jogo j:jogos)sb.append(j).append("\n");
        return sb.toString();
    }

    public Set<Equipa> getEquipas(){
        return this.equipas.stream().map(Equipa::clone).collect(Collectors.toSet());
    }

    public Set<Jogo> getJogos(){
        return this.jogos.stream().map(Jogo::clone).collect(Collectors.toSet());
    }

    public void setEquipas(Set<Equipa>equipas){
        this.equipas=equipas.stream().map(Equipa::clone).collect(Collectors.toSet());
    }

    public void setJogos(Set<Jogo>jogos){
        this.jogos=jogos.stream().map(Jogo::clone).collect(Collectors.toSet());
    }

    public boolean addJogo(Jogo j){
        try{
            if(equipas.stream().map(Equipa::getNome).anyMatch(a->a.equals(j.getATeam()))){
                if(equipas.stream().map(Equipa::getNome).anyMatch(a->a.equals(j.getBTeam()))){
                    jogos.add(j);
                    return true;
                }
                else throw new EquipaInexistenteException("Equipa B inexistente");
            }
            else throw new EquipaInexistenteException("Equipa A inexistente");
        }catch(EquipaInexistenteException e){
            e.printStackTrace();
            return false;
        }
    }

    public void addEquipa(Equipa e){
        equipas.add(e);
    }

    public Equipa getEquipa(String nome){
        try{
            if(equipas.stream().anyMatch(a->a.getNome().equals(nome))){
                for(Equipa e:equipas)if(e.getNome().equals(nome))return e;
                return null;
            }
            else throw new EquipaInexistenteException("Equipa nao encontrada");
        }catch(EquipaInexistenteException e){
            e.printStackTrace();
            return null;
        }
    }

    public void printText(String pathname){
        try{
            PrintWriter printer = new PrintWriter(pathname);
            printer.print(this.toString());
            printer.flush();
            printer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void printBinary(String pathname){
        try{
            FileOutputStream fileStream= new FileOutputStream(pathname);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileStream);
            outputStream.writeObject(this);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readBinary(String pathname){
        try{
            FileInputStream fileStream= new FileInputStream(pathname);
            ObjectInputStream inputStream = new ObjectInputStream(fileStream);
            Estado novo=(Estado)inputStream.readObject();
            this.equipas=novo.getEquipas();
            this.jogos=novo.getJogos();
            inputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<String> lerFicheiro(String pathname) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(pathname), StandardCharsets.UTF_8);
        }
        catch(IOException exc) {
            exc.printStackTrace();
            lines = new ArrayList<>();
        }
        return lines;
    }


    public void readText(String pathname){
        List<String> linhas = lerFicheiro(pathname);
        String[] linhaPartida;
        Equipa e=null;
        for (String linha : linhas) {
            linhaPartida = linha.split(":", 2);
            try {
                if(linhaPartida[0].equals("Equipa")){
                        if(e!=null)equipas.add(e.clone());
                        e = new Equipa();
                        e.setNome(linhaPartida[1]);
                    }
                else if(linhaPartida[0].equals("Guarda-Redes")){
                    if(e==null)throw new EquipaInexistenteException();
                    GuardaRedes g = parseGuardaRedes(linhaPartida[1]);
                    e.addJogador(g);
                }
                else if(linhaPartida[0].equals("Avancado")){
                    if(e==null)throw new EquipaInexistenteException();
                    Avancados a = parseAvancado(linhaPartida[1]);
                    e.addJogador(a);
                }
                else if(linhaPartida[0].equals("Medio")){
                    if(e==null)throw new EquipaInexistenteException();
                    Medios m = parseMedio(linhaPartida[1]);
                    e.addJogador(m);
                }
                else if(linhaPartida[0].equals("Defesa")){
                    if(e==null)throw new EquipaInexistenteException();
                    Defesas d = parseDefesa(linhaPartida[1]);
                    e.addJogador(d);
                }
                else if(linhaPartida[0].equals("Lateral")){
                    if(e==null)throw new EquipaInexistenteException();
                    Laterais l = parseLateral(linhaPartida[1]);
                    e.addJogador(l);
                }
                else if(linhaPartida[0].equals("Jogo")){
                    if(e!=null)equipas.add(e);
                    Jogo j = parseJogo(linhaPartida[1]);
                    jogos.add(j);
                }
                else{
                    System.out.println("Linha invalida.");
                    break;
                }
            } catch (EquipaInexistenteException equipaInexistenteException) {
                equipaInexistenteException.printStackTrace();
            }
        }
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
                if(campos[i].contains("->")){
                    String[]par=campos[i].split("->");
                    ATeamSubs.add(new ParInteiros(Integer.parseInt(par[0]),Integer.parseInt(par[1])));
                    if(!campos[i+1].contains("->")) {
                        team = false;
                    }
                }
                else{
                    ATeam.add(Integer.parseInt(campos[i]));
                }
            }
            else{
                if(campos[i].contains("-")){
                    String[]par=campos[i].split("->");
                    BTeamSubs.add(new ParInteiros(Integer.parseInt(par[0]),Integer.parseInt(par[1])));
                }
                else{
                    BTeam.add(Integer.parseInt(campos[i]));
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

}
