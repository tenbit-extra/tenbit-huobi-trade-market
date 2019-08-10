package cn.tenbit.huobi.config;

import java.io.File;
import java.math.BigDecimal;

/**
 * @Author bangquan.qian
 * @Date 2019-08-09 20:52
 */
public interface ConfigDefine {

    String MAIL_CHARSET = "utf-8";

    String USER_DIR = System.getProperty("user.dir");

    String MAIL_OUT_PROPERTIES = USER_DIR + File.separator + "mail.properties";
    String CONFIG_OUT_PROPERTIES = USER_DIR + File.separator + "config.properties";

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

    String DB_KEY_HB_LAST_SELL_PRICE = "hb:c2c:price:last_sell:";
    String DB_KEY_HB_LAST_BUY_PRICE = "hb:c2c:price:last_buy:";

    String DB_KEY_HB_EMAIL_LAST_SEND_TIME = "hb:c2c:email:last_send_time:";

    int MIN_EMAIL_INTERVAL_TIME_MS = 5 * 60 * 1000;

    BigDecimal MIN_DELTA_PRICE = new BigDecimal("0.02");
}
