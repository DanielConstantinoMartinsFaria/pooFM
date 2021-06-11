package FootballManager.Interpretador;

import FootballManager.Model.Auxiliares.ParInteiros;
import FootballManager.Model.Equipas.Equipa;
import FootballManager.Model.Equipas.Taticas.*;
import FootballManager.Model.Estado;
import FootballManager.Model.Eventos.Ataque;
import FootballManager.Model.Exceptions.*;
import FootballManager.Model.Jogo;
import FootballManager.Model.Players.*;

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
                case "settatica"->setTatica(fullLine[1]);
                case "createequipa"-> criaEquipa();
                case "createjogador"-> criaJogador();
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

    public void criaEquipa(){
        Scanner scanner=new Scanner(System.in);
        String nome;
        boolean loop=true;
        while(loop){
            System.out.print("Nome da Equipa:");
            nome=scanner.nextLine();
            Equipa e=new Equipa(nome, new TreeMap<>());
            try{
                estado.addEquipa(e,false);
                loop=false;
            } catch (EquipaInvalidaException equipaInvalidaException) {
                while(loop){
                    System.out.println(equipaInvalidaException.getMessage()+"\nDeseja subsitituir?(Y/N)");
                    String option = scanner.nextLine();
                    if(option.equalsIgnoreCase("y")){
                        try{
                            estado.addEquipa(e,true);
                            loop=false;
                        } catch (EquipaInvalidaException invalidaException) {
                            invalidaException.printStackTrace();
                        }
                    }
                    else if(option.equalsIgnoreCase("n")){
                        loop=false;
                    }
                    else System.out.println("Opcao invalida");
                }
            }
        }
    }

    public void criaJogador(){
        Scanner scanner=new Scanner(System.in);
        Jogador j=null;
        String nome;
        int numero;
        int velocidade;
        int resistencia;
        int destreza;
        int impulsao;
        int cabeca;
        int remate;
        int passe;
        int pos;
        int special;
        System.out.print("Nome do Jogador:");
        nome=scanner.nextLine();
        System.out.print("Numero do Jogador:");
        numero=scanner.nextInt();
        System.out.print("Velocidade do Jogador:");
        velocidade=scanner.nextInt();
        System.out.print("Resistencia do Jogador:");
        resistencia=scanner.nextInt();
        System.out.print("Destreza do Jogador:");
        destreza=scanner.nextInt();
        System.out.print("Impulsao do Jogador:");
        impulsao=scanner.nextInt();
        System.out.print("Cabeca do Jogador:");
        cabeca=scanner.nextInt();
        System.out.print("Remate do Jogador:");
        remate=scanner.nextInt();
        System.out.print("Passe do Jogador:");
        passe=scanner.nextInt();
        boolean loop=true;
        while(loop){
            System.out.print("""
                    Posicao do Jogador:
                    1.Guarda-Redes 2.Defesa 3.Lateral 4.Medio 5.Avancado
                    """);
            pos=scanner.nextInt();
            try{
                switch (pos){
                    case 1->{
                        System.out.print("Elasticidade do Jogador:");
                        special=scanner.nextInt();
                        j=new GuardaRedes(nome,numero,velocidade,resistencia,destreza,impulsao,cabeca,remate,passe,special,new ArrayList<>());
                        loop=false;
                    }

                    case 2->{
                        System.out.print("Corpo do Jogador:");
                        special=scanner.nextInt();
                        j=new Defesas(nome,numero,velocidade,resistencia,destreza,impulsao,cabeca,remate,passe,special,new ArrayList<>());
                        loop=false;
                    }
                    case 3->{
                        System.out.print("Cruzamento do Jogador:");
                        special=scanner.nextInt();
                        j=new Laterais(nome,numero,velocidade,resistencia,destreza,impulsao,cabeca,remate,passe,special,new ArrayList<>());
                        loop=false;
                    }
                    case 4->{
                        System.out.print("Recuperacao do Jogador:");
                        special=scanner.nextInt();
                        j=new Medios(nome,numero,velocidade,resistencia,destreza,impulsao,cabeca,remate,passe,special,new ArrayList<>());
                        loop=false;
                    }
                    case 5->{
                        System.out.print("Desmarcacao do Jogador:");
                        special=scanner.nextInt();
                        j=new Avancados(nome,numero,velocidade,resistencia,destreza,impulsao,cabeca,remate,passe,special,new ArrayList<>());
                        loop=false;
                    }
                    default-> throw new JogadorInvalidoException("Posicao invalida");
                }
            } catch (JogadorInvalidoException e) {
                System.out.println(e.getMessage());
            }
        }
        loop=true;
        scanner.nextLine();
        while(loop){
            System.out.print("Equipa do Jogador:");
            String equipa=scanner.nextLine();
            try{
                estado.addJogador(j,equipa);
                loop=false;
            } catch (EquipaInvalidaException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void setTatica(String team){
        Scanner scanner=new Scanner(System.in);
        boolean loop=true;
        int nTatica = 0;
        while (loop){
            System.out.println(qualTatica());
            nTatica=scanner.nextInt();
            if(nTatica>0&&nTatica<5)loop=false;
            else System.out.println("Tatica invalida");
        }
        try{
            Equipa e = estado.getEquipa(team);
            e.setTatica(criaTatica(e,nTatica));
            if(e.temTatica())estado.addEquipa(e,true);
        } catch (EquipaInvalidaException | TaticaInvalidaException e) {
            System.out.println(e.getMessage());
        }
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
                createJogador: Inicia o processo de criacao de um jogador
                createEquipa: Inicia o processo de criacao de uma equipa
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
                System.out.print("Escolha um jogador para "+tatica.nomePosicao(i)+":\n"+"0 para cancelar\n");
            } catch (TaticaInvalidaException e) {
                e.printStackTrace();
            }
            tatica.printCompatible(equipa,i,adicionados);
            System.out.print(">");
            numero=scanner.nextInt();
            if(numero==0)return null;
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
        if(!team.temTatica()){
            team.randomTatica();
        }
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
        Set<ParInteiros>subs;
        if(which&&jogo.getATeamSubs().size()<3){
            subs=jogo.getATeamSubs();
            System.out.println("-".repeat(80));
            System.out.print(team.plantelTatica());
            System.out.println("-".repeat(80));
            System.out.print("Jogador que sai:(0 para cancelar)");
            in=scanner.nextInt();
            boolean resIn=subs.stream().anyMatch(j->j.getX()==in);
            if(in>0&&!resIn){
                System.out.print("Jogador que entra:(0 para cancelar)");
                out=scanner.nextInt();
                boolean resOut=subs.stream().anyMatch(j->j.getX()==out);
                if(out>0&&!resOut){
                    team.substituicao(in,out);
                    jogo.addSub(in,out, true);
                }
            }
        }
        else if(!which&&jogo.getBTeamSubs().size()<3){
            subs=jogo.getBTeamSubs();
            System.out.println("-".repeat(80));
            System.out.print(team.plantelTatica());
            System.out.println("-".repeat(80));
            System.out.print("Jogador que sai:(0 para cancelar)");
            in=scanner.nextInt();
            boolean resIn=subs.stream().anyMatch(j->j.getX()==in);
            if(in>0&&!resIn){
                System.out.print("Jogador que entra:(0 para cancelar)");
                out=scanner.nextInt();
                boolean resOut=subs.stream().anyMatch(j->j.getX()==out);
                if(out>0&&!resOut){
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
            if(!ateam.temTatica()) {
                return;
            }
            jogo.setAPlantel(Arrays.stream(ateam.getTatica().getTitulares()).collect(Collectors.toSet()));

            setTatica(bteam);
            if(!bteam.temTatica()) {
                return;
            }
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
                        stringBuilder.append(atk.getAcontecimento()).append("...\n");
                    }
                    else {
                        sucess = atk.golo(bteam, ateam);
                        stringBuilder.append(atk.getAcontecimento()).append("...\n");
                    }
                    if(sucess) {
                        stringBuilder.append(randomSuccess());
                        TimeUnit.MILLISECONDS.sleep(500);
                        if(equipa)resultado.addX(1);
                        else resultado.addY(1);
                        equipa=!equipa;
                    }
                    else {
                        double comm=rand.nextDouble();
                        if(comm<0.15){
                            stringBuilder.append("E passou ao lado...\n");
                        }else if(comm<0.30){
                            stringBuilder.append("A bola vai parar a bancada!\n");
                        }else if(comm<0.5){
                            stringBuilder.append("A equipa adversaria recupera a bola...\n");
                        }else if(comm<0.65){
                            stringBuilder.append("Diretamente para as maos do guarda-redes...\n");
                        }else if(comm<0.80){
                            stringBuilder.append("E falhou... Para onde estava ele a olhar?\n");
                        } else if(comm<0.90){
                            stringBuilder.append("E a bola bate na trave...\n");
                        }else{
                            stringBuilder.append("Em cheioooo... no poste...\n");
                        }
                        equipa = rand.nextBoolean();
                    }
                    stringBuilder.append(align(80,ATeam+" |"+
                            resultado.getX()+"|VS|"+resultado.getY()+"| "+BTeam)).append("\n");
                    if(!atk.getAcontecimento().equals("Tentativa de ataque"))System.out.print(stringBuilder.toString());
                    TimeUnit.MILLISECONDS.sleep(500);

                    if(i%15==0){
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
            estado.addEquipa(bteam,true);
            estado.addEquipa(ateam,true);
            estado.addJogo(jogo);
            System.out.println("\n\n"+jogo.prettyString(ateam,bteam));
        } catch (JogoInvalidoException | InterruptedException  e) {
            e.printStackTrace();
        } catch (TaticaInvalidaException | EquipaInvalidaException | JogadorInvalidoException e) {
            System.out.println(e.getMessage());
        }
    }

    public String randomSuccess(){
        Random rand=new Random();
        double comm=rand.nextDouble();
        if(comm<0.25){
            return "E é GOLO!!!\n";
        }else if(comm<0.5){
            return "A bola entra, na GAVETA!!\n";
        }else if(comm<0.75){
            return "O guarda-redes nada pode fazer!! GOLO!!!\n";
        }else {
            return "GOLOOO, o guarda-redes ainda tocou mas entrou de qualquer forma!\n";
        }
    }

    public String randomFail(){
        Random rand=new Random();
        double comm=rand.nextDouble();
        if(comm<0.10){
            return "E passou ao lado...\n";
        }else if(comm<0.25){
            return "A bola vai parar a bancada!\n";
        }else if(comm<0.45){
            return "A equipa adversaria recupera a bola...\n";
        }else if(comm<0.55){
            return "Diretamente para as maos do guarda-redes...\n";
        }else if(comm<0.65){
            return "E falhou... Para onde estava ele a olhar?\n";
        }else if(comm<0.70){
            return "E a bola bate na trave...\n";
        }else if(comm<0.75){
            return "Em cheioooo... no poste...\n";
        }else if(comm<0.85){
            return "Grande golo... se entrasse...\n";
        }else if(comm<0.95){
            return "Incrivel defesa!!!\n";
        }else{
            return "O guarda-redes parece que voou!!";
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
