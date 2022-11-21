package com.dong.beta.validate;

import com.dong.beta.entity.Book;

/**
 * @author dzq
 * @date 11/21/22 13:46
 */
public class AttributeValidation implements IValidation{
    @Override
    public Integer getOrder() {
        return 1;
    }

    @Override
    public boolean shouldSkip() {
        return false;
    }

    @Override
    public boolean validate(Book book) {
        return false;
    }
}
