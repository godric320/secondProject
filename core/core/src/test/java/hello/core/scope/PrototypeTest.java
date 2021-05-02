package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypenBean.class);

        System.out.println("find prototypeBean1");
        PrototypenBean prototypenBean1 = ac.getBean(PrototypenBean.class);
        System.out.println("find prototypeBean2");
        PrototypenBean prototypenBean2 = ac.getBean(PrototypenBean.class);

        System.out.println("prototypenBean1 = " + prototypenBean1);
        System.out.println("prototypenBean2 = " + prototypenBean2);
        assertThat(prototypenBean1).isNotSameAs(prototypenBean2);
        prototypenBean1.destroy();
        prototypenBean2.destroy();
        ac.close();
    }

    @Scope("prototype")
    static class PrototypenBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}