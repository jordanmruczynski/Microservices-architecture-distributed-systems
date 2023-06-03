package com.jordanmruczynski.notification;

import com.jordanmruczynski.amqp.RabbitMQMessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
        scanBasePackages = {
                "com.jordanmruczynski.notification",
                "com.jordanmruczynski.amqp"
        }
)
@EnableEurekaClient
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            RabbitMQMessageProducer rabbitMQMessageProducer,
            NotificationConfig notificationConfig) {
        return args -> {
             rabbitMQMessageProducer.publish(
                     "foo",
                     notificationConfig.getInternalExchange(),
                     notificationConfig.getInternalNotificationRoutingKey());
        };
    }
}