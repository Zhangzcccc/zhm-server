package com.ic.agile.common.validator;

import com.ic.agile.common.exception.RenException;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.MessageSourceResourceBundleLocator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Locale;
import java.util.Set;

/**
 * hibernate-validator校验工具类
 * 参考文档：http://docs.jboss.org/hibernate/validator/6.0/reference/en-US/html_single/
 *
 * @author zhangzc
 * @since 1.0.0
 */
public class ValidatorUtils {

    private static ResourceBundleMessageSource getMessageSource() {
        ResourceBundleMessageSource bundleMessageSource = new ResourceBundleMessageSource();
        bundleMessageSource.setDefaultEncoding("UTF-8");
        bundleMessageSource.setBasenames("i18n/validation");
        return bundleMessageSource;
    }

    /**
     * 校验对象
     * @param object        待校验对象
     * @param groups        待校验的组
     */
    public static void validateEntity(Object object, Class<?>... groups)
            throws RenException {
        Locale.setDefault(LocaleContextHolder.getLocale());
        Validator validator = Validation.byDefaultProvider().configure().messageInterpolator(
                new ResourceBundleMessageInterpolator(new MessageSourceResourceBundleLocator(getMessageSource())))
                .buildValidatorFactory().getValidator();

        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
        	ConstraintViolation<Object> constraint = constraintViolations.iterator().next();
            throw new RenException(constraint.getMessage());
        }
    }
}