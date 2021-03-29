package com.example;

import com.example.controller.OrderItemController;
import com.example.entity.OrderItemEntity;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OrderItemTest {

    Logger logger = LoggerFactory.getLogger(OrderItemTest.class);

    @Autowired
    private OrderItemController orderItemController;

    @Test
    public void list(){
        logger.info("begin list...");
        List<OrderItemEntity> lst = orderItemController.list();
        lst.stream().forEach(s->{
            logger.info("s:{}",s);
        });
    }

}
