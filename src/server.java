import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class server {
    Socket socket;
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9090);
        int counter = 0;
        Socket socket=serverSocket.accept();;
        DataOutputStream dataOutputStream= new DataOutputStream(socket.getOutputStream());
        DataInputStream dataInputStream= new DataInputStream(socket.getInputStream());
        Scanner scanner =new Scanner(System.in);

        if(counter == 0){
            /***********************************************************/
            /**gerefan eltelaat az client va add kardan**/
            DataInputStream is = new DataInputStream(socket.getInputStream());
            String name = is.readUTF();
            //System.out.println(name);
            String familyname = is.readUTF();
            String email = is.readUTF();
            String user = is.readUTF();
            String pass = is.readUTF();
            counter ++;
            etelaa etelaa = new etelaa(name , familyname , email , user , pass);

            add add = new add();
            add.addPerson(etelaa);
            /*****************************************************************/
        }

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true){
                    try {
//                        dataOutputStream.writeUTF(scan.nextLine());
                        System.out.println(dataInputStream.readUTF());
                        Addpayam addpayam = new Addpayam();
                        addpayam.addp(dataInputStream);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true){
                    try {
//                        dataOutputStream.writeUTF(scan.nextLine());
                        dataOutputStream.writeUTF(scanner.nextLine());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
//            Socket socket =serverSocket.accept();

    }
}
