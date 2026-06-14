package com.uninter.gerenciadorunidade.audit;

import com.uninter.gerenciadorunidade.core.domain.AuditAction;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Auditable {

    AuditAction action();

    String entity() default "";

    int entityIdArgIndex() default 0;

}