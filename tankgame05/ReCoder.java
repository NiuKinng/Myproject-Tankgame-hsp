package myprojects.tankgame05;

import java.io.*;
import java.util.Vector;

public class ReCoder {
    private static int Killnumm;
    private static FileWriter fw = null;
    public static BufferedWriter bw = null;
    public static BufferedReader br = null;
    private static Vector<EnmTank> enmTanks = null;
    private static Vector<Node> nodes = new Vector<>();
    private static String reCordFile="src//myrecoder.txt";
    private  static int allEnamyTankNum;
    public static void setEnmTanks(Vector<EnmTank> enmTanks) {
        ReCoder.enmTanks = enmTanks;
    }

    public static String getReCordFile() {
        return reCordFile;
    }

    public static Vector<Node> getNodes() {

        try {
            br = new BufferedReader(new FileReader(reCordFile));
            allEnamyTankNum=Integer.parseInt(br.readLine());
            String line;
            while((line=br.readLine())!=null)
            {
                String[] xyd=line.split(" ");
                myprojects.tankgame05.Node node = new myprojects.tankgame05.Node(Integer.parseInt(xyd[0]),
                        Integer.parseInt(xyd[1]),Integer.parseInt(xyd[2]));
                nodes.add(node);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);

        } finally {
            if(br!=null)
            {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return nodes;
    }

    public static void KeepReCoder() {
        try {
            bw = new BufferedWriter(new FileWriter(reCordFile));
            bw.write(Killnumm + "\r\n");
            for (int i = 0; i < enmTanks.size(); i++) {
                EnmTank enmTank = enmTanks.get(i);
                if (enmTank.isAlive) {
                    String record = enmTank.getX() + " " + enmTank.getY() + " " + enmTank.getDirect();
                    bw.write(record + "\r\n");

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static int getKillnumm() {
        return Killnumm;
    }

    public static void setKillnumm() {
        Killnumm++;

    }


}
