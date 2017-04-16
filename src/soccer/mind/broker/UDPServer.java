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
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author pguan
 */
public class UDPServer implements Runnable{
    private final ConcurrentLinkedQueue<String> queue;

    public UDPServer(ConcurrentLinkedQueue<String> queue) throws SocketException {
        this.queue = queue;
        this.s = new DatagramSocket(20000);
        this.flag = true;
    }
    
    private boolean flag;
    private DatagramSocket s;
    public void run() {
        try {
            byte[] buf = new byte[1024];
            DatagramPacket p = new DatagramPacket(buf, 1024);
            while (flag) {
                s.receive(p);
                if(p.getLength()>0) {
                    String data = new String(p.getData());
                    queue.add(data);
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

}
