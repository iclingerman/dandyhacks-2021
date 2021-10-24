# Socket Battleship

## DandyHacks 2021 Submission (Video Games Track)

Socket Battleship was created for DandyHacks 2021 as part of the Video Games Track. All the code for this project was designed and written in the span of 36 hours. Created by:
[Ian Clingerman](https://www.linkedin.com/in/ian-clingerman/), 
[Sophie Stahl](https://www.linkedin.com/in/sophiestahl/). 

## About the Game

This is a text based battleship game that you can play against you friends remotely! Using TCP sockets, one player is able to host a game and another player is able to connect to them to play. The game runs through the console for both printing the boards and having players select their moves. 

Both the game and socket programmming code are written in Java. 

## Symbols Dictionary

On our board, the game is represented by symbols:
~ = water
s = ship
o = miss
x = hit

## How to Input Your Own Ships
When it comes to placing the ships as you wish, here is how to input:

letternumber orientation

For example: a1 v
or
For example: h5 h

The head of the ship is the topmost point when the ship is vertical and the leftmost point when the ship is horizontal.

The code assumes that you inputed in the correct format, but does check for bounding errors.

Choosing a move follows the same format minus the orientation character. 

## Usage

We have provided a Makefile to compile the Java code. Use 'make' to compile the code and 'make run' to run the Driver program. 
The compile command is 'javac -d bin *.java' and the run command is 'java -cp bin Driver'

The entirety of this project was designed and written during DandyHacks, a 36-hour hackathon hosted at the University of Rochester, as part of the Games track. 
