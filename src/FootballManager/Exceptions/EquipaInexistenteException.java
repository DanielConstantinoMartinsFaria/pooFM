package FootballManager.Exceptions;

public class EquipaInexistenteException extends Exception{
    public EquipaInexistenteException(){
        super();
    }
    public EquipaInexistenteException(String str){
        super(str);
    }
}
