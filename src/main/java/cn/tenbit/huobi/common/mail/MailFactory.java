package cn.tenbit.huobi.common.mail;

import cn.tenbit.huobi.config.ConfigDefine;
import cn.tenbit.huobi.config.Configs;
import lombok.extern.slf4j.Slf4j;

import javax.mail.Authenticator;
import javax.mail.Session;
import java.util.Properties;

/**
 * @Author bangquan.qian
 * @Date 2019-08-10 16:37
 */
@Slf4j
public class MailFactory {

    public static Session getSession() {
        String host = Configs.getProperty(ConfigDefine.MAIL_SEND_SMTP_HOST);
        String port = Configs.getProperty(ConfigDefine.MAIL_SEND_SMTP_PORT);
        String user = Configs.getProperty(ConfigDefine.MAIL_SEND_SMTP_USERNAME);
        String pass = Configs.getProperty(ConfigDefine.MAIL_SEND_SMTP_PASSWORD);

        Properties props = new Properties();
        props.putAll(System.getProperties());
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", host);
        props.setProperty("mail.smtp.port", port);
        props.setProperty("mail.smtp.user", user);
        props.setProperty("mail.smtp.auth", "true");
        // props.setProperty("mail.smtp.ssl.enable", "true");
        // props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.starttls.enable", "true");

        Authenticator auth = new MailAuthenticator(user, pass);

        Session session = Session.getInstance(props, auth);
        if (log.isDebugEnabled()) {
            session.setDebug(true);
        }
        return session;
    }
}
