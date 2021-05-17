package FootballManager;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        ArrayList<String> equipas = new ArrayList<>();
        Avancados ronaldo = new Avancados("Cristiano Ronaldo",7,89,84,87,95,86,95,81,equipas);
        Avancados felix = new Avancados("Joao Felix",7,81,75,85,79,80,82,77,equipas);
        /*
        ronaldo.addEquipa("Andorinha");
        ronaldo.addEquipa("Nacional");
        ronaldo.addEquipa("Sporting");
        ronaldo.addEquipa("Manchester United");
        ronaldo.addEquipa("Real Madrid");
        ronaldo.addEquipa("Juventus");
        System.out.println(ronaldo);
        */
        Equipa juventus = new Equipa();
        juventus.setNome("Juventus");
        juventus.addJogador(ronaldo);
        System.out.println(juventus);

        Equipa atletico = new Equipa();
        atletico.setNome("Atletico de Madrid");
        atletico.addJogador(felix);
        System.out.println(atletico);

        Jogo juveAtl = new Jogo(juventus,atletico);
        System.out.println(juveAtl);
    }

}


/*
Juventus
        ArrayList<String> equipas = new ArrayList<>();
        Avancados ronaldo = new Avancados("Cristiano Ronaldo",7,89,84,87,95,86,95,81,equipas);
        Avancados morata = new Avancados("Alvaro Morata","Espanhola",80,77,72,84,80,80,71,equipas);

        Medios chiesa = new Medios("Federico Chiesa","Italiana",85,74,86,53,68,74,71,82,equipas);
        Medios rabiot = new Medios("Adrien Rabiot","Francesa",72,77,75,73,72,80,78,80,equipas);
        Medios bentancur = new Medios("Rodrigo Bentancur","Argentina",72,87,68,73,60,65,79,81,equipas);
        Medios cuadrado = new Medios("Juan Cuadrado","Colombiano",89,71,91,74,70,84,78,85,equipas);

        Laterais sandro = new Laterais("Alex Sandro","Brasileira",81,90,78,79,68,80,77,84,equipas);
        Laterais danilo = new Laterais("Danilo Silva","Brasileira",69,75,67,75,73,84,79,78,equipas);

        Defesas deligt = new Defesas("Matthijs de Ligt","Holandesa",72,76,56,84,45,82,75,equipas);
        Defesas chiellini = new Defesas("Giorgio Chiellini","Italiana",66,54,57,87,45,78,65,equipas);

        GuardaRedes szczesny = new GuardaRedes("Wojciech Szczesny","Polaca",86,45,88,86,14,73,32,87,equipas);

        ArrayList<Jogador> titulares1=new ArrayList<>();
        ArrayList<Jogador> suplentes1=new ArrayList<>();
        ArrayList<Jogador> reservas1=new ArrayList<>();
        titulares1.add(ronaldo);
        titulares1.add(rabiot);
        titulares1.add(morata);
        titulares1.add(chiellini);
        titulares1.add(chiesa);
        titulares1.add(danilo);
        titulares1.add(sandro);
        titulares1.add(deligt);
        titulares1.add(szczesny);
        titulares1.add(bentancur);
        titulares1.add(cuadrado);
        Equipa juventus = new Equipa("Serie A","Juventus",titulares1,suplentes1,reservas1);

        System.out.println("Ronaldo:"+ronaldo.calculaRatingTotal());
        System.out.println("Morata:"+morata.calculaRatingTotal());
        System.out.println("Chiesa:"+chiesa.calculaRatingTotal());
        System.out.println("Rabiot:"+rabiot.calculaRatingTotal());
        System.out.println("Bentancur:"+bentancur.calculaRatingTotal());
        System.out.println("Cuadrado:"+cuadrado.calculaRatingTotal());
        System.out.println("Sandro:"+sandro.calculaRatingTotal());
        System.out.println("Danilo:"+danilo.calculaRatingTotal());
        System.out.println("Danilo:"+danilo.calculaRatingTotal());
        System.out.println("Danilo:"+danilo.calculaRatingTotal());
        System.out.println("Chiellini:"+chiellini.calculaRatingTotal());
        System.out.println("Szczensy:"+szczesny.calculaRatingTotal());

        System.out.println();
        System.out.println("Juventus(Média):"+(ronaldo.calculaRatingTotal()+rabiot.calculaRatingTotal()+morata.calculaRatingTotal()+chiellini.calculaRatingTotal()+chiesa.calculaRatingTotal()+danilo.calculaRatingTotal()+sandro.calculaRatingTotal()+deligt.calculaRatingTotal()+szczesny.calculaRatingTotal()+bentancur.calculaRatingTotal()+cuadrado.calculaRatingTotal())/11);
        System.out.println("Juventus:"+juventus.calculaRatingTotal());
        System.out.println("Atk:"+juventus.ataque()+"Def:"+juventus.defesa());
        System.out.println("|-----------------------------------------------------|");

 */



