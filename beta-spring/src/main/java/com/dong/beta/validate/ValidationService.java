package com.dong.beta.validate;

import com.dong.beta.entity.Book;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 * @author dzq
 * @date 11/21/22 13:38
 */
@Service
public class ValidationService {

    private final List<IValidation> validationChain;

    public ValidationService(List<IValidation> validationChain) {
        validationChain.sort(Comparator.comparingInt(IValidateOrder::getOrder));
        this.validationChain = validationChain;
    }

    public boolean validate(Book book) {

        validationChain.forEach(e -> {
            if (!e.shouldSkip()) {
                e.validate(book);
            }
        });
        return true;
    }
}
