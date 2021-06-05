package FootballManager.Model.Auxiliares;

public class InGameStats {
    public boolean central;
    public int vigor;

    public InGameStats(){
        vigor=100;
        central=true;
    }
    public InGameStats(Integer vigor,boolean central){
        this.vigor=100;
        this.central=central;
    }

    public int getVigor(){
        return vigor;
    }

    public boolean isCentral(){
        return central;
    }

    public void setCentral(boolean central){
        this.central=central;
    }

    public void setVigor(int vigor){
        this.vigor=vigor;
    }

    public void changeVigor(int value){
        vigor+=value;
        if(vigor<0)vigor=0;
        if(vigor>100)vigor=100;
    }
}
