package QualifierTestGenerators;
import java.lang.annotation.*;
import org.springframework.beans.factory.annotation.*;
@Target({ElementType.FIELD,ElementType.PARAMETER,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface LineBoundary {
}
