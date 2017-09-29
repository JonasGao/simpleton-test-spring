package org.jonas.test.spring.conf.bean.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableConfigurationProperties(Prop.class)
public class TestApp implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(TestApp.class, args);
    }

    @Autowired
    private Data data;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(data.getName());
    }
}

@Component
class DaoConfigFactoryProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Prop prop = beanFactory.getBean(Prop.class);
        beanFactory.registerSingleton("data1", new Data() {{
            setName(prop.getName());
        }});
    }
}

@ConfigurationProperties
class Prop {

    private String name = "Spring";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Data {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
