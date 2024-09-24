package myprojects.tankgame05;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener, Runnable {

    MyTank myTank = null;
    public static Vector<EnmTank> enmTanks = new Vector<>();

    static Vector<Boom> booms = new Vector<>();
    Vector<Node> nodes = new Vector<>();

    int enemynum = 4;
    Image image = null;

    public MyPanel(String key) {
        File file = new File(ReCoder.getReCordFile());
        if (file.exists()) {
            nodes=ReCoder.getNodes();
        }else{
            System.out.println("文件不存在，只能开启新游戏");
            key =2+"";
        }
        ReCoder.setEnmTanks(enmTanks);
        myTank = new MyTank(500, 600, 0);

        switch (key) {
            case "1":
                for (int i = 0; i < nodes.size(); i++) {
                     Node node = nodes.get(i);
                     EnmTank enmTank=new EnmTank(node.getX(),node.getY(),node.getDirect());
                    enmTank.setEnmTanks(enmTanks);
                    enmTank.setSpeed(1);
                    new Thread(enmTank).start();

                    Shot shot = new Shot(enmTank.getX() + 20, enmTank.getY() + 60, enmTank.getDirect());
                    enmTank.shots.add(shot);
                    new Thread(shot).start();

                    enmTanks.add(enmTank);

                }
                break;
            case "2":
                for (int i = 1; i <= enemynum; i++) {
                    EnmTank enmTank = new EnmTank((100 * i), 0, 2);
                    enmTank.setEnmTanks(enmTanks);
                    enmTank.setSpeed(1);
                    new Thread(enmTank).start();

                    Shot shot = new Shot(enmTank.getX() + 20, enmTank.getY() + 60, enmTank.getDirect());
                    enmTank.shots.add(shot);
                    new Thread(shot).start();

                    enmTanks.add(enmTank);

                }
                break;
            default:
                System.out.println("你的输入有误");
        }

        image = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/OIP-C.jpg"));
    }

    public void showInfo(Graphics g) {
        g.setColor(Color.BLACK);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);
        g.drawString("累计击毁敌方坦克数", 1020, 30);
        DrawTank(1020, 60, g, 0, 0);
        g.setColor(Color.black);
        g.drawString(ReCoder.getKillnumm() + "", 1080, 100);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        showInfo(g);
        g.fillRect(0, 0, 1000, 750);
        if (myTank.isAlive) {
            DrawTank(myTank.getX(), myTank.getY(), g, myTank.getDirect(), 0);
        }


        for (int i = 0; i < myTank.shots.size(); i++) {
            Shot shot = myTank.shots.get(i);
            if (myTank.shot != null && myTank.shot.isAlive) {
                g.fill3DRect(shot.x, shot.y, 5, 5, false);
            } else {
                myTank.shots.remove(shot);
            }

        }
        for (int i = 0; i < booms.size(); i++) {
            Boom boom = booms.get(i);
            g.drawImage(image, boom.x, boom.y, 60, 60, this);
            boom.lifDown();
            if (boom.life == 0) {
                booms.remove(boom);
            }
        }

        for (int i = 0; i < enmTanks.size(); i++) {
            EnmTank enmTank = enmTanks.get(i);
            if (enmTank.isAlive) {
                DrawTank(enmTank.getX(), enmTank.getY(), g, enmTank.getDirect(), 1);
                for (int j = 0; j < enmTank.shots.size(); j++) {
                    Shot o = enmTank.shots.get(j);
                    if (o.isAlive) {
                        g.fill3DRect(o.x, o.y, 5, 5, false);
                    } else {
                        enmTank.shots.remove(o);
                    }

                }
            }


        }
    }

    public void DrawTank(int x, int y, Graphics g, int direction, int type) {
        switch (type) {
            case 0:
                g.setColor(Color.cyan);
                break;
            case 1:
                g.setColor(Color.yellow);
                break;
        }

        switch (direction) {
            case 0:
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y);
                break;
            case 1:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x + 60, y + 20);
                break;
            case 2:
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y + 60);
                break;
            case 3:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x, y + 20);
                break;
            default:

        }
    }

    public static void hitTank(Vector<Shot> shots, Tank enmTank) {
        for (int i = 0; i < shots.size(); i++) {
            Shot shot = shots.get(i);
            if (enmTank.isAlive) {
                switch (enmTank.getDirect()) {
                    case 0:
                    case 2:
                        if (shot.x > enmTank.getX() && shot.x < enmTank.getX() + 40 && shot.y > enmTank.getY() && shot.y < enmTank.getY() + 60) {
                            shot.isAlive = false;
                            enmTank.isAlive = false;
                            enmTanks.remove(enmTank);
                            if (enmTank instanceof EnmTank) {
                                ReCoder.setKillnumm();
                            }

                            Boom boom = new Boom(enmTank.getX(), enmTank.getY());
                            booms.add(boom);

                        }
                        break;
                    case 1:
                    case 3:
                        if (shot.x >= enmTank.getX() && shot.x < enmTank.getX() + 60 && shot.y > enmTank.getY() && shot.y < enmTank.getY() + 40) {
                            shot.isAlive = false;
                            enmTank.isAlive = false;
                            enmTanks.remove(enmTank);
                            if (enmTank instanceof EnmTank) {
                                ReCoder.setKillnumm();
                            }
                            Boom boom = new Boom(enmTank.getX(), enmTank.getY());
                            booms.add(boom);
                        }
                        break;
                }
            }
        }


    }

    public void hitMytank() {
        for (int i = 0; i < enmTanks.size(); i++) {
            EnmTank enmTank = enmTanks.get(i);
            if (!enmTank.shots.isEmpty() && myTank.isAlive) {
                hitTank(enmTank.shots, myTank);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                myTank.setDirect(0);
                if (myTank.getY() > 0) {
                    myTank.movew();
                }

                break;
            case KeyEvent.VK_D:
                myTank.setDirect(1);
                if (myTank.getX() < 940) {
                    myTank.moved();
                }

                break;
            case KeyEvent.VK_S:
                myTank.setDirect(2);
                if (myTank.getY() < 650) {
                    myTank.moves();
                }

                break;
            case KeyEvent.VK_A:
                myTank.setDirect(3);
                if (myTank.getX() > 0) {
                    myTank.movea();
                }


                break;
            case KeyEvent.VK_J:
//                if(myTank.shot==null||!myTank.shot.isAlive)
//                {
//                    myTank.shotEnemyTank();
//                }
                myTank.shotEnemyTank();

        }
//        if (e.getKeyCode() == KeyEvent.VK_W) {
//            myTank.setDirect(0);
//        } else if (e.getKeyCode()==KeyEvent.VK_D) {
//            myTank.setDirect(1);
//        } else if (e.getKeyCode()==KeyEvent.VK_S) {
//            myTank.setDirect(2);
//        }else if (e.getKeyCode()==KeyEvent.VK_A) {
//            myTank.setDirect(3);
//        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (myTank.shot != null && myTank.shot.isAlive) {
                for (int i = 0; i < enmTanks.size(); i++) {
                    EnmTank enmTank = enmTanks.get(i);
                    hitTank(myTank.shots, enmTank);
                }

            }

            hitMytank();

            this.repaint();
        }
    }
}
