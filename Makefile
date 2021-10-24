OBJS = *.java
CC = javac
FLAGS = -d ./bin

all : $(OBJS)
	$(CC) $(FLAGS) $(OBJS)

run:
	java -cp ./bin Driver

clean:
	rm bin/*.class