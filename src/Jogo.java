import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Jogo{
    private Equipa ATeam;
    private Equipa BTeam;
    private int resultado; //0-> A ganha ; 1-> B ganha ; 2-> Empate

    public Jogo(){
        this.ATeam = new Equipa();
        this.BTeam = new Equipa();
        this.resultado = 0;
    }

    public int resultadoFinal(){
        if(ATeam.calculaRatingTotal() > BTeam.calculaRatingTotal()) return 0;
        else if(ATeam.calculaRatingTotal() < BTeam.calculaRatingTotal()) return 1;
        else return 2;
    }
}

/*ideia a desenvolver para determinar quem ganha um jogo (Não definitiva):
    -Geramos um valor aleatório entre A e B ( A < B e estão entre 0 e 1)
    -Se o resultado for abaixo de 0.5 ganha a equipa A , senão ganha a B
    -Caso a equipa A seja melhor, o valor B fica mais baixo , sendo mais provavel A ganhar
    -Caso a equipa B seja melhor, o valor A fica mais alto , sendo mais provavel B ganhar
    -Quanto maior a diferença, mais os valores transitam para perto do 0.5 sem passar para a outra metade
 */
