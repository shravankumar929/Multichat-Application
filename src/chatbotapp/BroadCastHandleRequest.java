/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatbotapp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author student
 */
class BroadCastHandleRequest {

    public static void HandleRequest(Socket clientSocket,BroadCastUI broadCastUI) {
        try{
            String message;
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                message = in.readUTF();
                in.close();
            broadCastUI.setText(message);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    
}
