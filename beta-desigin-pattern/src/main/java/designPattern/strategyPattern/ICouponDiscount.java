package designPattern.strategyPattern;

import java.math.BigDecimal;

public interface ICouponDiscount<T> {
    /**
     * 优惠券⾦额计算
     * @param couponInfo 券折扣信息；直减、满减、折扣、N元购
     * @param skuPrice 商品⾦额
     * @return 优惠后⾦额
     */
    BigDecimal discountAmount(T couponInfo, BigDecimal skuPrice);
}