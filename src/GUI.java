import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;

public class GUI extends Application {
    Board board;
    Scene menu;
    Scene invasion;
    StackPane root;
    VBox layout1;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("INWAZJA!!!");
        root = new StackPane();
        menu = new Scene(root, 600,400);
        primaryStage.setScene(menu);
        primaryStage.show();
        TextField textField = new TextField();
        root.getChildren().add(textField);
        Label label = new Label("Ile robotów chcesz sprowadzić na Ziemię?");
        root.getChildren().add(label);
        label.setTranslateY(-50);
        Button button = new Button("Przeprowadź inwazję");
        root.getChildren().add(button);
        button.setTranslateY(50);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // CHECK IF TEXTFIELD IS A NUMBER
                int numberOfRobots=Integer.parseInt(textField.getCharacters().toString());
                board=new Board(numberOfRobots);
                primaryStage.setScene(invasion);
                showBoard();
                System.out.println(board.checkPosition());
            }
        });
        primaryStage.show();
        try {
            layout1 = new VBox(20);
            invasion = new Scene(layout1, 600, 400);
        } catch (Exception e) {e.printStackTrace();}
    }
    private void showBoard() {
        Canvas canvas=new Canvas(600,400);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawShapes(gc);
        layout1.getChildren().add(canvas);
    }
    private void drawShapes(GraphicsContext gc) {
        double[] xArray={board.A.x,board.B.x,board.C.x};
        double[] yArray={board.A.y,board.B.y,board.C.y};
        gc.setFill(Color.CYAN);
        gc.fillPolygon(xArray,yArray,3);
        gc.setFill(Color.GREEN);
        gc.fillRect(board.A.x,board.A.y,7,7);
        gc.fillRect(board.B.x,board.B.y,7,7);
        gc.fillRect(board.C.x,board.C.y,7,7);
        gc.setFill(Color.BLUEVIOLET);
        for (int i=0;i<board.robotList.size();i++) {
            double x = board.robotList.get(i).getPos_X();
            double y = board.robotList.get(i).getPos_Y();
            gc.fillRect(x,y,3,3);
        }
        gc.setFill(Color.CHOCOLATE);
        double x = board.mainRobot.getPos_X();
        double y = board.mainRobot.getPos_Y();
        gc.fillRect(x,y,10,10);
    }
}