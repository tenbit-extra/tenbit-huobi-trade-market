package cn.tenbit.huobi.common.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * @Author bangquan.qian
 * @Date 2019-08-10 16:51
 */
public class MailAuthenticator extends Authenticator {

    private String username;

    private String password;

    public MailAuthenticator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
}
