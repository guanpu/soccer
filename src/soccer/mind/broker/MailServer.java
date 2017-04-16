/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.mind.broker;

import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pguan
 */
public class MailServer implements Runnable {
    
    private final ConcurrentLinkedQueue<String> queue;
    
    private final SimpleMailSender sender;
    
    public MailServer(ConcurrentLinkedQueue<String> queue) throws IOException {
        this.queue = queue;
        this.sender = new SimpleMailSender();
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                if (queue.isEmpty()) {
                    TimeUnit.MINUTES.sleep(1);                    
                } else {
                    String toBeSend = queue.poll();
                    if (null == toBeSend || toBeSend.isEmpty()) {
                        continue;
                    } else {
                        sender.sendToMyself("From Soccer App", toBeSend);
                    }
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(MailServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
