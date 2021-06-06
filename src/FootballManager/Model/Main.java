package FootballManager.Model;

import FootballManager.Interpretador.Interpretador;
import FootballManager.Model.Players.Avancados;
import FootballManager.Model.Players.GuardaRedes;
import FootballManager.Model.Players.Jogador;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Main{
    public static void main(String[] args){
        Avancados ronaldo = new Avancados("Cristiano Ronaldo",7,89,84,87,95,86,95,81,new ArrayList<>());
        Map<Integer,Jogador> teste=new TreeMap<>();
        teste.put(7,ronaldo);
        GuardaRedes novo= new GuardaRedes(teste.get(7));
        System.out.println(novo);
    }
}


/*
ArrayList<String> equipas = new ArrayList<>();

        //Juventus
        Avancados ronaldo = new Avancados("Cristiano Ronaldo",7,89,84,87,95,86,95,81,equipas);
        Avancados morata = new Avancados("Alvaro Morata",9,80,77,72,84,80,80,71,equipas);

        Medios chiesa = new Medios("Federico Chiesa",22,85,74,86,53,68,74,71,82,equipas);
        Medios rabiot = new Medios("Adrien Rabiot",25,72,77,75,73,72,80,78,80,equipas);
        Medios bentancur = new Medios("Rodrigo Bentancur",30,72,87,68,73,60,65,79,81,equipas);
        Medios cuadrado = new Medios("Juan Cuadrado",16,89,71,91,74,70,84,78,85,equipas);

        Laterais sandro = new Laterais("Alex Sandro",12,81,90,78,79,68,80,77,84,equipas);
        Laterais danilo = new Laterais("Danilo Silva",13,69,75,67,75,73,84,79,78,equipas);

        Defesas deligt = new Defesas("Matthijs de Ligt",4,72,76,56,84,45,82,75,equipas);
        Defesas chiellini = new Defesas("Giorgio Chiellini",3,66,54,57,87,45,78,65,equipas);

        GuardaRedes szczesny = new GuardaRedes("Wojciech Szczesny",1,86,45,88,86,14,73,32,87,equipas);


        Equipa juventus = new Equipa();
        try{
            juventus.setNome("Juventus");
            juventus.addJogador(ronaldo);
            juventus.add2Titulares(ronaldo.getNumero());
            juventus.addJogador(morata);
            juventus.add2Titulares(morata.getNumero());
            juventus.addJogador(chiesa);
            juventus.add2Titulares(chiesa.getNumero());
            juventus.addJogador(rabiot);
            juventus.add2Titulares(rabiot.getNumero());
            juventus.addJogador(bentancur);
            juventus.add2Titulares(bentancur.getNumero());
            juventus.addJogador(cuadrado);
            juventus.add2Titulares(cuadrado.getNumero());
            juventus.addJogador(sandro);
            juventus.add2Titulares(sandro.getNumero());
            juventus.addJogador(danilo);
            juventus.add2Titulares(danilo.getNumero());
            juventus.addJogador(deligt);
            juventus.add2Titulares(deligt.getNumero());
            juventus.addJogador(chiellini);
            juventus.add2Titulares(chiellini.getNumero());
            juventus.addJogador(szczesny);
            juventus.add2Titulares(szczesny.getNumero());

        } catch (JogadorInexistenteException | ExcessoJogadoresException e) {
            e.printStackTrace();
        }

        //Atletico

        Avancados felix = new Avancados("Joao Felix",7,81,75,85,79,80,82,77,equipas);
        Avancados suarez = new Avancados("Luis Suarez",9,70,78,76,69,90,89,83,equipas);

        Medios carrasco = new Medios("Yannick Carrasco",21,91,76,90,71,68,85,76,82,equipas);
        Medios trippier = new Medios("Kieran Trippier",23,73,88,73,76,58,75,80,83,equipas);
        Medios lemar = new Medios("Thomas Lemar",11,80,74,88,69,76,79,81,85,equipas);
        Medios llorente = new Medios("Marcos Llorente",14,84,86,75,66,62,75,84,83,equipas);
        Medios koke = new Medios("Koke",6,67,94,73,63,56,83,85,84,equipas);

        Defesas gimenez = new Defesas("Jose Gimenez",2,67,64,54,90,30,72,70,equipas);
        Defesas savic = new Defesas("Stefan Savic",15,61,48,60,86,27,43,71,equipas);
        Defesas hermoso = new Defesas("Mario Hermoso",22,75,76,75,86,41,65,77,equipas);

        GuardaRedes oblak = new GuardaRedes("Jan Oblak",13,87,41,90,87,13,78,43,90,equipas);

        Equipa atletico = new Equipa();
        try{
            atletico.setNome("Atletico de Madrid");
            atletico.addJogador(felix);
            atletico.add2Titulares(felix.getNumero());
            atletico.addJogador(suarez);
            atletico.add2Titulares(suarez.getNumero());
            atletico.addJogador(carrasco);
            atletico.add2Titulares(carrasco.getNumero());
            atletico.addJogador(trippier);
            atletico.add2Titulares(trippier.getNumero());
            atletico.addJogador(lemar);
            atletico.add2Titulares(lemar.getNumero());
            atletico.addJogador(llorente);
            atletico.add2Titulares(llorente.getNumero());
            atletico.addJogador(koke);
            atletico.add2Titulares(koke.getNumero());
            atletico.addJogador(gimenez);
            atletico.add2Titulares(gimenez.getNumero());
            atletico.addJogador(savic);
            atletico.add2Titulares(savic.getNumero());
            atletico.addJogador(hermoso);
            atletico.add2Titulares(hermoso.getNumero());
            atletico.addJogador(oblak);
            atletico.add2Titulares(oblak.getNumero());
            atletico.addJogador(sandro);
            atletico.add2Suplentes(sandro.getNumero());
        } catch (JogadorInexistenteException | ExcessoJogadoresException e) {
            e.printStackTrace();
        }

        LocalDate date = LocalDate.now();
        Jogo juveAtl = new Jogo(juventus,atletico,date);
        try{
            juveAtl.substituicao(sandro.getNumero(),savic.getNumero(),atletico);
            ParInteiros res=juveAtl.simulador(juventus,atletico);
        } catch (EquipaInexistenteException | JogoInvalidoException | JogadorInexistenteException | ExcessoJogadoresException e) {
            e.printStackTrace();
        }
        //System.out.println(juveAtl);

        Estado estado = new Estado();

        estado.addEquipa(juventus);
        estado.addEquipa(atletico);
        try{
            estado.addJogo(juveAtl);
        } catch (EquipaInexistenteException e) {
            e.printStackTrace();
        }

        try{
            estado.printText("teste.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Interpretador interpretador = new Interpretador(estado);
*/