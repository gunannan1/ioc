package com.spdb.Util.Valiator.NotNull;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotNull {
}
