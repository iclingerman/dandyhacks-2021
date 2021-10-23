import java.io.IOException;
import java.net.*;

public class Driver {
    public static void main(String[] args) throws UnknownHostException {
        // InetAddress ip = InetAddress.getByName("127.0.0.1");
        Socket sock;
        String ip = "";
        int port = 0;
        boolean isServer = false;
        if (args[0].equals("server")) {
            isServer = true;
            ip = "127.0.0.1";
            port = Integer.parseInt(args[1]);
        } else {
            String[] temp = args[1].split(":");
            ip = temp[0];
            port = Integer.parseInt(temp[1]);
        }

        System.out.printf("ip: %s, port: %d\n", ip, port);

        try {
            sock = new Socket(ip, port);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}