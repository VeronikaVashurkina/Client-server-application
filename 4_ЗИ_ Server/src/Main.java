
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, UnknownHostException {
        System.out.println(InetAddress.getLocalHost());
        String salt = new Functions().getDES();
        System.out.println("Salt: " + salt);
        System.out.println("Server.Create password: ");
        Scanner scanner = new Scanner(System.in);
        String password = scanner.next();
        System.out.println("Salt standart: " + salt + password);
        String hash = new Functions().encryptPassword(salt + password);
        System.out.println("Hash: " + hash);

        for (int i = 0; i < 200; i++) System.out.println("");


        System.out.println("Server save: Hash-" + hash + "  and Salt " + salt);

        try {ServerSocket server = new ServerSocket(1030);
            Socket client = server.accept();
            System.out.print("Connection accepted.");

            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            DataInputStream in = new DataInputStream(client.getInputStream());


            String clientPassword = in.readUTF();
            System.out.println("READ from client password - " + clientPassword);
            System.out.println("Salt: " + salt);
            System.out.println("Salt standart: " + salt + clientPassword);
            String hashClient = new Functions().encryptPassword(salt + clientPassword);
            System.out.println("Hash: " + hashClient);

            if(hash.equals(hashClient))System.out.println("Password is correct.");
            else System.out.println("Password isnt correct.");

            out.flush();



            System.out.println("Client disconnected");
            System.out.println("Closing connections & channels.");

            in.close();
            out.close();

            client.close();


            System.out.println("Closing connections & channels - DONE.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

