package mm.minemopper;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;

public class Block {
    private final int row;
    private final int column;
    private String contents;
    private int numberOfAdjacentMines;
    private Image imageFile;
    private ImageView image;
    private Boolean clicked;
    private Boolean gameIsOn;

    public Block(int row, int column, UI ui, int height, int width, Block [][] board) {
        this.row = row;
        this.column = column;
        this.contents = " ";
        this.numberOfAdjacentMines = 0;

        this.imageFile = new Image("file:images/blank.png");
        this.image = new ImageView(this.imageFile);

        this.clicked = false;
        this.gameIsOn = true;

        this.setMouseListener(ui, height, width, board);
    }

    public void setGameOver(){
       this.gameIsOn = false;
    }

    public void setMouseListener(UI ui, int height, int width, Block [][] board){
            this.image.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY){
                    this.clickedLeftButton(ui, height, width, board);
                    System.out.println("Clicked");
                }
                else if (event.getButton() == MouseButton.SECONDARY){
                    this.clickedRightButton(ui, height, width, board);
                }
            });

    }

    public void clickedLeftButton(UI ui, int height, int width, Block [][] board){
        if(this.gameIsOn) {
            System.out.println("clicked");
            this.updatePicture(ui, height, width, board);
        }
   }

    public void clickedRightButton(UI ui, int height, int width, Block [][] board){
        if(this.gameIsOn){
        if (!this.clicked) {
            this.imageFile = new Image("file:images/flag.png");
            this.image = new ImageView(this.imageFile);
            this.clicked = true;
            System.out.println("right clicked");
            if(this.contents.equals("Mine")){
                System.out.println("FOUND MINE!");
                ui.decreaseMines();
            }
        }else{
            this.clicked = false;
            this.imageFile = new Image("file:images/blank.png");
            this.image = new ImageView(this.imageFile);
            if(this.contents.equals("Mine")){
                System.out.println("NOT FOUND MINE!");
                ui.increaseMines();
            }
        }
        this.setMouseListener(ui, height, width, board);
        }
    }

    public void updatePicture(UI ui, int height, int width, Block [][] board){
        if (this.contents.equals("Mine")){
            this.imageFile = new Image("file:images/mine.png");
            System.out.println("Game over");
            ui.setGameOver(height, width, board);
        }else if (this.numberOfAdjacentMines == 0){
            System.out.println("Empty");
            this.clicked=true;
            this.imageFile = new Image("file:images/empty.png");
            ui.findAdjacentEmptySpace(height, width, board, this.row, this.column);

        }else{
            this.imageFile = new Image("file:images/" + this.numberOfAdjacentMines + ".png");
        }
        this.image = new ImageView(this.imageFile);
    }

    public void setClicked(){
        this.clicked = true;
    }

    public Boolean getClicked(){
        return this.clicked;
    }

    public void setEmptyPicture(){
        this.imageFile = new Image("file:images/empty.png");
        this.image = new ImageView(this.imageFile);
    }



    public String getContents(){
        return this.contents;
    }

    public void setContents(String contents){
        this.contents = contents;
    }

    public void increaseNumberOfAdjacentMines(){
        this.numberOfAdjacentMines++;
        this.contents = String.valueOf(this.numberOfAdjacentMines);
    }

    public ImageView getBlock(){
        return this.image;
    }

    @Override
    public String toString() {
        return "Block{" +
                "row=" + row +
                ", column=" + column +
                ", contents='" + contents +
                ", adjacent mines = " + numberOfAdjacentMines + '\'' +
                '}';
    }
}
