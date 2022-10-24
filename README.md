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


