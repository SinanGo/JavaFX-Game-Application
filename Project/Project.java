
/*
 
  This program was prepared for Programming-ll final project.
  
  Evren Koymat - 150120518
  Sinan Göçmen - 150120519
  
 */

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Project extends Application {

	//it is just a property for an animation. We put this here to reach easily.
	//There will be more explanation later.
	boolean get_bigger=false;
	
	public static void main(String[] args) {
	launch(args);	
	}

	@Override
	public void start(Stage main_stage) throws Exception {
	
	
	//There are separate stages for each level.	
	Stage level1_stage = new Stage();
	level1_stage.setTitle("Level-1");
	level1_stage.setScene(new Scene(new BorderPane_for_L1()));
	
	Stage level2_stage = new Stage();
	level2_stage.setTitle("Level-2");
	level2_stage.setScene(new Scene(new BorderPane_for_L2()));
	
	Stage level3_stage = new Stage();
	level3_stage.setTitle("Level-3");
	level3_stage.setScene(new Scene(new BorderPane_for_L3()));
	
	Stage level4_stage = new Stage();
	level4_stage.setTitle("Level-4");
	level4_stage.setScene(new Scene(new BorderPane_for_L4()));
	
	Stage level5_stage = new Stage();
	level5_stage.setTitle("Level-5");
	level5_stage.setScene(new Scene(new BorderPane_for_L5()));
	
	
	
	//This is for the main menu.It has three other panes in it.
	BorderPane menu_pane = new BorderPane();
	
	
	StackPane menu_top = new StackPane();//for the title of the game
	GridPane menu_center = new GridPane();//for an animation to make menu more beautiful
	VBox menu_right = new VBox(20);//for the level buttons
	
	
	menu_pane.setTop(menu_top);
	menu_pane.setCenter(menu_center);
	menu_pane.setRight(menu_right);
	
	menu_center.setStyle("-fx-border-color: black");
	menu_right.setStyle("-fx-border-color: black");
	menu_right.setStyle("-fx-border-color: black");
	
	//************************************************************
	
	//the top pane
	
	//there is the name of the game at the top.
	
	menu_top.setPadding(new Insets(20,50,20,50));
	
	//It is a dummy rectangle to make menu_top fixed. Its opacity is zero
	Rectangle rectangle = new Rectangle();
	rectangle.setWidth(70);
	rectangle.setHeight(70);
	rectangle.setLayoutX(0);
	rectangle.setLayoutY(0);
	rectangle.setOpacity(0);
	
	//Settings about the title
	Label game_title = new Label("Boooom!");
	game_title.setFont(Font.font("Times New Roman", 
			FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 50));
	game_title.setAlignment(Pos.CENTER);

	//It is an animation which provides the title gets bigger and smaller constantly
	EventHandler<ActionEvent> event_handler = e -> {
		
		//When it is small enough, it starts to get bigger and vice versa
		if(game_title.getFont().getSize()<=40)
		get_bigger = true;
		else if(game_title.getFont().getSize()>=50)
		get_bigger = false;
		
		if(get_bigger) {
			game_title.setFont(Font.font("Times New Roman", 
			FontWeight.EXTRA_BOLD, FontPosture.ITALIC, game_title.getFont().getSize()+1));
		}
		else {
			game_title.setFont(Font.font("Times New Roman", 
			FontWeight.EXTRA_BOLD, FontPosture.ITALIC, game_title.getFont().getSize()-1));
		}	
	
	};
	
	Timeline animation = new Timeline(new KeyFrame(Duration.millis(20),event_handler));
	animation.setCycleCount(Timeline.INDEFINITE);
	animation.play();
	
	menu_top.getChildren().addAll(rectangle,game_title);
	
	//*************************************************************
	
	//the right pane 
	
	//there is a title and there are buttons to reach the levels
	
	Label level_title = new Label("Levels");
	level_title.setFont(Font.font("Times New Roman", 
			FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 19));
	level_title.setAlignment(Pos.CENTER);
	
	Button level1_button = new Button("level 1");
	Button level2_button = new Button("level 2");
	Button level3_button = new Button("level 3");
	Button level4_button = new Button("level 4");
	Button level5_button = new Button("level 5");
	Label warning_label = new Label("The previous \nlevel is not \ndone!!!");
	warning_label.setTextFill(Color.DARKRED);
	//when a button is clicked, a specific stage is shown for each level
	//But if the previous level is not done, button does not work
	level1_button.setOnAction(e -> {
		level1_stage.show();
	});
	
	level2_button.setOnAction(e -> {
		if(BorderPane_for_L1.is_it_done)
		level2_stage.show();
		else {
			if(!menu_right.getChildren().contains(warning_label))
			menu_right.getChildren().addAll(warning_label);
		}
	});
	
	level3_button.setOnAction(e -> {
		if(BorderPane_for_L2.is_it_done)
		level3_stage.show();
		else {
			if(!menu_right.getChildren().contains(warning_label))
			menu_right.getChildren().addAll(warning_label);
		}
	});
	
	level4_button.setOnAction(e -> {
		if(BorderPane_for_L3.is_it_done)
		level4_stage.show();
		else {
			if(!menu_right.getChildren().contains(warning_label))
			menu_right.getChildren().addAll(warning_label);
		}
	});
	
	level5_button.setOnAction(e -> {
		if(BorderPane_for_L4.is_it_done)
		level5_stage.show();
		else {
			if(!menu_right.getChildren().contains(warning_label))
			menu_right.getChildren().addAll(warning_label);
		}
	});
	
	menu_right.getChildren().addAll(level_title,
			level1_button,level2_button,level3_button,level4_button,level5_button);
	menu_right.setPadding(new Insets(20,50,50,45));
	
	//****************************************************************************
	
	//menu_left
	
	//There is a pane just like game board. the rectangle colors change constantly 
	menu_center.setStyle("-fx-border-color: black;-fx-background-color: darkgray");
	menu_center.setPadding(new Insets(5,2,2,2));
	menu_center.setHgap(2);
	menu_center.setVgap(2);
	
	//A loop for creating the rectangles and put them to the pane
	for(int i = 0; i<=8;i++) {
		for(int j = 0;j<=8;j++) {
			Rectangle r = new Rectangle();
			r.setWidth(36);
			r.setHeight(39);
			r.setLayoutX(j*36);
			r.setLayoutY(i*49);
			r.setStroke(Color.BLACK);
			
			//random coloring
			EventHandler<ActionEvent> event_handler_2 = e -> {
				
			int random_value = (int)(Math.random()*4);
			
			if(random_value == 0)
				r.setFill(Color.WHITE);
			else if(random_value == 1)
				r.setFill(Color.DARKGRAY);
			else if(random_value == 2)
				r.setFill(Color.BLUE);
			else
				r.setFill(Color.PURPLE);
			};
			
			//This is an animation which provides rectangles to change their color every second
			Timeline animation_2 = new Timeline(new KeyFrame(Duration.millis(1000),event_handler_2));
			animation_2.setCycleCount(Timeline.INDEFINITE);
			animation_2.play();
			
			menu_center.add(r,i,j);
		}
	}
	
	//****************************************************************************
	
	//Settings about the main stage
	main_stage.setScene(new Scene(menu_pane,500,500));
	main_stage.setTitle("Boooom!");
	main_stage.show();
	

	}
		
	
}
