
/*
 
  This program was prepared for Programming-ll final project.
  
  Evren Koymat - 150120518
  Sinan Göçmen - 150120519
  
 */

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//this is the border pane of the level one which extends BorderPane.
//It contains three pane, which are the game board,the top board and the bottom board.
public class BorderPane_for_L1 extends BorderPane {
	
	//We create these panes and then add them to the border pane.
	
	Game_Board1 game_board1 = new Game_Board1();
	Top_Board1 top_board1 = new Top_Board1();
	Bottom_Board1 bottom_board1 = new Bottom_Board1();
	
	public double counter;					// It will count the number of rectangle which refill with blue or white color
	public static double points;			// It will count the total point of current level
	public static double high_score;		//It will store the highest score in a level.
	public static boolean is_it_done;		//It determines if the level is done or not

	public BorderPane_for_L1() {

		setCenter(game_board1);
		setTop(top_board1);
		setBottom(bottom_board1);
	}


//**************************************************************************
//the game board is a GridPane. It contains 100 rectangles.
class Game_Board1 extends GridPane {

	//We define an array to save and "find" rectangles easily.
	
	rectangle[][] r_list = new rectangle[10][10];
	
	public Game_Board1() {	
		//the build method creates the rectangles and add them to specific positions.
		build();
	}
	
	public void build() {
		//Following lines are created to design the board. 
		setStyle("-fx-border-color: black;-fx-background-color: darkgray");
		setPadding(new Insets(10,10,10,10));
		setHgap(5);
		setVgap(5);
		
		//Here is a really important part.
		//For each position, we create a rectangle and add it to the pane and also to the array.
		for(int i = 0;i<10;i++) {
			for(int j = 0;j<10;j++) {
				rectangle r = new rectangle(i,j);
				r_list[i][j] = r;
				add(r, i, j);	
				//When we click the mouse, destroy all function is activated.
				r.setOnMouseClicked(e -> {destroy_all(r);
				//points variable changes according to counter variable.
				if (counter == 1)
					points -= 3;
				else if (counter == 2)
					points -=1;
				else if (counter == 3)
					points +=1;
				else if (counter ==4)
					points +=2;
				else if (counter == 5)
					points +=4;
		
				if (points >= high_score)
					high_score = points;
				
				// We create Labels to add top board of the pane.
				Label name = new Label("Level # 1									");
				Label point = new Label("" + points);
				Label score = new Label("								High Score: " +high_score);
				
				//We use the top_board1.getChildren().clear method to delete previous point and score. So each mouseclicked event changes the point and score.
				top_board1.getChildren().clear();
				top_board1.getChildren().add(name);
				top_board1.getChildren().add(point);
				top_board1.getChildren().add(score);
				//We need to assing counter variable to 0 because each mouseclick event destroys & affects different rectangles and different number of rectangles.
				counter = 0;
				});
			}
		}
		//These are just settings for level-1. 
		r_list[1][7].setFill(Color.PURPLE);r_list[1][7].setDestroyable(true);
		r_list[1][8].setFill(Color.PURPLE);r_list[1][8].setDestroyable(true);
		r_list[2][8].setFill(Color.PURPLE);r_list[2][8].setDestroyable(true);
		
		r_list[7][1].setFill(Color.BLUE);r_list[7][1].setDestroyable(true);
		r_list[8][2].setFill(Color.BLUE);r_list[8][2].setDestroyable(true);
		r_list[8][1].setFill(Color.BLUE);r_list[8][1].setDestroyable(true);
		
		r_list[4][4].setFill(Color.WHITE);
		r_list[4][5].setFill(Color.WHITE);
		r_list[5][4].setFill(Color.WHITE);
		r_list[5][5].setFill(Color.WHITE);
		
	}
	
