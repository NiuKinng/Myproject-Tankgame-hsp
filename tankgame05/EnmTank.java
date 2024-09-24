package myprojects.tankgame05;

import java.util.Vector;

public class EnmTank extends Tank implements Runnable {
    Vector<Shot> shots = new Vector<>();
    Vector<EnmTank> EnmTanks = new Vector<>();

    public EnmTank(int x, int y, int direct) {
        super(x, y, direct);
    }

    public Vector<Shot> getShots() {
        return shots;
    }

    public void setShots(Vector<Shot> shots) {
        this.shots = shots;
    }

    public Vector<EnmTank> getEnmTanks() {
        return EnmTanks;
    }

    public void setEnmTanks(Vector<EnmTank> enmTanks) {
        EnmTanks = enmTanks;
    }

    @Override
    public void run() {
        while (true) {
            if (isAlive && shots.isEmpty()) {
                Shot shot = null;
                switch (getDirect()) {
                    case 0:

                        shot = new Shot(getX() + 20, getY(), 0);
                        break;
                    case 1:
                        shot = new Shot(getX() + 60, getY() + 20, 1);
                        break;
                    case 2:
                        shot = new Shot(getX() + 20, getY() + 60, 2);
                        break;
                    case 3:
                        shot = new Shot(getX(), getY() + 20, 3);
                        break;
                }
                shots.add(shot);
                new Thread(shot).start();
            }
            switch (getDirect()) {
                case 0:
                    for (int i = 0; i < 30; i++) {
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        if (getY() > 0&&!isTouchTank()) {
                            movew();
                        }
                    }
                    break;

                case 1:
                    for (int i = 0; i < 30; i++) {
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        if (getX() < 940&&!isTouchTank()) {
                            moved();
                        }

                    }
                    break;
                case 2:
                    for (int i = 0; i < 30; i++) {
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        if (getY() < 690&&!isTouchTank()) {
                            moves();
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 30; i++) {
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        if (getX() > 0&&!isTouchTank()) {
                            movea();
                        }
                    }
                    break;

            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            setDirect((int) (Math.random() * 4));
            if (!isAlive) {
                break;
            }
        }
    }

    public boolean isTouchTank() {
        switch (getDirect()) {
            case 0:
                for (int i = 0; i < EnmTanks.size(); i++) {
                    EnmTank enmTank = EnmTanks.get(i);
                    if (enmTank != this) {
                        if (enmTank.getDirect() == 0 || enmTank.getDirect() == 2) {
                            if (this.getX() >= enmTank.getX() && this.getX() <= enmTank.getX() + 40
                                    && this.getY() >= enmTank.getY() && this.getY() <= enmTank.getY() + 60) {
                                return true;
                            }
                            if (this.getX() + 40 >= enmTank.getX() && this.getX() + 40 <= enmTank.getX() + 40
                                    && this.getY()>= enmTank.getY() && this.getY()<= enmTank.getY() + 60) {
                                return true;
                            }

                        }
                        if (enmTank.getDirect() == 1 || enmTank.getDirect() == 3) {
                            if (this.getX() >= enmTank.getX() && this.getX() <= enmTank.getX() + 60
                                    && this.getY() >= enmTank.getY() && this.getY() <= enmTank.getY() + 40) {
                                return true;
                            }
                            if (this.getX() + 40 >= enmTank.getX() && this.getX() + 40 <= enmTank.getX() + 60
                                    && this.getY() >= enmTank.getY() && this.getY() <= enmTank.getY() + 40) {
                                return true;
                            }

                        }
                    }
                }
                break;
            case 1:
                for (int i = 0; i < EnmTanks.size(); i++) {
                    EnmTank enmTank = EnmTanks.get(i);
                    if (enmTank != this) {
                        if (enmTank.getDirect() == 0 || enmTank.getDirect() == 2) {
                            if (this.getX() +60>= enmTank.getX() && this.getX()+60 <= enmTank.getX() + 40
                                    && this.getY() >= enmTank.getY() && this.getY() <= enmTank.getY() + 60) {
                                return true;
                            }
                            if (this.getX() + 60 >= enmTank.getX() && this.getX() + 60 <= enmTank.getX() + 40
                                    && this.getY() +40 >= enmTank.getY() && this.getY() + 40 <= enmTank.getY() + 60) {
                                return true;
                            }

                        }
                        if (enmTank.getDirect() == 1 || enmTank.getDirect() == 3) {
                            if (this.getX() +60>= enmTank.getX() && this.getX()+60 <= enmTank.getX() + 60
                                    && this.getY() >= enmTank.getY() && this.getY() <= enmTank.getY() + 40) {
                                return true;
                            }
                            if (this.getX() + 60 >= enmTank.getX() && this.getX() + 60 <= enmTank.getX() + 60
                                    && this.getY() + 40 >= enmTank.getY() && this.getY() + 40 <= enmTank.getY() + 40) {
                                return true;
                            }

                        }
                    }
                }
                break;
            case 2:
                for (int i = 0; i < EnmTanks.size(); i++) {
                    EnmTank enmTank = EnmTanks.get(i);
                    if (enmTank != this) {
                        if (enmTank.getDirect() == 0 || enmTank.getDirect() == 2) {
                            if (this.getX()>= enmTank.getX() && this.getX()<= enmTank.getX() + 40
                                    && this.getY() +60>= enmTank.getY() && this.getY()+60 <= enmTank.getY() + 60) {
                                return true;
                            }
                            if (this.getX()+40 >= enmTank.getX() && this.getX()+40<= enmTank.getX() + 40
                                    && this.getY()+60 >= enmTank.getY() && this.getY() +60<= enmTank.getY() + 60) {
                                return true;
                            }

                        }
                        if (enmTank.getDirect() == 1 || enmTank.getDirect() == 3) {
                            if (this.getX()>= enmTank.getX() && this.getX() <= enmTank.getX() + 60
                                    && this.getY()+60 >= enmTank.getY() && this.getY() +60<= enmTank.getY() + 40) {
                                return true;
                            }
                            if (this.getX()+40 >= enmTank.getX() && this.getX() +40  <= enmTank.getX() + 60
                                    && this.getY() + 60 >= enmTank.getY() && this.getY() + 60 <= enmTank.getY() + 40) {
                                return true;
                            }

                        }
                    }
                }
                break;
            case 3:
                for (int i = 0; i < EnmTanks.size(); i++) {
                    EnmTank enmTank = EnmTanks.get(i);
                    if (enmTank != this) {
                        if (enmTank.getDirect() == 0 || enmTank.getDirect() == 2) {
                            if (this.getX() >= enmTank.getX() && this.getX() <= enmTank.getX() + 40
                                    && this.getY() >= enmTank.getY() && this.getY()<= enmTank.getY() + 60) {
                                return true;
                            }
                            if (this.getX()  >= enmTank.getX() && this.getX() <= enmTank.getX() + 40
                                    && this.getY() + 40 >= enmTank.getY() && this.getY() + 40 <= enmTank.getY() + 60) {
                                return true;
                            }

                        }
                        if (enmTank.getDirect() == 1 || enmTank.getDirect() == 3) {
                            if (this.getX() >= enmTank.getX() && this.getX() <= enmTank.getX() + 60
                                    && this.getY()>= enmTank.getY() && this.getY() <= enmTank.getY() + 40) {
                                return true;
                            }
                            if (this.getX() >= enmTank.getX() && this.getX() <= enmTank.getX() + 60
                                    && this.getY() + 40 >= enmTank.getY() && this.getY() + 40 <= enmTank.getY() + 40) {
                                return true;
                            }

                        }
                    }
                }
                break;

        }
        return false;
    }
}

