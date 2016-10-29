/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatbotapp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author student
 */
public class BroadCastArea extends Thread{
    public static int port = 5555;
    public ServerSocket sock;
    public BroadCastUI broadCastUI;
    public OnlineUsers onlineUsers;
    
    public Socket clientSocket;
    public void setBroadCastUI(BroadCastUI broadCastUI){
        this.broadCastUI = broadCastUI;
    }
    public void setScanner(OnlineUsers onlineUsers){
        this.onlineUsers =  onlineUsers;
        this.onlineUsers.init();
    }
    public void SendData(String data){
        ArrayList<String> onlineUsersList = this.onlineUsers.getOnlineHosts();
        for(String ipAddress: onlineUsersList){
            BroadCastSendPacket.send(ipAddress,port,data);
        }
    }
    @Override
    public void run(){
        try {
            this.sock = new ServerSocket(port);
            while(true){
                try{
                    clientSocket = sock.accept();
                    BroadCastHandleRequest.HandleRequest(clientSocket,broadCastUI);
                    clientSocket.close();
                }
                catch (IOException ex) {
                }
            }
        }
        catch (IOException ex) {
           // Logger.getLogger(BroadCastArea.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
