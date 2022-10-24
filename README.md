# JavaFX-Game-Application

Introduction : 

This application is a kind of game named “Boooom!” . We are asked to do this by using JavaFX 
framework, which is a set of graphics that programmers use to create complex applications.

In this game, there are 5 levels and each of them includes several types of rectangles. The aim
is to destroy rectangles by considering their durability and clicking as few as possible. When all the
rectangles are destroyed, next level becomes available. The usage of the game will be explained in
more detail in the implementation part.


Implementation :

In this program, there are 6 main classes. One of them is for the main menu, the others are for the levels. When we open project.java class in eclipse and start the program, The main menu is shown like this. At the top of the menu, there is the title which has a constant motion. At left, there is a simple example of a game board. It has a constant motion as well. At right, there are separate buttons to reach the levels one by one. When the previous level is done, the button for the next level becomes active. If we try to reach next level without completing the previous one, A warning text occurs just under of the buttons. When we click a button, a different window arises. For each level, there is a specific stage and window.


![image](https://user-images.githubusercontent.com/105942580/197603219-badc3e58-af7d-4055-8874-8fb0a60ff16a.png)

Main Menu :

In the main menu, there is a border pane which has three other panes named menu_top, menu_center and menu_right. Each of them is created with different types of pane according to their purposes.

![image](https://user-images.githubusercontent.com/105942580/197603295-03858501-119e-4b30-a0fd-d0fca9bbf33f.png)

In menu_top, there is just a label to show the title of the game. But it is more than that. There is an animation in this part. The get_bigger property depends on the size of the title “Boooom!”.
When it becomes 30, get_bigger becomes true and activate an if statement which provides the title is getting bigger until it is 50. Then get_bigger becomes false and title is getting smaller until it is 30 and so on.

The creation of the title :

![image](https://user-images.githubusercontent.com/105942580/197603391-e202a92e-da8b-4e41-a1f7-7a9e15dea809.png)


The creation of the animation : 

![image](https://user-images.githubusercontent.com/105942580/197603434-63eb7104-4e8c-494b-9013-eeaabb4b0c4e.png)

In the menu_right , there is a title “Levels” and 5 buttons for each level. There is an also warning label which occurs when we try to reach levels without doing the previous one.

Each button has an action. When level_x button is pressed, level_x_stage.show() method becomes active and the window for level_x arises. Also we check if the previous level is done or not in setOnAction method as in the fallowing figure.

![image](https://user-images.githubusercontent.com/105942580/197603509-9ed388d5-e022-4070-aeb8-50b7d7e9c30c.png)


In the menu_left, There is a for loop. This loop creates 10x10 rectangles and put them to a specific position. Then it gives them random numbers from 0 to 4 to fill them in different random colors. The colors change every second thanks to a TimeLine animation as in the figure.

![image](https://user-images.githubusercontent.com/105942580/197603548-7b032d91-2a44-4d5e-9701-77dc9e65366c.png)


Level Stages: 

As it is stated, there are separate stages for each level. Each level is a copy of each other. There are just small changes according to the unique properties of the levels like filled rectangles. In this part, the details about level-2 will be shared.

-BorderPane_for_L2 Class Hierarchy
![image](https://user-images.githubusercontent.com/105942580/197603655-0425aedc-12e2-40ea-9a24-7e77b6c595c3.png)


-Border Pane for level-2

As we stated, all level classes are actually a border pane. Level-2 border pane contains three other panes in itself as in the fallowing figure, which are the top pane, the bottom pane and the game board. We created and add them to the border pane.

![image](https://user-images.githubusercontent.com/105942580/197603744-e6a23c77-76e0-45d8-aa2f-24ff53e7399d.png)

![image](https://user-images.githubusercontent.com/105942580/197603758-2179543c-0437-4b4e-b391-37a21a99c001.png)

There are also four other properties in the border pane. Counter simply counts the number of the rectangles destroyed. Points take the point of the user by considering counter. High_score take the high score depends on the point of the user. Is_it_done property determines if the all rectangles are destroyed or not by considering their colors. More explanation will be given. 

![image](https://user-images.githubusercontent.com/105942580/197603797-d821a08d-f17f-4ca8-940e-c3d2b762f80c.png)


Rectangle Class :

We decided to create a rectangle class which extends the Rectangle class. The aim was to add new properties to make coding easier and also using the Rectangle class properties at the same time. It has destroyable, x and y properties. When a rectangle is brown or white, destroyable property is set as false and vice versa for blue and purple.

In the constructer of the rectangle, a default rectangle is created with a specific x and y property and also a default size and color. It is better to use new x and y properties than default x and y properties because they give us the pixel count. But new x and y properties give us a coordinate.

![image](https://user-images.githubusercontent.com/105942580/197603863-f690350e-4ab3-4651-8833-364490438d0b.png)


Also, the rectangle class has a method named destroy().  In destroy function, The color and the destroyable property changes as in the fallowing figure. The destroyable property is determined in the game_board2 according to the level-2 filling rules. If a rectangle is purple, destroy function makes it blue. If it is blue, then it will be white an also “not destroyable.”

![image](https://user-images.githubusercontent.com/105942580/197604111-7efcf707-6ca4-4552-85b9-45b352cd136c.png)


Top Board : 

In top board, there is just the name of the level, the point and also the high score . Additionaly, there is a small bug but it can be solved easily. More explanation will be given in the test cases.
The build function of the class provides the class to have three labels as in the fallowing figure.


![image](https://user-images.githubusercontent.com/105942580/197604193-7b15afed-79f7-4be4-979a-2ca316f134fd.png)

When we get a two-digit point, the point label does not show the point properly. But the point is stored correctly and there is a small solution. If we close the window and open it again in the main menu, we can see the point even if it is 2-digit and also can continue from where we left.

Bottom Board :

In this board, there are several labels. As in project guide, when we click on a rectangle, the coordinates of the destroyed rectangles are shown. At the end of the level, a label arises which says “the next level is available!”. The implementation of these are in the game_board2 class, so more detail will be given in that part.

Game Board :

In our game board ,we create a rectangle list to store the rectangles at first.


![image](https://user-images.githubusercontent.com/105942580/197604334-5ef2e1a4-987e-4521-9d15-c155f09b9c17.png)


![Screenshot_9](https://user-images.githubusercontent.com/105942580/197604497-36142902-deb6-470e-bb45-44c9b889c07a.png)


In the build() method, we create rectangles according to the i (row) and j (column) values in the first loop. Then , we activate the setOnMouseClicked event to destroy boxes .

![image](https://user-images.githubusercontent.com/105942580/197604536-cf10dc9d-078b-48c6-a8c0-eddc27604736.png)

We increment the points value in this loop according to the counter value.And also created high_score value.

![image](https://user-images.githubusercontent.com/105942580/197604562-ed46160f-f98a-47b6-9cce-e4eb0b4d4400.png)


In every mouseClicked event , we need to change the points and counter variables . So we used .clear() method and add them again.

![image](https://user-images.githubusercontent.com/105942580/197604628-c0fd1123-9e9a-444a-84ca-99e7ff1d634c.png)

In the destroy_all(rectangle r) method, First, we check the IsDestroyable() value of the rectangle which is clicked. Then we try to find any affected rectangle by using if statements.

Also we need to write the clicked box and affected box on the bottom page , so we create hit# and box variables.

![image](https://user-images.githubusercontent.com/105942580/197604681-7e151920-79b2-4e21-a7e3-b0c72ee56ce3.png)

We need to write gained or lost points according to each click event. So we add this code at the end of each if blocks.

![image](https://user-images.githubusercontent.com/105942580/197604711-99b228c7-eca7-4f6e-8e16-bb4d227adf7a.png)

At the end of the GameBoard part, we need to write next level message if the current level is completed.By this code , we ensure that the level is completed or not:

![image](https://user-images.githubusercontent.com/105942580/197604767-2f8a0208-fba1-4d3b-9518-ef940612ef66.png)

Test cases :

When we run the game, the main menu arises. If we want to open level-2 without complating level-1, it gives a warning as in the fallowing figure. 

![image](https://user-images.githubusercontent.com/105942580/197604814-5a76c41e-2b12-418e-81d5-df609df7ff68.png)


When we click the button level-1, the level-1 stage arises. At the end of the level, the next level becomes available.

![image](https://user-images.githubusercontent.com/105942580/197604842-fe9fa619-d864-4122-b351-c3d7235f0cfe.png)


When the point is two digit and not shown properly, we can close the window and open again.

![image](https://user-images.githubusercontent.com/105942580/197604895-5888b4c7-c598-4a9b-a8c2-715fe9a40fae.png)


When we complete the level, a label arises in the bottom pane which says “The next level is available!” However, when we complete the level-5, it says congratulations. It is shown in the fallowing figure.

![image](https://user-images.githubusercontent.com/105942580/197604933-5f0f3a0f-5db9-46ef-941b-5d98d057bbdf.png)

                                                                  How to run the code ?

Firstly  download the project levels & main class files.

Then open the windows command prompt.

Change the current directory to the location of downloaded files. (cd ./..).

Type the javac Project.java to compile the code.

Then type java Project.

It will opens the menu. 

Congrats !! You can play the game ...


![Screenshot_10](https://user-images.githubusercontent.com/105942580/197605648-593c4555-c646-4dd0-aa06-7a5673a6ca6a.png)

