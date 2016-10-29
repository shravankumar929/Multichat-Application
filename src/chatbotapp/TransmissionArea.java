/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatbotapp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author student
 */
public class TransmissionArea extends Thread{
    public static int port = 6666;
    public ServerSocket sock;
    public SocketClient[] client;
    private FileServer fileServer;
    TransmissionArea(){
        try {
            this.sock = new ServerSocket(port);
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void SendData(String ipAddress,String data){
            TransmissionSendPacket.SendData(ipAddress,port,data);
    }

    @Override
    public void run(){
            while(true){
                try{
                    Socket clientSocket = sock.accept();
                    TransmissionHandleRequest.HandleRequest(client,clientSocket,this,this.fileServer);
                }
                catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }            
    }
    void addFileServer(FileServer fileServer){
        this.fileServer = fileServer;
    }
    void addSocketClient(SocketClient[] users) {
        this.client = users;
    }

    
}
