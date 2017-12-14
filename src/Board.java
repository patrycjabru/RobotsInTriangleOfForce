import java.util.*;
import java.util.List;

public class Board {
//    test2
    int x=600;
    int y=400;
    List<Robot> robotList = new ArrayList<Robot>();
    Robot mainRobot;
    Transmitter A = new Transmitter();
    Transmitter B = new Transmitter();
    Transmitter C = new Transmitter();
    Map<Integer, Double> signalsA = new HashMap<>();
    Map<Integer, Double> signalsB = new HashMap<>();
    Map<Integer, Double> signalsC = new HashMap<>();
    Map<Integer, Double> sumAB;
    Map<Integer, Double> sumBC;
    Map<Integer, Double> sumCA;
    Map<Integer, Double> sumABC;
    int[] friendlyRobots;
    Board(int numberOfRobots) {
        createTransmitters();
        createRobots(numberOfRobots);
        createSignalMaps();
        sum();
        this.friendlyRobots =findFriendlyRobots();
    }
    void createRobots(int number) {
        Random random=new Random();
        for (int i=0;i<number;i++) {
            double x = random.nextDouble()*this.x;
            double y = random.nextDouble()*this.y;
            Robot robot=new Robot(x,y);
            robot.setToA(A.x, A.y,A.amplitude);
            robot.setToB(B.x, B.y,B.amplitude);
            robot.setToC(C.x, C.y,C.amplitude);
            robotList.add(robot);
        }
        mainRobot=new Robot(random.nextDouble()*this.x,random.nextDouble()*this.y);
        mainRobot.setToA(A.x, A.y,A.amplitude);
        mainRobot.setToB(B.x, B.y,B.amplitude);
        mainRobot.setToC(C.x, C.y,C.amplitude);
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
        sumAB = new HashMap<>();
        sumBC = new HashMap<>();
        sumCA = new HashMap<>();
        sumABC = new HashMap<>();
        for (int i=0;i<robotList.size();i++) {
            sumAB.put(i,robotList.get(i).getSignalA()+robotList.get(i).getSignalB());
            sumBC.put(i,robotList.get(i).getSignalB()+robotList.get(i).getSignalC());
            sumCA.put(i,robotList.get(i).getSignalC()+robotList.get(i).getSignalA());
            sumABC.put(i,robotList.get(i).getSignalA()+robotList.get(i).getSignalB()+robotList.get(i).getSignalB());
        }
    }
    int[] findFriendlyRobots() {    //DOESN'T WORK
//        double minAB=mainRobot.getSignalA()-signalsA.get(0)+mainRobot.getSignalB()-signalsB.get(0);
//        int indexAB=0;
//        double minBC=mainRobot.getSignalB()-signalsB.get(0)+mainRobot.getSignalC()-signalsC.get(0);
//        int indexBC=0;
//        double minCA=mainRobot.getSignalC()-signalsC.get(0)+mainRobot.getSignalA()-signalsA.get(0);
//        int indexCA=0;
//        for (int i=0;i<robotList.size();i++) {
//            if (mainRobot.getSignalA()-signalsA.get(i)+mainRobot.getSignalB()-signalsB.get(i)<minAB) {
//                minAB = mainRobot.getSignalA()-signalsA.get(i)+mainRobot.getSignalB()-signalsB.get(i);
//                indexAB = i;
//            }
//            if (mainRobot.getSignalB()-signalsB.get(i)+mainRobot.getSignalC()-signalsC.get(i)<minBC) {
//                minBC = mainRobot.getSignalB()-signalsB.get(i)+mainRobot.getSignalC()-signalsC.get(i);
//                indexBC = i;
//            }
//            if (mainRobot.getSignalC()-signalsC.get(i)+mainRobot.getSignalA()-signalsA.get(i)<minCA) {
//                minCA = mainRobot.getSignalC()-signalsC.get(i)+mainRobot.getSignalA()-signalsA.get(i);
//                indexCA = i;
//            }
//        }
//        int[] output={indexAB,indexBC,indexCA};
//        friendlyRobots =output;
//        return output;
        double eps=10;
        //AB axis
        double ABRobotSignalA = mainRobot.getSignalA();
        double ABRobotSignalB = mainRobot.getSignalB();
        double minDiffAB=1000;
        int indexAB=0;
        for (int i=0;i<signalsA.size();i++) {
            if ((Math.abs(signalsA.get(i) - ABRobotSignalA))+(Math.abs(signalsB.get(i) - ABRobotSignalB))<minDiffAB && signalsC.get(i)-mainRobot.getSignalC()>eps) {
                indexAB = i;
                minDiffAB = (Math.abs(signalsA.get(i) - ABRobotSignalA))+(Math.abs(signalsB.get(i) - ABRobotSignalB));
            }
        }
        //BC axis
        double BCRobotSignalB = mainRobot.getSignalB();
        double BCRobotSignalC = mainRobot.getSignalC();
        double minDiffBC=1000;
        int indexBC=0;
        for (int i=0;i<signalsB.size();i++) {
            if ((Math.abs(signalsB.get(i) - BCRobotSignalB))+(Math.abs(signalsC.get(i) - BCRobotSignalC))<minDiffBC && signalsA.get(i)-mainRobot.getSignalA()>eps) {
                indexBC = i;
                minDiffBC = (Math.abs(signalsB.get(i) - BCRobotSignalB))+(Math.abs(signalsC.get(i) - BCRobotSignalC));
            }
        }
        //CA axis
        double CARobotSignalC = mainRobot.getSignalC();
        double CARobotSignalA = mainRobot.getSignalA();
        double minDiffCA=1000;
        int indexCA=0;
        for (int i=0;i<signalsC.size();i++) {
            if ((Math.abs(signalsC.get(i) - CARobotSignalC))+(Math.abs(signalsA.get(i) - CARobotSignalA))<minDiffCA && signalsB.get(i)-mainRobot.getSignalB()>eps) {
                indexCA = i;
                minDiffCA = (Math.abs(signalsC.get(i) - CARobotSignalC))+(Math.abs(signalsA.get(i) - CARobotSignalA));
            }
        }
        int[] output={indexAB,indexBC,indexCA};
        return output;
    }
    boolean checkPosition() {
        int[] friendlyRobotsIndexes=findFriendlyRobots();
        System.out.println(friendlyRobotsIndexes[0]);
        System.out.println(friendlyRobotsIndexes[1]);
        System.out.println(friendlyRobotsIndexes[2]);
        if (sumABC.get(friendlyRobotsIndexes[0])>mainRobot.getSignalA()+mainRobot.getSignalB()+mainRobot.getSignalC())
            return false;
        if (sumABC.get(friendlyRobotsIndexes[1])>mainRobot.getSignalA()+mainRobot.getSignalB()+mainRobot.getSignalC())
            return false;
        if (sumABC.get(friendlyRobotsIndexes[2])>mainRobot.getSignalA()+mainRobot.getSignalB()+mainRobot.getSignalC())
            return false;
        return true;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}