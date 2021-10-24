import java.util.Scanner;
import java.net.*;

public class Driver {
    public static void main(String[] args) throws UnknownHostException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to BattleShip by Ian Clingerman and Sophie Stahl");
        System.out.print("Please enter your name: ");
        String name = scan.nextLine();
        System.out.println("Welcome " + name);
        boolean isHost = false;
        while (true) {
            System.out.print("Would you like to host or connect to a game? (Enter 'host' or 'connect'): ");
            String type = scan.nextLine(); 
            if (type.equals("host")) {
                isHost = true;
                break;
            } else if (type.equals("connect")) {
                break;
            } else {
                System.out.println("Error - please enter a valid option");
            }
        }

        if (isHost) {
            Server s = new Server(name);
        } else {
            System.out.print("Please enter the information for the game you wish to connect to (ip:port): ");
            String[] temp = scan.nextLine().split(":");
            String ip = temp[0];
            int port = Integer.parseInt(temp[1]);
            Client c = new Client(ip, port, name);
        }
        

        scan.close();
    }
}