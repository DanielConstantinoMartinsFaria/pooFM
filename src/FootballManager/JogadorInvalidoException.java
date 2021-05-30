package FootballManager;

public class JogadorInvalidoException extends Exception {
    public JogadorInvalidoException(){
        super();
    }
    public JogadorInvalidoException(String str){
        super(str);
    }
}
