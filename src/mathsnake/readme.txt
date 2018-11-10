Environment.java is a interface in which the costant for the game will be inserted.
MathSnake.java is a Jframe in which we'll insert the component of our applet.
SnakeBoard.java is a jPanel in which we'll insert each objects of our game , snake , hurdle ...
In this file the moving of snake born when you press the buttom left or right and automaticaly it set the variabels 
leftDirection or rightDirection. This variables are attributes of SnakeBorad class so when leftDirection is True then 
i update the value of x of each image. If the value ,that i would like to insert , is less than 0 then i set each x to 0
or is greater than JB_WIDTH - 10 i set each x to JB_WIDTH -10. Why JB_WIDTH -10? Beacause each image takes 10 pixels starting 
from  x = 0
