package speltesting;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpelClient {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext factory = new ClassPathXmlApplicationContext("application-spel.xml");

        SimpleBeanA beanA = factory.getBean(SimpleBeanA.class);
        SimpleBeanB beanB = factory.getBean(SimpleBeanB.class);

        System.out.println(beanA.getSimpleValue());
        System.out.println(beanB.getSecondValue());
        System.out.println(beanB.getRandomValue());

        factory.close();
    }
}
