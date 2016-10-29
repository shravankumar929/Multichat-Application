/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatbotapp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author student
 */
public class TransmissionHandleRequest {
    public static void HandleRequest(SocketClient[] user, Socket sock, TransmissionArea transmissionArea, FileServer fileServer){
        try {
                DataInputStream in  = new DataInputStream(sock.getInputStream());
                String message = in.readUTF();
                String userip = sock.getInetAddress().getHostAddress();
                int number = Integer.parseInt(userip.substring(userip.lastIndexOf(".")+1));
                if(user[number]==null){
                    user[number] = new SocketClient();
                    user[number].addIpAddress(userip);
                    user[number].setTransmissionArea(transmissionArea);
                    user[number].setVisible(true);
                    user[number].addFileServer(fileServer);
                    user[number].setIp();
                }
                in.close();
                user[number].setOutputBuffer(message);
        }
        catch (IOException ex) {
                System.out.println(ex.getMessage());
        }        
    }
}
