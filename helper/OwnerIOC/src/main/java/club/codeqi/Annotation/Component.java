package club.codeqi.Annotation;

import java.lang.annotation.*;

/**
 * @author codeqi
 * @version 1.0
 * @date 2021/2/7 0007 14:04
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Component {
    String value() default "";
}
