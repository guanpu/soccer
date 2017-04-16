/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.mind.broker;

import java.io.IOException;
import java.net.SocketException;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author pguan
 */
public class BrokerMain {
    public static void main(String[] args) throws SocketException, InterruptedException, IOException {
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

        Thread udpThread = new Thread(new UDPServer(queue));
        Thread mailThread = new Thread(new MailServer(queue));
        udpThread.start();
        mailThread.start();
        udpThread.join();
        mailThread.join();
    }
    
}
