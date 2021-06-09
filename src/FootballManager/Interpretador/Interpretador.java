package FootballManager.Interpretador;

import FootballManager.Model.Auxiliares.ParInteiros;
import FootballManager.Model.Equipas.Equipa;
import FootballManager.Model.Equipas.Taticas.*;
import FootballManager.Model.Estado;
import FootballManager.Model.Eventos.Ataque;
import FootballManager.Model.Exceptions.*;
import FootballManager.Model.Jogo;
import FootballManager.Model.Players.Jogador;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
        String cmd;
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
                case "showall"-> showAll();
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
                    boolean loooooooop=true;
                    while(loooooooop){
                        if(fullLine.length==4){
                            try{
                                LocalDate data=LocalDate.parse(fullLine[3]);
                                System.out.println("Deseja realizar a simulacao em tempo real?(Y/N)");
                                String option=in.nextLine();
                                if(option.equalsIgnoreCase("y")){
                                    this.simulaResultado(fullLine[1],fullLine[2],data,false);
                                    loooooooop=false;
                                }
                                else if(option.equalsIgnoreCase("n")){
                                    this.simulaResultado(fullLine[1],fullLine[2],data,true);
                                    loooooooop=false;
                                }else System.out.println("Opcao invalida");
                            }catch (DateTimeParseException e){
                                System.out.println("Data Inválida");
                                loooooooop=false;
                            }
                        }
                        else loooooooop=false;
                    }
                }
                case "transferencia"-> transferencia();
                default -> System.out.println("Comando Invalido");
            }
            System.out.println("-".repeat(60));
        } while (loop);
        System.out.println();
    }

    public String helpView(){
        return """
                COMANDOS:
                exit: Sai do programa
                help: Exibe este menu
                read : Le um ficheiro no formato especificado
                show: Imprime no ecra uma equipa ou jogo
                showAll: Imprime todos os jogos e equipas, simplificados
                simul: Simula um jogo
                transferencia: Inicia o processo de transferencia de um jogador
                Exemplo - show(Equipa A,Equipa B,2021-04-27)""";
    }

    public String helpView(String cmd){
        StringBuilder sb = new StringBuilder();
        switch (cmd){
            case "read" ->{
                sb.append("(<Nome do ficheiro>,Binario) Le o estado guardado em ficheiro de formato binario\n");
                sb.append("(<Nome do ficheiro>,Texto) Le o estado guardado em ficheiro de formato textual\n");
            }
            case "save" ->{
                sb.append("(<Nome do ficheiro>,Binario) Escreve o estado para um ficheiro de formato binario\n");
                sb.append("(<Nome do ficheiro>,Texto) Escreve o estado para um ficheiro de formato textual\n");
            }
            case "show" ->{
                sb.append("(<Nome de Equipa>): Imprime informacao sobre a equipa especificada\n");
                sb.append("(<Nome de Equipa A>,<Nome da Equipa B>,<Data>): Imprime informacao sobre o jogo especificado\n");
            }
            case "simul" -> sb.append("(<Nome de Equipa A>,<Nome da Equipa B>,<Data>):Simula o um jogo entre as equipas especificadas\n");
            default -> sb.append("Comando inválido\n");
        }
        return sb.toString();
    }

    public void readFile(String pathname,boolean isBin){
        try{
            if(isBin)estado.readBinary(pathname);
            else estado.readText(pathname);
        } catch (IOException | ClassNotFoundException | EquipaInvalidaException | ExcessoJogadoresException e) {
            System.out.println("Ficheiro "+e.getMessage()+" nao encontrado");
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
        System.out.println("-".repeat(60));
        try{
            System.out.println(estado.getEquipa(equipa).prettyString());
        } catch (EquipaInvalidaException e) {
            System.out.println(e.getMessage());
        }
    }

    public void show(String ATeam, String BTeam, LocalDate data){
        System.out.println("-".repeat(60));
        try{
            System.out.println(estado.getJogo(ATeam,BTeam,data)
                    .prettyString(estado.getEquipa(ATeam),estado.getEquipa(BTeam)));
        } catch (JogoInvalidoException | EquipaInvalidaException | JogadorInvalidoException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showAll(){
        System.out.println("-".repeat(60));
        System.out.println(this.estado);
    }

    public void transferencia(){
        Scanner scanner=new Scanner(System.in);
        boolean loop=true;
        while(loop){
            try{
                System.out.println("-".repeat(60));
                System.out.println("Equipa de Saida:");
                String e1=scanner.nextLine();
                Equipa team=estado.getEquipa(e1);

                System.out.println("N do Jogador a ser transferido:");
                for(Jogador j:team.getJogadores().values()){
                    System.out.println(j.getNome()+" |"+j.getNumero()+"| ");
                }
                int nJogador=scanner.nextInt();
                scanner.nextLine();
                System.out.println("-".repeat(60));
                System.out.println("Equipa de Destino:");
                String e2=scanner.nextLine();

                estado.transferencia(nJogador,e1,e2);
                loop=false;
            } catch (EquipaInvalidaException | JogadorInvalidoException | ExcessoJogadoresException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public Tatica criaTatica(Equipa equipa,int nTatica) throws TaticaInvalidaException {
        Tatica tatica;
        if(nTatica==1)tatica=new QuatroQuatroDois();
        else if(nTatica==2)tatica= new QuatroTresTres();
        else if(nTatica==3)tatica=new QuatroDoisTresUm();
        else if(nTatica==4)tatica=new TresQuatroTres();
        else throw new TaticaInvalidaException("Nao disponivel");
        Scanner scanner = new Scanner(System.in);
        Set<Integer> adicionados = new TreeSet<>();
        int numero;
        for(int i=0;i<11;i++){
            System.out.println("-".repeat(60));
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
            } catch (JogadorInvalidoException e) {
                System.out.println("Numero invalido");
                i--;
            }
            catch (TaticaInvalidaException e){
                System.out.println("Jogador invalido");
                i--;
            }
        }

        for(int i=0;i<7;i++){
            int k=(i+1);
            System.out.print("Escolha um jogador para suplente n:"+k+":\n(0 caso nao queira adicionar mais)");
            System.out.print(">");
            numero=scanner.nextInt();
            if(numero==0)i=7;
            else {
                try {
                    Jogador j = equipa.getJogador(numero);
                    tatica.setJogador(j, i, false);
                } catch (JogadorInvalidoException e) {
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

    public String align(int tam,String input){
        int init=tam-input.length();
        return "-".repeat(Math.max(0, init / 2))
                + input +
                "-".repeat(Math.max(0, tam - (init / 2 + input.length())));
    }

    public void setTatica(Equipa team) throws TaticaInvalidaException {
        Scanner scanner=new Scanner(System.in);
        Tatica tatica;
        String teamName=team.getNome();
        boolean looooooop=true;
        System.out.println("-".repeat(60));
        if(team.temTatica()){
            while(looooooop){
                System.out.println(team.plantelTatica());
                System.out.println(teamName+":Deseja alterar a tatica?:(Y/N)");
                String str=scanner.nextLine();
                if(str.equalsIgnoreCase("y")){
                    System.out.println(teamName+":Qual Tática?\n"+qualTatica());
                    tatica=this.criaTatica(team,scanner.nextInt());
                    team.setTatica(tatica);
                    looooooop=false;
                }
                else if(!str.equalsIgnoreCase("n")){
                    System.out.println("Comando invalido\n");
                }
                else looooooop=false;
            }
        }
        else{
            System.out.println(teamName+":Qual Tática?\n"+qualTatica());
            tatica=this.criaTatica(team,scanner.nextInt());
            team.setTatica(tatica);
        }
    }

    public void substituicao(Equipa team,Jogo jogo,boolean which){
        Scanner scanner=new Scanner(System.in);
        int in,out;
        if(which&&jogo.getATeamSubs().size()<3){
            System.out.println("-".repeat(80));
            System.out.print(team.plantelTatica());
            System.out.println("-".repeat(80));
            System.out.print("Jogador que sai:(0 para cancelar)");
            in=scanner.nextInt();
            if(in>0){
                System.out.print("Jogador que entra:(0 para cancelar)");
                out=scanner.nextInt();
                if(out>0){
                    team.substituicao(in,out);
                    jogo.addSub(in,out, true);
                }
            }
        }
        else if(!which&&jogo.getBTeamSubs().size()<3){
            System.out.println("-".repeat(80));
            System.out.print(team.plantelTatica());
            System.out.println("-".repeat(80));
            System.out.print("Jogador que sai:(0 para cancelar)");
            in=scanner.nextInt();
            if(in>0){
                System.out.print("Jogador que entra:(0 para cancelar)");
                out=scanner.nextInt();
                if(out>0){
                    team.substituicao(in,out);
                    jogo.addSub(in,out, false);
                }
            }
        }
        else{
            System.out.print("Máximo de subsituicoes atingido");
        }
    }

    public void simulaResultado(String ATeam,String BTeam, LocalDate data, boolean apenasResultado) {
        Scanner scanner = new Scanner(System.in);
        Jogo jogo=new Jogo(ATeam,BTeam,data);
        try{
            Equipa ateam=estado.getEquipa(ATeam);
            Equipa bteam=estado.getEquipa(BTeam);

            setTatica(ateam);
            jogo.setAPlantel(Arrays.stream(ateam.getTatica().getTitulares()).collect(Collectors.toSet()));

            setTatica(bteam);
            jogo.setBPlantel(Arrays.stream(bteam.getTatica().getTitulares()).collect(Collectors.toSet()));
            if(apenasResultado){
                jogo.simulador(ateam,bteam);
            }
            else{
                ParInteiros resultado = new ParInteiros();
                Random rand=new Random();
                Ataque atk=new Ataque();
                boolean equipa=true;
                boolean sucess;
                String cmd;
                System.out.println(align(80,ATeam+"VS"+BTeam));
                StringBuilder stringBuilder;
                for(int i=0;i<90;i++){
                    stringBuilder=new StringBuilder();
                    if(i==45)System.out.println(align(80,"Intervalo"));
                    int min=i+1;
                    stringBuilder.append(" ".repeat(20)).append(align(40,"Minuto:"+min)).append("\n");
                    if(equipa){
                        sucess=atk.golo(ateam,bteam);
                        stringBuilder.append(atk.getAcontecimento())
                                .append(" para ").append(ATeam).append("...\n");
                    }
                    else {
                        sucess = atk.golo(bteam, ateam);
                        stringBuilder.append(atk.getAcontecimento())
                                .append(" para ").append(BTeam).append("...\n");
                    }
                    if(sucess) {
                        stringBuilder.append("E é golo!\n");
                        if(equipa)resultado.addX(1);
                        else resultado.addY(1);
                        equipa=!equipa;
                    }
                    else {
                        stringBuilder.append("E falhou...\n");
                        equipa = rand.nextBoolean();
                    }
                    stringBuilder.append(align(80,ATeam+" |"+
                            resultado.getX()+"|VS|"+resultado.getY()+"| "+BTeam)).append("\n");
                    if(!atk.getAcontecimento().equals("Tentativa de ataque"))System.out.print(stringBuilder.toString());
                    TimeUnit.MILLISECONDS.sleep(500);

                    if((i+1)%15==0){
                        System.out.println(" ".repeat(20)+align(40,"Pausa:Pressione enter caso queira continuar\n")
                        +" ".repeat(20)+align(40,"Caso queria subsituir um jogador, escreva: sub\n"));

                        while (scanner.hasNextLine()){
                            cmd=scanner.nextLine();
                            if(cmd.isEmpty()){
                                break;
                            }
                            else{
                                if(cmd.equals("sub")){
                                    System.out.print("Qual Equipa?:");
                                    cmd=scanner.nextLine();
                                    if(cmd.equals(ATeam)){
                                        substituicao(ateam,jogo,true);
                                    }
                                    else if(cmd.equals(BTeam)){
                                        substituicao(bteam,jogo,false);
                                    }
                                    else System.out.print("Equipa indisponivel");
                                }
                            }
                        }
                    }

                }
                jogo.setResultado(resultado);
                jogo.setDone(true);
            }
            estado.addEquipa(bteam);
            estado.addEquipa(ateam);
            estado.addJogo(jogo);
            System.out.println("\n\n"+jogo.prettyString(ateam,bteam));
        } catch (JogoInvalidoException | InterruptedException  e) {
            e.printStackTrace();
        } catch (TaticaInvalidaException | EquipaInvalidaException | JogadorInvalidoException e) {
            System.out.println(e.getMessage());
        }
    }

    public String qualTatica(){
        return """
                1:4-4-2
                2:4-3-3
                3:4-2-3-1
                4:3-4-3
                """;
    }

}