	//This method gets a rectangle and find the other rectangles, which are around the rectangle, to destroy.
	//There is a separation for each corner and edge.
	//For example, when we click 0,8 , we destroy 0,8-0,9-1,8 at the same time if all of them are available,which means purple or blue.
	//If they are not, we do not anything to them. We can understand this by using IsDestroyable() method.
 	public void destroy_all(rectangle r) {
		//We get the coordinates of the rectangle to simplify.
		int i = r.getX_coordinate();
		int j = r.getY_coordinate();
		
		if(i==0 && j==0 && r.IsDestroyable() ) {
			if(r_list[i][j].IsDestroyable()) {
				r_list[i][j].destroy();
				//when we destroy a rectangle counter variable increase 1.
				counter +=1;
				//we need to ith and jth values for destroyed rectangle to print the bottom page.
				Label box = new Label("Box:" +i + "-" +j );
				//.clear() method helps us to clear the previous bottom page. So we can change the Box ith and jth values according to destroyed rectangles.
				bottom_board1.getChildren().clear();
				bottom_board1.getChildren().add(box);
			}
			if(r_list[i+1][j].IsDestroyable()) {
				r_list[i+1][j].destroy();
				counter +=1;
				//hit# label keeps the value of affected rectangle.
				Label hit1 = new Label("		Hit:" + (i+1) +"-" + j);
				bottom_board1.getChildren().add(hit1);
			}
			if(r_list[i][j+1].IsDestroyable()) {
				r_list[i][j+1].destroy();
				counter +=1;
				Label hit2 = new Label("			Hit:" + i +"-" + (j+1));
				bottom_board1.getChildren().add(hit2);
				}
			//according to counter value we need to print bottom page the value of gained or lost points
			if (counter == 1)
				bottom_board1.getChildren().add(new Label("											(-3 points)"));
			else if (counter == 2)
				bottom_board1.getChildren().add(new Label("											(-1 points)"));
			else if (counter == 3)
				bottom_board1.getChildren().add(new Label("											(+1 points)"));
			else if (counter ==4)
				bottom_board1.getChildren().add(new Label("											(+2 points)"));
			else if (counter == 5)
				bottom_board1.getChildren().add(new Label("											(+4 points)"));
		
		}
		else if(i==0 && j==9 && r.IsDestroyable() ) {
			if(r_list[i][j].IsDestroyable()) {
				r_list[i][j].destroy();
				counter +=1;
				Label box = new Label("Box:" +i + "-" +j );
				bottom_board1.getChildren().clear();
				bottom_board1.getChildren().add(box);
				}
			if(r_list[i+1][j].IsDestroyable()) {
				r_list[i+1][j].destroy();
				counter +=1;
				Label hit3 = new Label("		Hit:" + (i+1) +"-" + j);
				bottom_board1.getChildren().add(hit3);
				}
			if(r_list[i][j-1].IsDestroyable()) {
				r_list[i][j-1].destroy();
				counter +=1;
				Label hit4 = new Label("			Hit:" + i +"-" + (j-1));
				bottom_board1.getChildren().add(hit4);
				}
			if (counter == 1)
				bottom_board1.getChildren().add(new Label("											(-3 points)"));
			else if (counter == 2)
				bottom_board1.getChildren().add(new Label("											(-1 points)"));
			else if (counter == 3)
				bottom_board1.getChildren().add(new Label("											(+1 points)"));
			else if (counter ==4)
				bottom_board1.getChildren().add(new Label("											(+2 points)"));
			else if (counter == 5)
				bottom_board1.getChildren().add(new Label("											(+4 points)"));
		
		}
		else if(i==9 && j==9 && r.IsDestroyable() ) {
			if(r_list[i][j].IsDestroyable()) {
				r_list[i][j].destroy();
				counter +=1;
				Label box = new Label("Box:" +i + "-" +j );
				bottom_board1.getChildren().clear();
				bottom_board1.getChildren().add(box);
				}
			if(r_list[i-1][j].IsDestroyable()) {
				r_list[i-1][j].destroy();
				counter +=1;
				Label hit5 = new Label("		Hit:" + (i-1) +"-" + j);
				bottom_board1.getChildren().add(hit5);
				}
			if(r_list[i][j-1].IsDestroyable()) {
				r_list[i][j-1].destroy();
				counter +=1;
				Label hit6 = new Label("			Hit:" + i +"-" + (j-1));
				bottom_board1.getChildren().add(hit6);
				}
			if (counter == 1)
				bottom_board1.getChildren().add(new Label("											(-3 points)"));
			else if (counter == 2)
				bottom_board1.getChildren().add(new Label("											(-1 points)"));
			else if (counter == 3)
				bottom_board1.getChildren().add(new Label("											(+1 points)"));
			else if (counter ==4)
				bottom_board1.getChildren().add(new Label("											(+2 points)"));
			else if (counter == 5)
				bottom_board1.getChildren().add(new Label("											(+4 points)"));
		
		}
		else if(i==9 && j==0 && r.IsDestroyable() ) {
			if(r_list[i][j].IsDestroyable()) {
				r_list[i][j].destroy();
				counter +=1;
				Label box = new Label("Box:" +i + "-" +j );
				bottom_board1.getChildren().clear();
				bottom_board1.getChildren().add(box);
				}
			if(r_list[i-1][j].IsDestroyable()) {
				r_list[i-1][j].destroy();
				counter +=1;
				Label hit7 = new Label("		Hit:" + (i-1) +"-" + j);
				bottom_board1.getChildren().add(hit7);
				}
			if(r_list[i][j+1].IsDestroyable()) {
				r_list[i][j+1].destroy();
				counter +=1;
				Label hit8 = new Label("			Hit:" + i +"-" + (j+1));
				bottom_board1.getChildren().add(hit8);
				}
			if (counter == 1)
				bottom_board1.getChildren().add(new Label("											(-3 points)"));
			else if (counter == 2)
				bottom_board1.getChildren().add(new Label("											(-1 points)"));
			else if (counter == 3)
				bottom_board1.getChildren().add(new Label("											(+1 points)"));
			else if (counter ==4)
				bottom_board1.getChildren().add(new Label("											(+2 points)"));
			else if (counter == 5)
				bottom_board1.getChildren().add(new Label("											(+4 points)"));
		
		}
		else if(i==0 && j>0 && r.IsDestroyable() ) {
			if(r_list[i][j].IsDestroyable()) {
				r_list[i][j].destroy();
				counter +=1;
				Label box = new Label("Box:" +i + "-" +j );
				bottom_board1.getChildren().clear();
				bottom_board1.getChildren().add(box);
				}
			if(r_list[i+1][j].IsDestroyable()) {
				r_list[i+1][j].destroy();
				counter +=1;
				Label hit9 = new Label("		Hit:" + (i+1) +"-" + j);
				bottom_board1.getChildren().add(hit9);
				}
			if(r_list[i][j+1].IsDestroyable()) {
				r_list[i][j+1].destroy();
				counter +=1;
				Label hit10 = new Label("			Hit:" + i +"-" + (j+1));
				bottom_board1.getChildren().add(hit10);
				}
			if(r_list[i][j-1].IsDestroyable()) {
				r_list[i][j-1].destroy();
				counter +=1;
				Label hit11 = new Label("				Hit:" + i +"-" + (j-1));
				bottom_board1.getChildren().add(hit11);
				}
			if (counter == 1)
				bottom_board1.getChildren().add(new Label("											(-3 points)"));
			else if (counter == 2)
				bottom_board1.getChildren().add(new Label("											(-1 points)"));
			else if (counter == 3)
				bottom_board1.getChildren().add(new Label("											(+1 points)"));
			else if (counter ==4)
				bottom_board1.getChildren().add(new Label("											(+2 points)"));
			else if (counter == 5)
				bottom_board1.getChildren().add(new Label("											(+4 points)"));
		
		}
		else if(i>0 && j==0 && r.IsDestroyable() ) {
			if(r_list[i][j].IsDestroyable()) {
				r_list[i][j].destroy();
				counter +=1;
				Label box = new Label("Box:" +i + "-" +j );
				bottom_board1.getChildren().clear();
				bottom_board1.getChildren().add(box);
				}
			if(r_list[i+1][j].IsDestroyable()) {
				r_list[i+1][j].destroy();
				counter +=1;
				Label hit12 = new Label("		Hit:" + (i+1) +"-" + j);
				bottom_board1.getChildren().add(hit12);
				}
			if(r_list[i-1][j].IsDestroyable()) {
				r_list[i-1][j].destroy();
				counter +=1;
				Label hit13 = new Label("			Hit:" + (i-1) +"-" + j);
				bottom_board1.getChildren().add(hit13);
				}
			if(r_list[i][j+1].IsDestroyable()) {
				r_list[i][j+1].destroy();
				counter +=1;
				Label hit14 = new Label("				Hit:" + i +"-" + (j+1));
				bottom_board1.getChildren().add(hit14);
				}
			if (counter == 1)
				bottom_board1.getChildren().add(new Label("											(-3 points)"));
			else if (counter == 2)
				bottom_board1.getChildren().add(new Label("											(-1 points)"));
			else if (counter == 3)
				bottom_board1.getChildren().add(new Label("											(+1 points)"));
			else if (counter ==4)
				bottom_board1.getChildren().add(new Label("											(+2 points)"));
			else if (counter == 5)
				bottom_board1.getChildren().add(new Label("											(+4 points)"));
		
		}
		else if(i==9 && j<9 && r.IsDestroyable() ) {
			if(r_list[i][j].IsDestroyable()) {
				r_list[i][j].destroy();
				counter +=1;
				Label box = new Label("Box:" +i + "-" +j );
				bottom_board1.getChildren().clear();
				bottom_board1.getChildren().add(box);
				}
			if(r_list[i][j+1].IsDestroyable()) {
				r_list[i][j+1].destroy();
				counter +=1;
				Label hit15 = new Label("		Hit:" + i +"-" + (j+1));
				bottom_board1.getChildren().add(hit15);
				}
			if(r_list[i][j-1].IsDestroyable()) {
				r_list[i][j-1].destroy();
				counter +=1;
				Label hit16 = new Label("			Hit:" + i +"-" + (j-1));
				bottom_board1.getChildren().add(hit16);
				}
			if(r_list[i-1][j].IsDestroyable()) {
				r_list[i-1][j].destroy();
				counter +=1;
				Label hit17 = new Label("				Hit:" + (i-1) +"-" + j);
				bottom_board1.getChildren().add(hit17);
				}
			if (counter == 1)
				bottom_board1.getChildren().add(new Label("											(-3 points)"));
			else if (counter == 2)
				bottom_board1.getChildren().add(new Label("											(-1 points)"));
			else if (counter == 3)
				bottom_board1.getChildren().add(new Label("											(+1 points)"));
			else if (counter ==4)
				bottom_board1.getChildren().add(new Label("											(+2 points)"));
			else if (counter == 5)
				bottom_board1.getChildren().add(new Label("											(+4 points)"));
		
		}
		else if(i<9 && j==9 && r.IsDestroyable() ) {
			if(r_list[i][j].IsDestroyable()) {
				r_list[i][j].destroy();
				counter +=1;
				Label box = new Label("Box:" +i + "-" +j );
				bottom_board1.getChildren().clear();
				bottom_board1.getChildren().add(box);
				}
			if(r_list[i+1][j].IsDestroyable()) {
				r_list[i+1][j].destroy();
				counter +=1;
				Label hit18 = new Label("		Hit:" + (i+1) +"-" + j);
				bottom_board1.getChildren().add(hit18);
				}
			if(r_list[i-1][j].IsDestroyable()) {
				r_list[i-1][j].destroy();
				counter +=1;
				Label hit19 = new Label("			Hit:" + (i-1) +"-" + j);
				bottom_board1.getChildren().add(hit19);
				}
			if(r_list[i][j-1].IsDestroyable()) {
				r_list[i][j-1].destroy();
				counter +=1;
				Label hit20 = new Label("				Hit:" + i +"-" + (j-1));
				bottom_board1.getChildren().add(hit20);
				}
			if (counter == 1)
				bottom_board1.getChildren().add(new Label("											(-3 points)"));
			else if (counter == 2)
				bottom_board1.getChildren().add(new Label("											(-1 points)"));
			else if (counter == 3)
				bottom_board1.getChildren().add(new Label("											(+1 points)"));
			else if (counter ==4)
				bottom_board1.getChildren().add(new Label("											(+2 points)"));
			else if (counter == 5)
				bottom_board1.getChildren().add(new Label("											(+4 points)"));
		
		}
		else if(r.IsDestroyable()) {
			if(r_list[i][j].IsDestroyable()) {
				r_list[i][j].destroy();
				counter +=1;
				Label box = new Label("Box:" +i + "-" +j );
				bottom_board1.getChildren().clear();
				bottom_board1.getChildren().add(box);
				}
			if(r_list[i+1][j].IsDestroyable()) {
				r_list[i+1][j].destroy();
				counter +=1;
				Label hit21 = new Label("		Hit:" + (i+1) +"-" + j);
				bottom_board1.getChildren().add(hit21);
				}
			if(r_list[i-1][j].IsDestroyable()) {
				r_list[i-1][j].destroy();
				counter +=1;
				Label hit22 = new Label("			Hit:" + (i-1) +"-" + j);
				bottom_board1.getChildren().add(hit22);
				}
			if(r_list[i][j-1].IsDestroyable()) {
				r_list[i][j-1].destroy();
				counter +=1;
				Label hit23 = new Label("				Hit:" + i +"-" + (j-1));
				bottom_board1.getChildren().add(hit23);
				}
			if(r_list[i][j+1].IsDestroyable()) {
				r_list[i][j+1].destroy();
				Label hit24 = new Label("					Hit:" + i +"-" + (j+1));
				bottom_board1.getChildren().add(hit24);
				counter +=1;
				}
			if (counter == 1)
				bottom_board1.getChildren().add(new Label("											(-3 points)"));
			else if (counter == 2)
				bottom_board1.getChildren().add(new Label("											(-1 points)"));
			else if (counter == 3)
				bottom_board1.getChildren().add(new Label("											(+1 points)"));
			else if (counter ==4)
				bottom_board1.getChildren().add(new Label("											(+2 points)"));
			else if (counter == 5)
				bottom_board1.getChildren().add(new Label("											(+4 points)"));
		}
		
		//we need to check all rectangle if their colors either white or darkgray.If all the rectangles filled like this, our level is completed.
		int r_count = 0;
        for ( int m = 0 ; m < 10 ; m++) {
            for (int n = 0 ; n<10 ; n++) {
                if ((r_list[m][n].getFill() == Color.WHITE || r_list[m][n].getFill() == Color.DARKGRAY)) {
                   r_count +=1;      
            }
            if(r_count ==100)
            is_it_done=true;

            }
        }
        //When our level is completed , we print the next level is available now! message to the bottom page.
        if(is_it_done == true && r_count == 100) {
            bottom_board1.getChildren().clear();
            Label nextLevel = new Label("next level is available now!");
            nextLevel.setAlignment(Pos.CENTER);
            bottom_board1.setPadding(new Insets(30,30,30,30));
            bottom_board1.getChildren().add(nextLevel);
            nextLevel.setLayoutX(220);
            nextLevel.setLayoutY(13);
         
        }
	}//end of the destroy all function.
}// end of the game board 1.

//**************************************************************************
//This is the top board of the level-1
class Top_Board1 extends HBox{
	
