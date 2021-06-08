package FootballManager.Interpretador;

import FootballManager.Model.Equipas.Equipa;
import FootballManager.Model.Equipas.Taticas.QuatroQuatroDois;
import FootballManager.Model.Equipas.Taticas.QuatroTresTres;
import FootballManager.Model.Equipas.Taticas.Tatica;
import FootballManager.Model.Estado;
import FootballManager.Model.Exceptions.EquipaInvalidaException;
import FootballManager.Model.Exceptions.JogadorInexistenteException;
import FootballManager.Model.Exceptions.JogoInvalidoException;
import FootballManager.Model.Exceptions.TaticaInvalidaException;
import FootballManager.Model.Jogo;
import FootballManager.Model.Players.Jogador;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Interpretador {
    private Estado estado;

    public Interpretador(){
        estado= new Estado();
    }

    public Interpretador(Estado estado){
        this.estado=estado.clone();
    }

    public void run(){
        interpretador();
    }

    public void interpretador(){
        Scanner in = new Scanner(System.in);
        String[] fullLine;
        String cmd="";
        boolean loop=true;
        do {
            System.out.print(">");
            fullLine = in.nextLine().trim().split("[,()]+");
            cmd = fullLine[0].toLowerCase();
            switch (cmd) {
                case "read" -> {
                    if(fullLine.length==3){
                        if(fullLine[2].equalsIgnoreCase("binario"))readFile(fullLine[1],true);
                        else if(fullLine[2].equalsIgnoreCase("texto"))readFile(fullLine[1],false);
                        else System.out.println("Formato nao reconhecido");
                    }
                }
                case "save" -> {
                    if(fullLine.length==3){
                        if(fullLine[2].equalsIgnoreCase("binario"))saveFile(fullLine[1],true);
                        else if(fullLine[2].equalsIgnoreCase("texto"))saveFile(fullLine[1],false);
                        else System.out.println("Formato nao reconhecido");
                    }
                }
                case "help" -> {
                    if(fullLine.length==2){
                        System.out.println(helpView(fullLine[1]));
                    }
                    else if(fullLine.length==1){
                        System.out.println(helpView());
                    }
                    else{
                        System.out.println("Numero de argumentos invalido");
                    }
                }
                case "showall"->System.out.println(this.estado);
                case "show" -> {
                    if(fullLine.length==2){
                        this.show(fullLine[1]);
                    }
                    else if(fullLine.length==4){
                        this.show(fullLine[1],fullLine[2],LocalDate.parse(fullLine[3]));
                    }
                }
                case "exit" -> {
                    System.out.println("Exiting...");
                    loop=false;
                }
                case "simul" ->{
                    if(fullLine.length==5){
                        if(fullLine[4].equalsIgnoreCase("false")){
                            this.simulaResultado(fullLine[1],fullLine[2],LocalDate.parse(fullLine[3]),true);
                        }
                        else if(fullLine[4].equalsIgnoreCase("true")){
                            //Nao implementado
                            int n=0;
                        }
                    }
                }
                default -> {
                    System.out.println("Comando Invalido");
                }
            }
        } while (loop);
        System.out.println();
    }

    public String helpView(){
        StringBuilder sb = new StringBuilder();
        sb.append("COMANDOS:\n");
        sb.append("exit: Sai do programa\n");
        sb.append("help: Exibe este menu\n");
        sb.append("read : Le um ficheiro no formato especificado\n");
        sb.append("show: Imprime no ecra uma equipa ou jogo\n");
        sb.append("showAll: Imprime todos os jogos e equipas, simplificados\n");
        sb.append("simul:Simula um jogo\n");
        sb.append("Exemplo - show(Juventus,Atletico de Madrid,2021-04-27");
        return sb.toString();
    }

    public String helpView(String cmd){
        StringBuilder sb = new StringBuilder();
        switch (cmd){
            case "read" ->{
                sb.append("(<Nome do ficheiro>,Binario) Le o estado guardado em ficheiro de formato binario");
                sb.append("(<Nome do ficheiro>,Texto) Le o estado guardado em ficheiro de formato textual");
            }
            case "show" ->{
                sb.append("(<Nome de Equipa>): Imprime informacao sobre a equipa especificada\n");
                sb.append("(<Nome de Equipa A>,<Nome da Equipa B>,<Data>): Imprime informacao sobre o jogo especificado\n");
            }
            case "simul" ->{
                sb.append("(<Nome de Equipa A>,<Nome da Equipa B>,<Data>,true):Simula o um jogo, passo a passo, entre as equipas especificadas\n");
                sb.append("(<Nome de Equipa A>,<Nome da Equipa B>,<Data>,false):Simula o resultado de um jogo entre as equipas especificadas\n");
            }
            default -> sb.append("Comando inválido\n");
        }
        return sb.toString();
    }

    public void readFile(String pathname,boolean isBin){
        try{
            if(isBin)estado.readBinary(pathname);
            else estado.readText(pathname);
        } catch (IOException | ClassNotFoundException | EquipaInvalidaException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveFile(String pathname,boolean isBin){
        try{
            if(isBin)estado.printBinary(pathname);
            else estado.printText(pathname);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void show(String equipa){
        try{
            System.out.println(estado.getEquipa(equipa).prettyString());
        } catch (EquipaInvalidaException e) {
            System.out.println(e.getMessage());
        }
    }

    public void show(String ATeam, String BTeam, LocalDate data){
        try{
            System.out.println(estado.getJogo(ATeam,BTeam,data)
                    .prettyString(estado.getEquipa(ATeam),estado.getEquipa(BTeam)));
        } catch (JogoInvalidoException | EquipaInvalidaException | JogadorInexistenteException e) {
            System.out.println(e.getMessage());
        }
    }

    public Tatica criaTatica(Equipa equipa,int nTatica) throws TaticaInvalidaException {
        Tatica tatica;
        if(nTatica==1)tatica=new QuatroQuatroDois();
        else if(nTatica==2)tatica= new QuatroTresTres();
        else throw new TaticaInvalidaException("Nao disponivel");
        Scanner scanner = new Scanner(System.in);
        Set<Integer> adicionados = new TreeSet<>();
        int numero;
        for(int i=0;i<11;i++){
            try{
                System.out.print("Escolha um jogador para "+tatica.nomePosicao(i)+":\n");
            } catch (TaticaInvalidaException e) {
                e.printStackTrace();
            }
            tatica.printCompatible(equipa,i,adicionados);
            System.out.print(">");
            numero=scanner.nextInt();
            try{
                Jogador j=equipa.getJogador(numero);
                tatica.setJogador(j,i,true);
                adicionados.add(numero);
            } catch (JogadorInexistenteException e) {
                System.out.println("Numero invalido");
                i--;
            }
            catch (TaticaInvalidaException e){
                System.out.println("Jogador invalido");
                i--;
            }
        }

        for(int i=0;i<7;i++){
            System.out.print("Escolha um jogador para suplente n"+i+":\n(0 caso nao queira adicionar mais)");
            System.out.print(">");
            numero=scanner.nextInt();
            if(numero==0)i=7;
            else {
                try {
                    Jogador j = equipa.getJogador(numero);
                    tatica.setJogador(j, i, false);
                } catch (JogadorInexistenteException e) {
                    System.out.println("Numero invalido");
                    i--;
                } catch (TaticaInvalidaException e) {
                    System.out.println("Jogador invalido");
                    i--;
                }
            }
        }

        return tatica;
    }

    public void simulaResultado(String ATeam,String BTeam, LocalDate data, boolean apenasResultado) {
        Scanner scanner = new Scanner(System.in);
        boolean looooooop=true;
        try{
            if(apenasResultado){
                System.out.println(ATeam+":Qual Tática?\n"+qualTatica()+"\n");
                Equipa ateam=estado.getEquipa(ATeam);
                if(ateam.temTatica()){
                    while(looooooop){
                        System.out.println("Deseja alterar a tatica?:(Y/N)");
                        String str=scanner.nextLine();
                        if(str.equalsIgnoreCase("y")){
                            Tatica tatica1=this.criaTatica(ateam,scanner.nextInt());
                            ateam.setTatica(tatica1);
                            looooooop=false;
                        }
                        else if(!str.equalsIgnoreCase("n")){
                            System.out.println("Comando invalido\n");
                        }
                    }
                }
                else{
                    Tatica tatica1=this.criaTatica(ateam,scanner.nextInt());
                    ateam.setTatica(tatica1);
                }
                looooooop=true;
                System.out.println(BTeam+":Qual Tática?\n"+qualTatica()+"\n");
                Equipa bteam=estado.getEquipa(BTeam);
                if(bteam.temTatica()){
                    while(looooooop){
                        System.out.println("Deseja alterar a tatica?:(Y/N)");
                        String str=scanner.nextLine();
                        if(str.equalsIgnoreCase("y")){
                            Tatica tatica2=this.criaTatica(bteam,scanner.nextInt());
                            ateam.setTatica(tatica2);
                            looooooop=false;
                        }
                        else if(!str.equalsIgnoreCase("n")){
                            System.out.println("Comando invalido\n");
                        }
                    }
                }
                else{
                    Tatica tatica2=this.criaTatica(bteam,scanner.nextInt());
                    bteam.setTatica(tatica2);
                }
                Jogo jogo=new Jogo(ATeam,BTeam,data);
                jogo.simulador(ateam,bteam);
                estado.addJogo(jogo);
                System.out.println(jogo.prettyString(ateam,bteam));
            }
        } catch (JogoInvalidoException | JogadorInexistenteException e) {
            e.printStackTrace();
        } catch (TaticaInvalidaException | EquipaInvalidaException e) {
            System.out.println(e.getMessage());
        }
    }

    public String qualTatica(){
        return """
                1:4-4-2
                2:4-3-3
                """;
    }

}
