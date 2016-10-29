/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbotapp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 *
 * @author asif
 */
public class FileServerHandleRequest extends Thread{
    Socket clientSocket;
    SocketClient users[];
    static int port = 5656;
    FileServerHandleRequest(Socket clientSock, SocketClient users[]){
        this.users = users;
        this.clientSocket = clientSock;
    }
    @Override
    public void run(){
        try{
             String ipAddress = this.clientSocket.getInetAddress().getHostAddress().trim();
             int offset = Integer.parseInt(ipAddress.substring(ipAddress.lastIndexOf(".")+1));
             SocketClient user = users[offset];
             DataInputStream din = new DataInputStream(this.clientSocket.getInputStream());
             String message = din.readUTF();
             System.out.println(message);
             if(message.startsWith("File::")){ // add to content pane
                 din.close();
                 this.clientSocket.close();
                 StringTokenizer st = new StringTokenizer(message,"::");
                 String filePath = st.nextToken();
                 filePath = st.nextToken().trim();
                 String ownerIp = ipAddressLib.getIpAddress();
                 while(user==null){
                   // System.out.println("User is null");
                    user = users[offset];
                 }
                 user.addRow(ownerIp, ipAddress, filePath, 1024);
             }
             else if(message.startsWith("FileTransmissionRequest")){ // send files to user
                 System.out.println("Transmission Request");
                 din.close();
                 this.clientSocket.close();
                 StringTokenizer st = new StringTokenizer(message,"::");
                 String filePath = st.nextToken();
                 filePath = st.nextToken().trim();
                 System.out.println("requested"+filePath);
                 FilesTransmission inst = new FilesTransmission(ipAddress,port,filePath,user);
                 System.out.println("init completed sending file");
                 inst.startSending();
                 System.out.println("Completed");
             }
             else if(message.startsWith("FileTransmissionResponse")){ // receive files from user
                 StringTokenizer st = new StringTokenizer(message,"::");
                 String filePath = st.nextToken();
                 filePath = st.nextToken().trim();
                 long fileLength = Long.parseLong(st.nextToken().trim());
                 FilesTransmission inst = new FilesTransmission(ipAddress,din,filePath, fileLength,user);
                 inst.startReceiving();
                 din.close();
                 this.clientSocket.close();
             }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }    
    
}
