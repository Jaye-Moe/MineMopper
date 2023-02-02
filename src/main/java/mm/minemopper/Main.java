// Add timer
// Add score



package mm.minemopper;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage)  {

        int width = 9;
        int height = 9;
        int numberOfMines = 10;

        Block [][] board = new Block[height][width];

        GridPane playingArea = new GridPane();

        UI ui = new UI(height,width,board,numberOfMines, playingArea);


        ui.buildBoard(height, width, board, playingArea, ui);
        ui.setMines(height,width,board, numberOfMines);
        ui.findAdjacentMines(height,width,board);

        System.out.println("\n\n\n");

        ui.printBoard(height,width,board);


        playingArea.setVgap(5);
        playingArea.setHgap(5);
        playingArea.setPadding(new Insets(10,10,10,10));


        Button resetButton = new Button();
        resetButton.setText("RESET");
        resetButton.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 24));
        resetButton.setAlignment(Pos.CENTER);

        resetButton.setOnMouseClicked(mouseEvent -> {
            System.out.println("Resseting");
            playingArea.getChildren().clear();
            ui.buildBoard(height, width, board, playingArea, ui);
            ui.setMines(height,width,board, numberOfMines);
            ui.findAdjacentMines(height,width,board);
            playingArea.getChildren().clear();
            ui.resetRemainingMines();
            ui.updateBoard(height,width,board, playingArea);
            playingArea.add(resetButton, 3,14,5,1);
            ui.clearGameOverMessage();

        });



        playingArea.add(resetButton, 3,14,5,1);

        // Update the board when clicked
        playingArea.setOnMouseClicked(e ->{
            playingArea.getChildren().clear();
            ui.updateBoard(height,width,board, playingArea);
            playingArea.add(resetButton, 3,14,5,1);

        });



        Scene scene = new Scene(playingArea,510,720);
        playingArea.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        stage.setScene(scene);
        stage.setTitle("Mine Mopper");

        stage.show();



    }







    public static void main(String[] args) {
        launch();
    }
}