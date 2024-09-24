package myprojects.tankgame05;

public class Tank {
    private int x;
    private int y;
    private int direct;
    private int speed=3;
    boolean isAlive=true;


    public Tank(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
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

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }
    public void movew(){
        this.y=y-speed;
    }
    public void moves(){
        this.y=y+speed;
    }
    public void movea(){
        this.x=x-speed;
    }
    public void moved(){
        this.x=x+speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
