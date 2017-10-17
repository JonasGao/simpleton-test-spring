package org.jonas.test.spring.customcomponent;

import org.springframework.beans.factory.BeanNameAware;

@HsJob("test-my-hs-job")
public class HsJobNameTest implements BeanNameAware {
    @Override
    public void setBeanName(String name) {
        System.out.println(name);
    }
}
