package chatbotapp;

import java.io.IOException;



public class MainUI {

    public static void main(String args[]) {
        
        try {
            SocketClient[] users = new SocketClient[256];
            for (int i = 1; i < users.length; ++i) {
                users[i] = null;
            }
            
            BroadCastUI broadCastUI = new BroadCastUI();
            OnlineUsers onlineUsers = new OnlineUsers();
            
            broadCastUI.scanner(onlineUsers); // will send onlineUsers object to broadCastui
            broadCastUI.addSocketClient(users); // will send userslist to broadCastui
            broadCastUI.scannerInit(); // will add the users to listmodel
            
            TransmissionArea transmissionArea = new TransmissionArea();            
            transmissionArea.addSocketClient(users);
            BroadCastArea broadCastArea = new BroadCastArea();
            broadCastArea.setBroadCastUI(broadCastUI);
            broadCastArea.setScanner(onlineUsers);
            broadCastUI.addTransmissionArea(transmissionArea);
            broadCastUI.addBroadCastArea(broadCastArea);
            
            FileServer fileServer = new FileServer();
            fileServer.addOnlineUsers(onlineUsers);
            fileServer.addSocketClient(users);
            transmissionArea.addFileServer(fileServer);
            broadCastUI.addFileServer(fileServer);

            transmissionArea.start();
            broadCastArea.start();
            fileServer.start();
            
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
