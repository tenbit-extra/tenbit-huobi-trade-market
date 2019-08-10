package cn.tenbit.huobi.common.event;

/**
 * @Author bangquan.qian
 * @Date 2019-08-10 12:02
 */
public class StringEvent implements Event<String> {

    private String data;

    private int type;

    public StringEvent(int type, String data) {
        this.type = type;
        this.data = data;
    }

    @Override
    public String getData() {
        return data;
    }

    @Override
    public int getType() {
        return type;
    }
}
