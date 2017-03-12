/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.mind.broker;

import java.net.SocketException;

/**
 *
 * @author pguan
 */
public class BrokerMain {
    public static void main(String[] args) throws SocketException, InterruptedException {
        Thread thread = new Thread(new UDPServer());
        thread.start();
        thread.join();
    }
    
}
