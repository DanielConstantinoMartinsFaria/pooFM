package FootballManager.Model.Eventos;

import FootballManager.Model.Players.Jogador;

public class Remate extends Eventos{
    private Jogador marcador;
    private Jogador guardaRedes;

    public Remate(boolean equipaCasa,Jogador marcador,Jogador guardaRedes){
        super(equipaCasa);
        this.marcador=marcador;
        this.guardaRedes=guardaRedes;
    }
}
