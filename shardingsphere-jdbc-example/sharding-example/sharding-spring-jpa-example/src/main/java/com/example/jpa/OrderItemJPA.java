package com.example.jpa;

import com.example.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemJPA extends JpaRepository<OrderItemEntity, Long> {

}
