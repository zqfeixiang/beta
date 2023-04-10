package designPattern.strategyPattern;

import java.math.BigDecimal;

/**
 * 策略控制类 主要是外部可以传递不同的策略实现，在通过统⼀的⽅法执⾏优惠策略计算。
 * @param <T>
 */
public class Context<T> {
    private ICouponDiscount<T> couponDiscount;
    public Context(ICouponDiscount<T> couponDiscount) {
        this.couponDiscount = couponDiscount;
    }
    public BigDecimal discountAmount(T couponInfo, BigDecimal skuPrice) {
        return couponDiscount.discountAmount(couponInfo, skuPrice);
    }
}