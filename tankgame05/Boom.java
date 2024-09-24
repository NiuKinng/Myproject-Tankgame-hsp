package myprojects.tankgame05;

public class Boom {
    int x, y;
    int life = 9;
    boolean isalive = true;

    public Boom(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void lifDown() {
        if (life > 0) {
            life--;
        } else {
            isalive = false;
        }
    }
}
