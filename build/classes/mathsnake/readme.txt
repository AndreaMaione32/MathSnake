Environment.java is an interface in which are defined the costant values for the game.
MathSnake.java is a JFrame in which we'll insert the components of our applet.
SnakeBoard.java is a JPanel in which we'll insert all the objects of our game, snake, hurdle ...
In this file the movements of the snake are a consequence of the pressing of the left or right button and automatically
the leftDirection or rightDirection variables are set.
This variables are attributes of SnakeBoard class so when leftDirection is True then i update the value of x of each image.
If the value, that i would like to insert, is less than 0 then i set each x to 0 or is greater than JB_WIDTH - 10 i set each x to
JB_WIDTH -10.
Why JB_WIDTH -10 ? Because each image takes 10 pixels starting from x = 0.
