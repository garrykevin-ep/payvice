package com.garrykevin.payvice.request.expense.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TotalAmountEqualsPayerAmountValidator.class )
public @interface TotalAmountEqualsPayerAmount {

  String message() default "payer amount does not match total amount";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
