package cn.tenbit.huobi.common.mail;

import cn.tenbit.huobi.config.ConfigDefine;
import cn.tenbit.huobi.config.Configs;

import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * @Author bangquan.qian
 * @Date 2019-08-10 16:37
 */
public class Mails {

    public static void send(String content) throws Exception {
        String send = Configs.getProperty(ConfigDefine.MAIL_SEND_SMTP_USERNAME);
        String recv = Configs.getProperty(ConfigDefine.MAIL_RECV_SMTP_USERNAME);

        MimeMessage message = new MimeMessage(MailFactory.getSession());

        message.setFrom(new InternetAddress(send));
        message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(recv));
        message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(send));

        message.setSubject("Tenbit Huobi Notice", ConfigDefine.MAIL_CHARSET);
        message.setText(content, ConfigDefine.MAIL_CHARSET, "plain");
        message.setSentDate(new Date());

        message.saveChanges();
        Transport.send(message);
    }
}
