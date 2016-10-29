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
 * @author student
 */
public class TransmissionSendPacket {
    public static void SendData(String ipAddress, int port, String message){
        try{
                Socket sock = new Socket(ipAddress,port);
                DataOutputStream out;
                out = new DataOutputStream(sock.getOutputStream());
                out.close();
                sock.close();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
}
