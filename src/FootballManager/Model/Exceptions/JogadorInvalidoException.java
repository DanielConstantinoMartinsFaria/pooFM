package FootballManager.Model.Exceptions;

public class JogadorInvalidoException extends Exception {
    public JogadorInvalidoException(){
        super();
    }
    public JogadorInvalidoException(String str){
        super(str);
    }
    public JogadorInvalidoException(int n,String razao){
        super("O jogador n:"+n+" "+razao);
    }
}
