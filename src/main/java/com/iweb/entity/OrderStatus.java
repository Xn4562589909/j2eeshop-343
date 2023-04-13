package com.iweb.entity;


/**
 * @author Guan
 * @date 2023/3/31 15:37
 */
public enum OrderStatus {
    /**
     * waitPay代表待支付 waitDelivery代表待发货 waitConfirm代表待收货
     * waitReview代表待评价 finish代表已完成 delete代表已删除
     */
    WAIT_PAY("waitPay","待支付"),
    WAIT_CONFIRM("waitConfirm","待收货"),
    WAIT_DELIVERY("waitDelivery","待发货"),
    WAIT_REVIEW("waitReview","待评价"),
    FINISH("finish","已完成"),
    DELETE("delete","已删除");

    private OrderStatus(String code,String name){
        this.code = code;
        this.name = name;
    }

    /**
     * 数据库中记录订单状态的代码
     */
    private String code;
    /**
     * 状态名称
     */
    private String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
