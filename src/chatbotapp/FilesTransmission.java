package chatbotapp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author asif
 */
class FilesTransmission {
    public String ipAddress;
    public int port;
    public String filePath;
    public DataInputStream din;
    public long fileLength;
    public SocketClient user;
    
    FilesTransmission(String ipAddress, int port, String filePath, SocketClient user) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.filePath = filePath;
        this.user = user;
    }

    FilesTransmission(String ipAddress, DataInputStream din, String filePath, long fileLength, SocketClient user) {
        this.ipAddress = ipAddress;
        this.din = din;
        this.filePath = filePath;
        this.fileLength = fileLength;
        this.user = user;
    }

    public void startSending() {
        // file Send format
        // FileTransmissionResponse::filePath::fileSize
        // filedata
        System.out.println("HEWR"+this.filePath);
        //filePath = filePath.replaceAll("/home/student","");
        try{
            File fp = new File(filePath);
            System.out.println(filePath);
            this.sendFile(fp);
            System.out.println("sendFile function complete");
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    public void sendFile( File fp){
        if(fp.isDirectory()){
            try{
                System.out.println(fp.getAbsoluteFile());
                File fileNames[] = fp.listFiles();
                for(int i=0;i<fileNames.length;++i){
                    sendFile(fileNames[i]);
                }
            }
            catch(Exception ex){
             ex.printStackTrace();
             System.out.println(ex.getMessage());
            }
        }
        else if(fp.isFile()){
            try{
                System.out.println("Socket Created");
                Socket clientSocket = new Socket(this.ipAddress,this.port);
                DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());
                String inst = "FileTransmissionResponse::"+fp.getPath()+"::"+fp.length();
                System.out.println("writing "+inst);
                dout.writeUTF(inst);
                byte buffer[]  = new byte[1024];
                int readCount;
                int totalCount = 0;
                FileInputStream fin = new FileInputStream(fp);
                System.out.println("initiating read&write");
                while((readCount = fin.read(buffer,0,buffer.length))>0){
                    totalCount += readCount;
                    dout.write(buffer,0,readCount);
                }
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
                 ex.printStackTrace();
            }
            
        }
    }

    public void startReceiving() {
        System.out.println("Receving started");
        int rowPos = 0;
        for(int i=0;i< this.user.rowCount;++i){
            String tmp = (String) this.user.modal.getValueAt(i, 2);
            String ip = (String) this.user.modal.getValueAt(i, 0);
            if(this.ipAddress.equals(ip) && tmp.equals(this.filePath)){
                rowPos = i;
                break;
            }
        }
        System.out.println("Starting Transmission");
        try{
            
            File fp = new File(this.filePath);
            if(fp.exists()==false) fp.createNewFile();
            FileOutputStream fout = new FileOutputStream(fp);
            int readCount;
            int totalCount = 0;
            byte buffer[] = new byte[1024];
            while((readCount=din.read(buffer,0,buffer.length))>0){
                totalCount += readCount;
                this.user.modal.setValueAt(totalCount, rowPos, 4);
                fout.write(buffer, 0, readCount);
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        System.out.println("Completed");
    }
    
}

