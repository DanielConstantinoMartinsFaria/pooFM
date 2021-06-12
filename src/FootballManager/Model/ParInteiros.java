package FootballManager.Model;

import java.io.Serializable;

public class ParInteiros implements Comparable<ParInteiros>, Serializable {
    private int x;
    private int y;

    public ParInteiros(){
        x=0;
        y=0;
    }
    public ParInteiros(int x,int y){
        this.x=x;
        this.y=y;
    }

    public ParInteiros(ParInteiros par){
        this.x= par.getX();
        this.y=par.getY();
    }

    @Override
    public int compareTo(ParInteiros o) {
        if(this.equals(o))return 0;
        else if(this.getX() == o.getX())return this.getY()-o.getY();
        else return this.getX()-o.getX();
    }

    public boolean equals(Object o){
        if(o==null)return false;
        if(o==this)return true;
        if(this.getClass()!=o.getClass())return false;
        ParInteiros novo=(ParInteiros) o;
        return (novo.getX()==this.x && novo.getY()==this.y);
    }

    public String toString(){
        return x + "," + y;
    }

    public ParInteiros clone(){
        return new ParInteiros(this);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int sum(){
        return x+y;
    }

    public int max(){
        int res=x;
        if(y>res)res=y;
        return res;
    }

    public int min(){
        int res=x;
        if(y<res)res=y;
        return res;
    }

    public void addX(int value){
        x+=value;
    }
    public void addY(int value){
        y+=value;
    }
}
