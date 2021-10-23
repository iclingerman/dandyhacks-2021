import java.io.IOException;
import java.net.*;

public class Driver {
    public static void main(String[] args) throws UnknownHostException {
        String ip = "";
        int port = 0;
        String name = args[1];
        if (args[0].equals("server")) {
            port = Integer.parseInt(args[2]);
            Server s = new Server(port, name);
        } else {
            String[] temp = args[2].split(":");
            ip = temp[0];
            port = Integer.parseInt(temp[1]);
            Client c = new Client(ip, port, name);
        }
    }
}