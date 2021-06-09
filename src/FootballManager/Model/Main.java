package FootballManager.Model;

import FootballManager.Interpretador.Interpretador;

public class Main{
    public static void main(String[] args){
        Interpretador interpretador= new Interpretador();
        interpretador.run();
    }
}

/*
        Interpretador interpretador= new Interpretador();
        interpretador.run();
*/

/*
        ArrayList<String> equipas = new ArrayList<>();
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
        atletico.setNome("Atletico de Madrid");
        atletico.addJogador(felix);
        atletico.addJogador(suarez);
        atletico.addJogador(carrasco);
        atletico.addJogador(trippier);
        atletico.addJogador(lemar);
        atletico.addJogador(llorente);
        atletico.addJogador(koke);
        atletico.addJogador(gimenez);
        atletico.addJogador(savic);
        atletico.addJogador(hermoso);
        atletico.addJogador(oblak);

        Tatica tatica = new QuatroQuatroDois();
        try {
            tatica.setJogador(oblak,0,true);

            tatica.setJogador(gimenez,1,true);
            tatica.setJogador(trippier,2,true);
            tatica.setJogador(savic,3,true);
            tatica.setJogador(hermoso,4,true);

            tatica.setJogador(koke,5,true);
            tatica.setJogador(lemar,6,true);
            tatica.setJogador(llorente,7,true);
            tatica.setJogador(carrasco,8,true);

            tatica.setJogador(felix,9,true);
            tatica.setJogador(suarez,10,true);
        } catch (JogadorInexistenteException | TaticaInvalidaException e) {
            e.printStackTrace();
        }
        atletico.setTatica(tatica);
 */

/*
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
        juventus.setNome("Juventus");
        juventus.addJogador(ronaldo);
        juventus.addJogador(morata);
        juventus.addJogador(chiesa);
        juventus.addJogador(rabiot);
        juventus.addJogador(bentancur);
        juventus.addJogador(cuadrado);
        juventus.addJogador(sandro);
        juventus.addJogador(danilo);
        juventus.addJogador(deligt);
        juventus.addJogador(chiellini);
        juventus.addJogador(szczesny);

        Tatica tatica2 = new QuatroQuatroDois();
        try{
            tatica2.setJogador(szczesny,0,true);
            tatica2.setJogador(chiesa,1,true);
            tatica2.setJogador(deligt,2,true);
            tatica2.setJogador(sandro,3,true);
            tatica2.setJogador(danilo,4,true);
            tatica2.setJogador(bentancur,5,true);
            tatica2.setJogador(rabiot,6,true);
            tatica2.setJogador(cuadrado,7,true);
            tatica2.setJogador(chiesa,8,true);
            tatica2.setJogador(ronaldo,9,true);
            tatica2.setJogador(morata,10,true);
        } catch (JogadorInexistenteException | TaticaInvalidaException e) {
            e.printStackTrace();
        }
        juventus.setTatica(tatica2);
*/

/*
        Jogo jogo = new Jogo(juventus.getNome(),atletico.getNome(), LocalDate.now());

        Estado estado=new Estado();
        estado.addEquipa(juventus);
        estado.addEquipa(atletico);
        Interpretador interpretador=new Interpretador(estado);

        interpretador.simulaResultado(juventus.getNome(),atletico.getNome(),jogo.getData(),false);
        interpretador.showAll();
 */