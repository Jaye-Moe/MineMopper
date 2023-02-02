package mm.minemopper;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.Random;

public class UI {
    private final Label gameMessage;
    private final Label remainingMinesMessage;
    private int remainingMines;

    public UI(int height, int width, Block [][] board, int numberOfMines, GridPane playingArea){
        this.gameMessage = new Label();
        this.gameMessage.setText("");
        this.gameMessage.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 24));
        this.gameMessage.setAlignment(Pos.CENTER);

        this.remainingMines = 10;

        this.remainingMinesMessage = new Label();
        this.remainingMinesMessage.setText("Remaining Mines: " + this.remainingMines);
        this.remainingMinesMessage.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 24));
        this.remainingMinesMessage.setAlignment(Pos.CENTER);
    }

    public void clearGameOverMessage(){
        this.gameMessage.setText("");
    }

    public void resetRemainingMines(){
        this.remainingMines = 10;
    }

    public void decreaseMines(){
        this.remainingMines--;
    }

    public void increaseMines(){
        this.remainingMines++;
    }

    public void checkForWin(){
        if (this.remainingMines==0){
            this.gameMessage.setText("YOU WON!");
            System.out.println("You Won!");
        }
    }


   public void buildBoard(int height, int width, Block [][] board, GridPane playingArea, UI ui){
        for (int rowNumber = 0; rowNumber < height; rowNumber++){
            for (int columnNumber = 0; columnNumber < width; columnNumber++){

                System.out.print(rowNumber + "," + columnNumber + "   ");
                Block block = new Block(rowNumber,columnNumber, ui, height, width, board);

                board[rowNumber][columnNumber] = block;

                playingArea.add(block.getBlock(),columnNumber, rowNumber);

            }

            System.out.println();
        }
        this.checkForWin();
        playingArea.add(this.gameMessage, 3,15,5,1);
        playingArea.add(this.remainingMinesMessage, 2,16,7,1);
    }

    public void setMines(int height, int width, Block [][] board, int numberOfMines){
        Random rand = new Random();

        for (int i = 0; i <numberOfMines; i++){

            int mineRowToPlace = rand.nextInt(height);
            int mineColumnToPlace = rand.nextInt(width);
            System.out.println("Mine at " + mineRowToPlace + ", " + mineColumnToPlace);
            if(board[mineRowToPlace][mineColumnToPlace].getContents().equals("Mine")){
                i--;
            }
            board[mineRowToPlace][mineColumnToPlace].setContents("Mine");

        }
    }

    public void printBoard(int height, int width, Block [][] board){
        for (int rowNumber = 0; rowNumber < height; rowNumber++){
            for (int columnNumber = 0; columnNumber < width; columnNumber++){

                System.out.print(rowNumber + "," + columnNumber + " " + board[rowNumber][columnNumber].getContents() + " | ");
            }
            System.out.println();
        }
    }

    public void setGameOver(int height, int width, Block [][] board){
        this.gameMessage.setText("Game Over!");
        for (int rowNumber = 0; rowNumber < height; rowNumber++){
            for (int columnNumber = 0; columnNumber < width; columnNumber++){
                board[rowNumber][columnNumber].setGameOver();
            }
        }
    }


    public void updateBoard(int height, int width, Block [][] board, GridPane playingArea){
        for (int rowNumber = 0; rowNumber < height; rowNumber++){
            for (int columnNumber = 0; columnNumber < width; columnNumber++){

                playingArea.add(board[rowNumber][columnNumber].getBlock(),columnNumber, rowNumber);
            }
        }
        this.checkForWin();
        this.remainingMinesMessage.setText("Remaining Mines: " + this.remainingMines);
        playingArea.add(this.gameMessage, 3,15,5,1);
        playingArea.add(this.remainingMinesMessage, 2,16,7,1);
    }


    public void findAdjacentEmptySpace(int height, int width, Block [][] board, int row, int column) {
        System.out.println("Clicked on " + row + ", " + column);
        System.out.println("Looking for adjacent empty space");
        int i = 0;
        while (i < 10) {


            for (int rowNumber = 0; rowNumber < height; rowNumber++) {
                for (int columnNumber = 0; columnNumber < width; columnNumber++) {
                    if (board[rowNumber][columnNumber].getClicked() && board[rowNumber][columnNumber].getContents().equals(" ")) {
                        System.out.println("Visible and empty");

                        if (rowNumber > 0) {
                            if (board[rowNumber - 1][columnNumber].getContents().equals(" ")) {
                                board[rowNumber - 1][columnNumber].setEmptyPicture();
                                board[rowNumber - 1][columnNumber].setClicked();
                            }
                        }
                        if (rowNumber < height - 1) {
                            if (board[rowNumber + 1][columnNumber].getContents().equals(" ")) {
                                board[rowNumber + 1][columnNumber].setEmptyPicture();
                                board[rowNumber + 1][columnNumber].setClicked();
                            }
                        }
                        if (columnNumber > 0) {
                            if (board[rowNumber][columnNumber - 1].getContents().equals(" ")) {
                                board[rowNumber][columnNumber - 1].setEmptyPicture();
                                board[rowNumber][columnNumber - 1].setClicked();
                            }
                        }
                        if (columnNumber < width - 1) {
                            if (board[rowNumber][columnNumber + 1].getContents().equals(" ")) {
                                board[rowNumber][columnNumber + 1].setEmptyPicture();
                                board[rowNumber][columnNumber + 1].setClicked();
                            }
                        }
                        if (columnNumber > 0 && rowNumber > 0) {
                            if (board[rowNumber - 1][columnNumber - 1].getContents().equals(" ")) {
                                board[rowNumber - 1][columnNumber - 1].setEmptyPicture();
                                board[rowNumber - 1][columnNumber - 1].setClicked();
                            }
                        }
                        if (columnNumber > 0 && rowNumber < height - 1) {
                            if (board[rowNumber + 1][columnNumber - 1].getContents().equals(" ")) {
                                board[rowNumber + 1][columnNumber - 1].setEmptyPicture();
                                board[rowNumber + 1][columnNumber - 1].setClicked();
                            }
                        }
                        if (columnNumber < width - 1 && rowNumber > 0) {
                            if (board[rowNumber - 1][columnNumber + 1].getContents().equals(" ")) {
                                board[rowNumber - 1][columnNumber + 1].setEmptyPicture();
                                board[rowNumber - 1][columnNumber + 1].setClicked();
                            }
                        }
                        if (columnNumber < width - 1 && rowNumber < height - 1) {
                            if (board[rowNumber + 1][columnNumber + 1].getContents().equals(" ")) {
                                board[rowNumber + 1][columnNumber + 1].setEmptyPicture();
                                board[rowNumber + 1][columnNumber + 1].setClicked();
                            }
                        }
                    }
                }
            }
        i++;
        }
    }

    public void findAdjacentMines(int height, int width, Block [][] board){
        for (int rowNumber = 0; rowNumber < height; rowNumber++) {
            for (int columnNumber = 0; columnNumber < width; columnNumber++) {
                if (!board[rowNumber][columnNumber].getContents().equals("Mine")) {
                    if (rowNumber > 0) {
                        if (board[rowNumber - 1][columnNumber].getContents().equals("Mine")) {
                            System.out.println(rowNumber + ", " + columnNumber + " has a mine above it");
                            board[rowNumber][columnNumber].increaseNumberOfAdjacentMines();
                        }
                    }
                    if (rowNumber < height - 1) {
                        if (board[rowNumber + 1][columnNumber].getContents().equals("Mine")) {
                            System.out.println(rowNumber + ", " + columnNumber + " has a mine below it");
                            board[rowNumber][columnNumber].increaseNumberOfAdjacentMines();
                        }
                    }
                    if (columnNumber > 0) {
                        if (board[rowNumber][columnNumber - 1].getContents().equals("Mine")) {
                            System.out.println(rowNumber + ", " + columnNumber + " has a mine to the left of it");
                            board[rowNumber][columnNumber].increaseNumberOfAdjacentMines();
                        }
                    }
                    if (columnNumber < width - 1) {
                        if (board[rowNumber][columnNumber + 1].getContents().equals("Mine")) {
                            System.out.println(rowNumber + ", " + columnNumber + " has a mine to the right of it");
                            board[rowNumber][columnNumber].increaseNumberOfAdjacentMines();
                        }
                    }
                    if (columnNumber > 0 && rowNumber > 0) {
                        if (board[rowNumber - 1][columnNumber - 1].getContents().equals("Mine")) {
                            System.out.println(rowNumber + ", " + columnNumber + " has a mine up-left");
                            board[rowNumber][columnNumber].increaseNumberOfAdjacentMines();
                        }
                    }
                    if (columnNumber > 0 && rowNumber < height - 1) {
                        if (board[rowNumber + 1][columnNumber - 1].getContents().equals("Mine")) {
                            System.out.println(rowNumber + ", " + columnNumber + " has a mine down-left");
                            board[rowNumber][columnNumber].increaseNumberOfAdjacentMines();
                        }
                    }
                    if (columnNumber < width - 1 && rowNumber > 0) {
                        if (board[rowNumber - 1][columnNumber + 1].getContents().equals("Mine")) {
                            System.out.println(rowNumber + ", " + columnNumber + " has a mine up-right");
                            board[rowNumber][columnNumber].increaseNumberOfAdjacentMines();
                        }
                    }
                    if (columnNumber < width - 1 && rowNumber < height - 1) {
                        if (board[rowNumber + 1][columnNumber + 1].getContents().equals("Mine")) {
                            System.out.println(rowNumber + ", " + columnNumber + " has a mine down-right");
                            board[rowNumber][columnNumber].increaseNumberOfAdjacentMines();
                        }
                    }
                }
            }
        }
    }





}
