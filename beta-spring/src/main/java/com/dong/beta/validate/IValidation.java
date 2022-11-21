package com.dong.beta.validate;

import com.dong.beta.entity.Book;

/**
 * @author dzq
 * @date 11/21/22 13:41
 */
public interface IValidation extends IValidateOrder{
    boolean shouldSkip();
    boolean validate(Book book);
}