/*
Atletico
        Avancados felix = new Avancados("Joao Felix","Portuguesa",81,75,85,79,80,82,77,84,equipas);
        Avancados suarez = new Avancados("Luis Suarez","Argentina",70,78,76,69,90,89,83,91,equipas);

        Medios carrasco = new Medios("Yannick Carrasco","Belga",91,76,90,71,68,85,76,82,equipas);
        Medios trippier = new Medios("Kieran Trippier","Ingles",73,88,73,76,58,75,80,83,equipas);
        Medios lemar = new Medios("Thomas Lemar","Francesa",80,74,88,69,76,79,81,85,equipas);
        Medios llorente = new Medios("Marcos Llorente","Espanhola",84,86,75,66,62,75,84,83,equipas);
        Medios koke = new Medios("Koke","Espanhola",67,94,73,63,56,83,85,84,equipas);

        Defesas gimenez = new Defesas("Jose Gimenez","Argentina",67,64,54,90,30,72,70,90,equipas);
        Defesas savic = new Defesas("Stefan Savic","Montenegrense",61,48,60,86,27,43,71,79,equipas);
        Defesas hermoso = new Defesas("Mario Hermoso","Espanhola",75,76,75,86,41,65,77,79,equipas);

        GuardaRedes oblak = new GuardaRedes("Jan Oblak","Eslovena",87,41,90,87,13,78,43,90,equipas);

        ArrayList<Jogador> titulares2=new ArrayList<>();
        ArrayList<Jogador> suplentes2=new ArrayList<>();
        ArrayList<Jogador> reservas2=new ArrayList<>();
        titulares2.add(felix);
        titulares2.add(suarez);
        titulares2.add(carrasco);
        titulares2.add(trippier);
        titulares2.add(lemar);
        titulares2.add(llorente);
        titulares2.add(koke);
        titulares2.add(gimenez);
        titulares2.add(savic);
        titulares2.add(hermoso);
        titulares2.add(oblak);
        Equipa atletico = new Equipa("Espanhola","Atletico de Madrid",titulares2,suplentes2,reservas2);

        System.out.println("Felix:"+felix.calculaRatingTotal());
        System.out.println("Suarez:"+suarez.calculaRatingTotal());
        System.out.println("Carrasco:"+carrasco.calculaRatingTotal());
        System.out.println("Trippier:"+trippier.calculaRatingTotal());
        System.out.println("Lemar:"+lemar.calculaRatingTotal());
        System.out.println("Llorente:"+llorente.calculaRatingTotal());
        System.out.println("Koke:"+koke.calculaRatingTotal());
        System.out.println("Gimenez:"+gimenez.calculaRatingTotal());
        System.out.println("Savic:"+savic.calculaRatingTotal());
        System.out.println("Hermoso:"+hermoso.calculaRatingTotal());
        System.out.println("Oblak:"+oblak.calculaRatingTotal());

        System.out.println();
        System.out.println("Atletico(Média):"+(felix.calculaRatingTotal()+suarez.calculaRatingTotal()+carrasco.calculaRatingTotal()+trippier.calculaRatingTotal()+lemar.calculaRatingTotal()+llorente.calculaRatingTotal()+koke.calculaRatingTotal()+gimenez.calculaRatingTotal()+savic.calculaRatingTotal()+hermoso.calculaRatingTotal()+oblak.calculaRatingTotal())/11);
        System.out.println("Atletico:"+atletico.calculaRatingTotal());
        System.out.println("Atk:"+atletico.ataque()+"Def:"+atletico.defesa());
        System.out.println("|-----------------------------------------------------|");
 */

/*
        Jogo juveVsAtl = new Jogo(juventus,atletico);

        int res=juveVsAtl.resultadoFinal();

        System.out.print("Juventus:"+juveVsAtl.getGolosA()+" VS ");
        System.out.println("Atlético de Madrid:"+juveVsAtl.getGolosB());
*/