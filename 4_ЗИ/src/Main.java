import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        try {
            Socket socket = new Socket("172.23.0.1", 1030);
            DataOutputStream oos = new DataOutputStream(socket.getOutputStream());


            System.out.println("Client connected to socket.");


            System.out.println("Client. Enter password: ");
            Scanner scanner = new Scanner(System.in);
            String clientCommand = scanner.next();

            oos.writeUTF(clientCommand);

            oos.flush();
            System.out.println("Clien sent message " + clientCommand + " to server.");


            System.out.println("Closing connections & channels on clentSide - DONE.");
            oos.close();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
