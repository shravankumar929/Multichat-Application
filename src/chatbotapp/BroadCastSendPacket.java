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
public class BroadCastSendPacket{
    public static void send(String ipAddress, int port, String data) {
        if(ipAddress.equals(ipAddressLib.getIpAddress())){
            return ;
        }
        try{
            Socket sock = new Socket(ipAddress,port);
            DataOutputStream out = new DataOutputStream(sock.getOutputStream());
            out.writeUTF(data);
        }
        catch(IOException ex){
            System.out.println(ex.getClass()+ex.getMessage());
        }
    }
}
