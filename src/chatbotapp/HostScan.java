/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatbotapp;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author student
 */
class HostScan extends Thread{
    static int scanport = 5555;
    String ipAddress;
    ArrayList<String> onlineUsersList;
    public HostScan(String string, ArrayList<String> onlineUsersList) {
        this.ipAddress = string;
        this.onlineUsersList = onlineUsersList;
    }
    @Override
    public void run(){
        try{
            Socket sock = new Socket(this.ipAddress, scanport);
            this.onlineUsersList.add(this.ipAddress);
            sock.close();
        }
        catch(IOException ex){
            //System.out.println(ex.getMessage());
        }
    }
}
