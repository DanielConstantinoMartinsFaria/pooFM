package FootballManager.Model.Exceptions;

import java.util.Arrays;

public class TaticaInvalidaException extends Exception{
    public TaticaInvalidaException(){
        super();
    }
    public TaticaInvalidaException(String str){
        super(str);
    }
    public TaticaInvalidaException(String tatica,String razao){
        super("A tatica "+tatica+"e invalida pois "+razao);
    }
}
