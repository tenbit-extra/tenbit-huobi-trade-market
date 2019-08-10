package cn.tenbit.huobi.config;

/**
 * @Author bangquan.qian
 * @Date 2019-08-09 20:52
 */
public interface ConfigDefine {

    String MAIL_CHARSET = "utf-8";

    String MAIL_PROPERTIES = "classpath:/mail.properties";
    String CONFIG_PROPERTIES = "classpath:/config.properties";

    String HUOBI_C2C_API_BUY = "huobi.c2c.api.buy";
    String HUOBI_C2C_API_SELL = "huobi.c2c.api.sell";

    String MAIL_SEND_SMTP_HOST = "mail.send.smtp.host";
    String MAIL_SEND_SMTP_PORT = "mail.send.smtp.port";
    String MAIL_SEND_SMTP_USERNAME = "mail.send.username";
    String MAIL_SEND_SMTP_PASSWORD = "mail.send.password";

    String MAIL_RECV_SMTP_USERNAME = "mail.recv.username";

    String DB_KEY_HB_SELL_PRICE = "hb:c2c:price:sell:";
    String DB_KEY_HB_BUY_PRICE = "hb:c2c:price:buy:";
}
