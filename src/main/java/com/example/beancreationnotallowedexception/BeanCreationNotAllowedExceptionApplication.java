package com.example.beancreationnotallowedexception;

import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.BeanCreationNotAllowedException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@SpringBootApplication
public class BeanCreationNotAllowedExceptionApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeanCreationNotAllowedExceptionApplication.class, args);
    }

    @Service
    static class MyService {
        private final MyBean myBean;

        MyService(MyBean myBean) {
            this.myBean = myBean;
        }

        @PreDestroy
        void destroy() {
            System.out.println("MyService.destroy");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            try {
                myBean.method();
            } catch (BeanCreationNotAllowedException ex) {
                // This is thrown because defaultValidator has already been closed.
                ex.printStackTrace();
            }
        }

    }

    @Component
    @Validated
    // This is a workaround for this issue:
    // @DependsOn("defaultValidator")
    static class MyBean {

        void method() {
            System.out.println("MyBean.method");
        }

        @PreDestroy
        void destroy() {
            System.out.println("MyBean.destroy");
        }
    }

}
