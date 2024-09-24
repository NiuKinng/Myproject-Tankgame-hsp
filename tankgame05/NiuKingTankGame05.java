package myprojects.tankgame05;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class NiuKingTankGame05 extends JFrame {
    MyPanel mp=null;
    static  Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {


        new NiuKingTankGame05();
    }
    public NiuKingTankGame05() {
        System.out.println("请输入选择：1.继续上局游戏 2。新游戏");
        String key=sc.next();

        mp=new MyPanel(key);
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);
        this.setSize(1350,750);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                ReCoder.KeepReCoder();
                System.exit(0);
            }
        });
    }
}
