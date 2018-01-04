package com.elong.nbopen.api.model.elong;

public enum EnumOrderShowStatus {

    担保失败(1),
    等待担保(2),
    等待确认(4),
    等待支付(8),
    等待核实入住(16),
    酒店拒绝订单(32),
    未入住(64),
    已经离店(128),
    已经取消(256),
    已经确认(512),
    已经入住(1024),
    正在担保(2048),
    正在支付(4096),
    支付失败(8192);
    private final long value;

    EnumOrderShowStatus(long v) {
        value = v;
    }

    public long value() {
        return value;
    }

    public static EnumOrderShowStatus fromValue(long v) {
        for (EnumOrderShowStatus c: EnumOrderShowStatus.values()) {
            if (c.value == v) {
                return c;
            }
        }
        return null;
    }
}
