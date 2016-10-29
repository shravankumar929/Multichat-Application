/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbotapp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author asif
 */
class FileSendRequest extends Thread{
    public String ipAddress;
    public String fileName;
    public int port;
    FileSendRequest(String ipAddress, int port,String fileNames) {
        this.ipAddress = ipAddress;
        this.fileName = fileNames;
        this.port = port;
    }
    @Override
    public void run(){
        try{
            String sendText = "File Transmission Request:  "+fileName;
            Socket ssock = new Socket(this.ipAddress,6666);
            DataOutputStream ddout = new DataOutputStream(ssock.getOutputStream());
            ddout.writeUTF(sendText);
            ddout.close();
            ssock.close();
            Socket sock = new Socket(this.ipAddress, this.port); 
            DataOutputStream dout = new DataOutputStream(sock.getOutputStream());
            dout.writeUTF("File:: "+this.fileName);
            dout.close();
            sock.close();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    
}
