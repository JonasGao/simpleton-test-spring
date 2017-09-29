package org.jonas.test.spring.conf.bean.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableConfigurationProperties(Prop1.class)
@Import({Register.class})
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
        Prop2 prop = beanFactory.getBean(Prop2.class);
        beanFactory.registerSingleton("data1", new Data() {{
            setName(prop.getName());
        }});
    }
}

@Component
class Register implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        System.out.println("???");
    }
}

@Configuration
class Conf {
    @Bean
    public Prop2 prop2(Prop1 prop1) {
        return new Prop2() {
            @Override
            String getName() {
                return "Hello " + prop1.getName();
            }
        };
    }
}

@ConfigurationProperties
class Prop1 {

    private String name = "Spring";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

abstract class Prop2 {
    abstract String getName();
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
