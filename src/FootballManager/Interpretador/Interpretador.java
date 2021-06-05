package FootballManager.Interpretador;

import FootballManager.Model.Estado;
import FootballManager.Model.Exceptions.EquipaInexistenteException;
import FootballManager.Model.Exceptions.JogadorInexistenteException;
import FootballManager.Model.Exceptions.JogoInvalidoException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

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

    public boolean interpretador(){
        Scanner in = new Scanner(System.in);
        String[] fullLine;
        String cmd="";
        boolean res=false;
        boolean loop=true;
        do {
            System.out.print(">");
            fullLine = in.nextLine().trim().split("[,()]+");
            cmd = fullLine[0].toLowerCase();
            switch (cmd) {
                case "read" -> {
                    if(fullLine.length==3){
                        if(fullLine[2].equals("Binario"))readFile(fullLine[1],true);
                        else if(fullLine[2].equals("Texto"))readFile(fullLine[1],false);
                        else System.out.println("Formato nao reconhecido");
                    }
                }
                case "save" -> {
                    if(fullLine.length==3){
                        if(fullLine[2].equals("Binario"))saveFile(fullLine[1],true);
                        else if(fullLine[2].equals("Texto"))saveFile(fullLine[1],false);
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
                    res=true;
                    loop=false;
                }
                default -> {
                    System.out.println("Comando Invalido");
                    res = false;
                    loop = false;
                }
            }
        } while (loop);
        System.out.println();
        return res;
    }

    public String helpView(){
        StringBuilder sb = new StringBuilder();
        sb.append("COMANDOS:\n");
        sb.append("exit: Sai do programa\n");
        sb.append("help: Exibe este menu\n");
        sb.append("read : Le um ficheiro no formato especificado\n");
        sb.append("show: Imprime no ecra uma equipa ou jogo\n");
        sb.append("showAll: Imprime todos os jogos e equipas, simplificados\n");
        sb.append("Exemplo - show(Juventus,Atletico de Madrid,2021-04-27");
        return sb.toString();
    }

    public String helpView(String cmd){
        StringBuilder sb = new StringBuilder();
        switch (cmd){
            case "read" ->{
                sb.append("<Nome do ficheiro, \"Binario\"> Le o estado guardado em ficheiro de formato binario");
                sb.append("<Nome do ficheiro, \"Texto\"> Le o estado guardado em ficheiro de formato textual");
            }
            case "show" ->{
                sb.append("<Nome de Equipa>: Imprime informacao sobre a equipa especificada\n");
                sb.append("<Nome de Equipa A, Nome da Equipa B, Data>: Imprime informacao sobre o jogo especificado\n");
            }
            default -> sb.append("Comando inválido\n");
        }
        return sb.toString();
    }

    public void readFile(String pathname,boolean isBin){
        try{
            if(isBin)estado.readBinary(pathname);
            else estado.readText(pathname);
        } catch (IOException | ClassNotFoundException | EquipaInexistenteException e) {
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
        } catch (EquipaInexistenteException e) {
            System.out.println(e.getMessage());
        }
    }

    public void show(String ATeam, String BTeam, LocalDate data){
        try{
            System.out.println(estado.getJogo(ATeam,BTeam,data)
                    .prettyString(estado.getEquipa(ATeam),estado.getEquipa(BTeam)));
        } catch (JogoInvalidoException | EquipaInexistenteException | JogadorInexistenteException e) {
            System.out.println(e.getMessage());
        }
    }
}
