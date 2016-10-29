/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbotapp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author asif
 */
public class FileServer extends Thread{
    
    public static int port= 5656;
    public OnlineUsers onlineUsers;
    public ServerSocket serverSock;
    private SocketClient[] users;
    FileServer(){
        try {
            this.serverSock = new ServerSocket(port);
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void addOnlineUsers(OnlineUsers onlineUsers){
        this.onlineUsers = onlineUsers;
    }

    public void sendBroadCast(String fileName){
        ArrayList<String> onlineUsersList = this.onlineUsers.getOnlineHosts();
        for(String ipAddress: onlineUsersList){
            FileSendRequest fsr = new FileSendRequest(ipAddress,port,fileName);
            fsr.start();
        }
    }
    public void sendRequest(String ipAddress1,String fileName){
        FileSendRequest fsr = new FileSendRequest(ipAddress1,port,fileName);
        fsr.start();
    }
    public static void sendFileRequest(String ipAddress,String filePath){
        try{
            Socket s = new Socket(ipAddress,FileServer.port);
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            dout.writeUTF("FileTransmissionRequest::"+filePath);
            dout.close();
            s.close();
        }
        catch(IOException ex){
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public void run(){
        while(true){
            try {
                Socket client = serverSock.accept();
                FileServerHandleRequest inst = new FileServerHandleRequest(client,users);
                inst.start();
            }
            catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            
        }
    }   

    void addSocketClient(SocketClient[] users) {
        this.users = users;
    }
}
