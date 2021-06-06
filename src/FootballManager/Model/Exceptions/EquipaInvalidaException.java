package FootballManager.Model.Exceptions;

public class EquipaInvalidaException extends Exception{
    public EquipaInvalidaException(){
        super();
    }
    public EquipaInvalidaException(String equipa){
        super(equipa);
    }
    public EquipaInvalidaException(String equipa,String razao){
        super("A equipa "+equipa+" e invalida pois "+razao);
    }
}
