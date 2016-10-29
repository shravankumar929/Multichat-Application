/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatbotapp;

import java.io.IOException;
import java.io.InputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 *
 * @author student
 */
public class ipAddressLib {
    public static String getIpAddress(){
        
        String ip = "";
        try {
            Process child = Runtime.getRuntime().exec("ifconfig");
            StringTokenizer st = new StringTokenizer("");
            try {
                InputStream in = child.getInputStream();
                int c;
                StringBuilder sb = new StringBuilder();
                while ((c = in.read()) != -1) {
                    sb.append((char) c);
                }   String str = sb.toString();
                st = new StringTokenizer(str);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
            String myeth = st.nextToken();
            if (myeth.charAt(0) != 'e') {
                JOptionPane.showMessageDialog(null, "Not connected to Lan!");
                System.exit(0);
            }
            NetworkInterface net = NetworkInterface.getByName(myeth);
            Enumeration<InetAddress> inetAddress = net.getInetAddresses();
            //InetAddress current = inetAddress.nextElement();
            while (inetAddress.hasMoreElements()) {
                InetAddress current = inetAddress.nextElement();
                if (current instanceof Inet4Address && !current.isLoopbackAddress()) {
                    ip = current.toString();
                    break;
                }
            }
        }
        catch (SocketException ex) {
            System.out.println("error=" + ex.getMessage());
        } catch (IOException ex) {
            //Logger.getLogger(BroadCastUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        ip = ip.substring(1,ip.length());
        System.out.println(ip);
       return ip;
    }
}
