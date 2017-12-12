import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    int x=100;
    int y=100;
    List<Robot> robotList = new ArrayList<Robot>();
    Robot mainRobot;
    Transmitter A = new Transmitter();
    Transmitter B = new Transmitter();
    Transmitter C = new Transmitter();
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
    boolean checkPosition() {
        return false;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}