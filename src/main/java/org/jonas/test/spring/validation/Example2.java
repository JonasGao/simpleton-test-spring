package org.jonas.test.spring.validation;

import lombok.Data;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author JonasGao
 * @since 2020/5/13
 */
@Component
public class Example2 implements ApplicationRunner {

    private final ValidatorFactory validatorFactory;

    public Example2(ValidatorFactory validatorFactory) {
        this.validatorFactory = validatorFactory;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<String> bList = new ArrayList<>();
        bList.add("");

        D d = new D();
        d.setList(bList);

        E e = new E();
        e.setList(bList);

        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<D>> validate1 = validator.validate(d);
        System.out.println("3");
        System.out.println(validate1);

        try {
            Set<ConstraintViolation<E>> validate2 = validator.validate(e);
            System.out.println("4");
            System.out.println(validate2);
        } catch (Exception ignored) {
            System.out.println("4 fail");
        }
    }

    @Data
    public static class D {
        @NotEmpty
        private List<@NotBlank String> list;
    }

    @Data
    public static class E {
        @NotEmpty
        @NotBlank
        private List< String> list;
    }

    @Data
    public static class F {
        @NotEmpty
        private List<String> list;
    }
}
