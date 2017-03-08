/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.mind.broker;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author pguan
 */
public class UDPServer {

    public UDPServer() throws SocketException {
        this.s = new DatagramSocket(20000);
        this.flag = true;
    }
    
    private boolean flag;
    private DatagramSocket s;
    public void listen() {
        try {
            byte[] buf = new byte[256];
            DatagramPacket p = new DatagramPacket(buf, 256);
            while (flag) {
                s.receive(p);
                if(p.getLength()>0) {
                    String data = new String(p.getData());
                    System.out.println(data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            s.close();
        }
    }
    public void stop() {
        this.flag = false;
    }
    
    public static void main(String[] args) throws SocketException {
        UDPServer server = new UDPServer();
        server.listen();
    }
    
}
