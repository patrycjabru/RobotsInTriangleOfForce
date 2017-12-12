import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Board {
//    test2
    int x=100;
    int y=100;
    List<Robot> robotList = new ArrayList<Robot>();
    Robot mainRobot;
    Transmitter A = new Transmitter();
    Transmitter B = new Transmitter();
    Transmitter C = new Transmitter();
    Map<Integer, Double> signalsA;
    Map<Integer, Double> signalsB;
    Map<Integer, Double> signalsC;
//    List<Double> sumAB = new ArrayList<>();
//    List<Double> sumBC = new ArrayList<>();
//    List<Double> sumCA = new ArrayList<>();
//    List<Double> sumABC = new ArrayList<>();
    Map<Integer, Double> sumAB;
    Map<Integer, Double> sumAC;
    Map<Integer, Double> sumCA;
    Map<Integer, Double> sumABC;
    Board(int numberOfRobots) {
        createTransmitters();
        createRobots(numberOfRobots);
        sum();
    }
    void createRobots(int number) {
        Random random=new Random();
        for (int i=0;i<number;i++) {
            double x = random.nextDouble()*this.x;
            double y = random.nextDouble()*this.y;
            Robot robot=new Robot(x,y);
            robot.setToA(A.x, A.y);
            robot.setToB(B.x, B.y);
            robot.setToC(C.x, C.y);
            robotList.add(robot);
        }
        mainRobot=new Robot(random.nextDouble()*this.x,random.nextDouble()*this.y);
        mainRobot.setToA(A.x, A.y);
        mainRobot.setToB(B.x, B.y);
        mainRobot.setToC(C.x, C.y);
    }
    void createTransmitters() {
        Random random = new Random();
        A.setX(random.nextDouble()*this.x);
        B.setX(random.nextDouble()*this.x);
        C.setX(random.nextDouble()*this.x);
        A.setY(random.nextDouble()*this.y);
        B.setY(random.nextDouble()*this.y);
        C.setY(random.nextDouble()*this.y);
    }
    void createSignalMaps() {
        for (int i=0;i<robotList.size();i++) {
            signalsA.put(i,robotList.get(i).getSignalA());
            signalsB.put(i,robotList.get(i).getSignalB());
            signalsC.put(i,robotList.get(i).getSignalC());
        }
    }
    void sum() {
//        for (int i=0;i<robotList.size();i++) {
//            sumAB.add(robotList.get(i).getSignalA()+robotList.get(i).getSignalB());
//            sumBC.add(robotList.get(i).getSignalB()+robotList.get(i).getSignalC());
//            sumCA.add(robotList.get(i).getSignalC()+robotList.get(i).getSignalA());
//            sumABC.add(robotList.get(i).getSignalA()+robotList.get(i).getSignalB()+robotList.get(i).getSignalC());
//        }
    }
    boolean checkPosition() {
        System.out.println(mainRobot.getSignalA());
        System.out.println(mainRobot.getSignalB());
        System.out.println(mainRobot.getSignalC());
//        if (mainRobot.getSignalA()+mainRobot.getSignalB()+mainRobot.getSignalC()>0)
//            return true;
        return false;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}