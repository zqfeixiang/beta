package designPattern.strategyPattern;

import java.math.BigDecimal;

public class NYGCouponDiscount implements ICouponDiscount<Double> {
    /**
     * n元购购买
     * 1. ⽆论原价多少钱都固定⾦额购买
     */
    public BigDecimal discountAmount(Double couponInfo, BigDecimal skuPrice) {
        return new BigDecimal(couponInfo);
    }
}