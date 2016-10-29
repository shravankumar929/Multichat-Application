/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatbotapp;

import java.util.ArrayList;

/**
 *
 * @author student
 */
public class OnlineUsers {
    public BroadCastUI broadCastUI;
    public  ArrayList<String> onlineUsersList;
    OnlineUsers() {
        this.onlineUsersList= new ArrayList<String>();
    }
    public void init(){
        this.onlineUsersList.clear();
        String ipAddress = ipAddressLib.getIpAddress();
        ipAddress = ipAddress.substring(0,ipAddress.lastIndexOf("."))+".";
        for(int i=1;i<=255;++i){
            HostScan instance = new HostScan(ipAddress+i,this.onlineUsersList);
            instance.start();
        }
        
    }
    public ArrayList<String> getOnlineHosts(){
        return this.onlineUsersList;
    }
}
