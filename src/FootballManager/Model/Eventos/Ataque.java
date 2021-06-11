package FootballManager.Model.Eventos;

import FootballManager.Model.Equipas.Equipa;
import FootballManager.Model.Exceptions.EventoInvalidoException;
import FootballManager.Model.Exceptions.JogadorInvalidoException;
import FootballManager.Model.Exceptions.TaticaInvalidaException;

import java.util.Random;

public class Ataque {
    private String acontecimento;
    public Ataque(){
        acontecimento="";
    }

    public String getAcontecimento(){
        return acontecimento;
    }

    public boolean golo(Equipa Atacante, Equipa Defensora) throws TaticaInvalidaException {
        Random r=new Random();
        double chance = 0.05+((Atacante.ataque()-Defensora.defesa())/100.0);
        try{
            if(r.nextDouble()<chance){
                if(r.nextDouble()<Atacante.chanceCruzamento()){
                    Cruzamento cruzamento = new Cruzamento();
                    cruzamento.setMarcador(Atacante.getJogador(Atacante.randomPlayer(cruzamento)));
                    double randComm=r.nextDouble();
                    if(randComm<0.25){
                        acontecimento="Cruzamento perigoso por "+cruzamento.marcadorName();
                    } else if(randComm<0.5){
                        acontecimento=cruzamento.marcadorName()+" coloca a bola na area de "+Defensora.getNome();
                    }else if(randComm<0.75){
                        acontecimento="Bola rasteira para "+Atacante.getNome();
                    }else {
                        acontecimento="Cruzamento inesperado por " + cruzamento.marcadorName();
                    }
                    return cruzamento.golo(Atacante, Defensora);
                }else{
                    Remate remate = new Remate();
                    remate.setMarcador(Atacante.getJogador(Atacante.randomPlayer(remate)));
                    double randComm=r.nextDouble();
                    if(randComm<0.25){
                        acontecimento="Jogada bem construida por "+Atacante.getNome()+"...\n";
                    } else if(randComm<0.5){
                        acontecimento=Atacante.getNome()+" troca a bola e chega a area de "+Defensora.getNome()+"!\n";
                    }else if(randComm<0.75){
                        acontecimento=Atacante.getNome()+" coloca bola nas costas da defesa adversaria!\n";
                    }else {
                        acontecimento=Atacante.getNome()+" esta a trocar bem a bola e...\n";
                    }
                    if(randComm<0.333){
                        acontecimento+=remate.marcadorNome()+" remata a baliza e";
                    } else if(randComm<0.667){
                        acontecimento+="Grande remate por parte de "+remate.marcadorNome();
                    }else {
                        acontecimento+= remate.marcadorNome()+" tira o defesa da frente e dispara a baliza";
                    }
                    return remate.golo(Atacante,Defensora);
                }
            }
            else{
                if(r.nextDouble()<0.15){
                    if(r.nextBoolean()){
                        Canto canto = new Canto(r.nextBoolean());
                        canto.setMarcador(Atacante.getJogador(Atacante.randomPlayer(canto)));
                        double randComm=r.nextDouble();
                        if(randComm<0.333){
                            acontecimento="A bola sai pela linha final e e canto para "+Atacante.getNome()+"\n";
                        } else if(randComm<0.667){
                            acontecimento="Canto para"+Atacante.getNome()+"\n";
                        }else {
                            acontecimento="O defesa corta e e canto para " + Atacante.getNome()+"\n";
                        }
                        randComm=r.nextDouble();
                        if(randComm<0.333){
                            acontecimento+=canto.marcadorName()+" coloca a bola junto ao primeiro poste";
                        }else if(randComm<0.667){
                            acontecimento+=canto.marcadorName()+" joga a bola rasteira";
                        }else{
                            acontecimento+="A bola vai alta";
                        }
                        return canto.golo(Atacante,Defensora);
                    }
                    else {
                        double distancia=25+25*r.nextGaussian();
                        if(distancia<16.5)distancia=20+3.5*r.nextGaussian();
                        if(distancia<16.5)distancia=16.5;
                        Livre livre = new Livre((float) distancia);
                        livre.setMarcador(Atacante.getJogador(Atacante.randomPlayer(livre)));
                        double randComm=r.nextDouble();
                        if(randComm<0.333){
                            acontecimento="O defesa parou a jogada como deu, e e livre para "+Atacante.getNome()+".\n";
                        } else if(randComm<0.667){
                            acontecimento="Livre para"+Atacante.getNome()+".\n";
                        }else {
                            acontecimento="O jogador da " + Atacante.getNome()+" cai e e livre!\n";
                        }
                        randComm=r.nextDouble();
                        if(distancia<28){
                            if(randComm<0.5){
                                acontecimento+=livre.marcadorName()+" remata diretamente a baliza";
                            }else{
                                acontecimento+=livre.marcadorName()+" coloca a bola por cima da barreira";
                            }
                        }
                        else{
                            if(randComm<0.5){
                                acontecimento+=livre.marcadorName()+" coloca a bola em jeito de cruzamento";
                            } else {
                                acontecimento+=livre.marcadorName()+" cruza para area de "+Defensora.getNome()+".\n";
                            }
                        }
                        return livre.golo(Atacante,Defensora);
                    }
                }
                else{
                    if(r.nextDouble()<0.8)acontecimento="Tentativa de ataque";
                    else acontecimento="Boa Tentativa de ataque";
                    return false;
                }
            }
        } catch (EventoInvalidoException | JogadorInvalidoException e) {
            e.printStackTrace();
            return false;
        }
    }
}