	public Top_Board1() {
		build();
	}
	
	
	public void build() {
		
		setPadding(new Insets(10,10,10,10));
		setStyle("-fx-border-color: black");
		Label name = new Label("Level # 1					 		 		 ");
		Label point = new Label("" + points);
		Label score = new Label("								High Score: " + high_score);
		getChildren().add(name);
		getChildren().add(point);
		getChildren().add(score);
	}
	
}

//**************************************************************************
//This is the bottom board of the level-1
class Bottom_Board1 extends Pane{
	
	public Bottom_Board1() {
		build();
	}
	
	public void build() {
		
		setPadding(new Insets(10,10,10,10));
		setStyle("-fx-border-color: black");
		Label name1 = new Label("---Text---");
		getChildren().add(name1);
		name1.setLayoutY(5);
		
	}
}

//***************************************************************************
// There is a tricky part here! We created a class named rectangle which extends 'Rectangle'.
// We add some useful properties to rectangle.
class rectangle extends Rectangle {
	
	boolean Destroyable = false;//determines if the rectangle is destroyable
	//As default, rectangles are all brown and not destroyable. We set it when we build the game board.
	int x_coordinate;//better than getX(), because give the information as coordinate, not pixel count or etc.
	int y_coordinate;//
	
	public rectangle (int x, int y)  {	
		//Just some settings for default rectangle.
		this.setHeight(50);
		this.setWidth(50);
		this.x_coordinate = x;
		this.y_coordinate = y;
		this.setFill(Color.DARKGRAY);
		this.setStroke(Color.BLACK);
	}

	//Getters and setters
	public boolean IsDestroyable() {
		return Destroyable;
	}

	public int getX_coordinate() {
		return x_coordinate;
	}

	public int getY_coordinate() {
		return y_coordinate;
	}

	public void setDestroyable(boolean Destroyable) {
		this.Destroyable = Destroyable;
	}

	public void setX_coordinate(int x) {
		this.x_coordinate = x;
	}

	public void setY_coordinate(int y) {
		this.y_coordinate = y;
	}

	//This method changes the color of the rectangle.
public void destroy() {
		
	//If it is purple, it turns the color to blue
	
		if(getFill()==Color.PURPLE) {
			setFill(Color.BLUE);
		}
		
	//If it is blue, it turns the color to white and set destroyable property as false. 
		
		else if(getFill()==Color.BLUE) {
			setFill(Color.WHITE);
			setDestroyable(false);
		}

	}

}

}


