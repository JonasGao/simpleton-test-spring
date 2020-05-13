package org.jonas.test.spring.validation;

import lombok.Data;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
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
public class Example1 implements ApplicationRunner {

    private final ValidatorFactory validatorFactory;

    public Example1(ValidatorFactory validatorFactory) {
        this.validatorFactory = validatorFactory;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        C c = new C();
        c.setMsg("");
        List<C> list = new ArrayList<>();
        list.add(c);

        A a = new A();
        a.setList(list);

        B b = new B();
        b.setList(list);

        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<A>> validate1 = validator.validate(a);
        System.out.println("1");
        System.out.println(validate1);

        Set<ConstraintViolation<B>> validate2 = validator.validate(b);
        System.out.println("2");
        System.out.println(validate2);
    }

    @Data
    public static class A {
        @NotEmpty
        @Valid
        private List<C> list;
    }

    @Data
    public static class B {
        @NotEmpty
        private List<C> list;
    }

    @Data
    public static class C {
        @NotBlank
        private String msg;
    }
}
