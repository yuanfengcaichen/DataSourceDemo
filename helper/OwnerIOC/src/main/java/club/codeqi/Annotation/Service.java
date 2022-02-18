package club.codeqi.Annotation;

import java.lang.annotation.*;

/**
 * @author codeqi
 * @version 1.0
 * @date 2021/2/7 0007 14:20
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Component
public @interface Service {
    String value() default "";
}
