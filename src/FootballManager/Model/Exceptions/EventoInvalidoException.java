package FootballManager.Model.Exceptions;

public class EventoInvalidoException extends Exception{
    public EventoInvalidoException(){
        super();
    }
    public EventoInvalidoException(String evento,String razao){
        super("O evento:"+evento+" e invalido pois "+razao);
    }
}
