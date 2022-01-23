package com.dhy_zk.financialSystem.config.anno;



import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author 大忽悠
 * @create 2022/1/22 20:03
 */
public class NoSuperValidator implements ConstraintValidator<NotSuper, Integer> {
    @Override
    public void initialize(NotSuper notSuper) {

    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return integer!=1;
    }
}
