package com.example.controller;

import com.example.entity.OrderItemEntity;
import com.example.jpa.OrderItemJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("orderItem")
public class OrderItemController {


    @Autowired
    private OrderItemJPA orderItemJPA;

    @RequestMapping("list")
    public List<OrderItemEntity> list(){
        return orderItemJPA.findAll();
    }

    @RequestMapping("save")
    public OrderItemEntity save(OrderItemEntity orderItemEntity){
        return (OrderItemEntity) orderItemJPA.save(orderItemEntity);
    }
}
