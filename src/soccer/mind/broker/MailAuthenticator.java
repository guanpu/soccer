package soccer.mind.broker;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Javax Mail authenticator.
 * @author pguan
 */
public class MailAuthenticator extends Authenticator {
    private String username;
    private String password;

    public MailAuthenticator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(username,password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
