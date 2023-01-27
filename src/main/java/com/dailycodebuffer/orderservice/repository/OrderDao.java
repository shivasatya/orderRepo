package com.dailycodebuffer.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dailycodebuffer.orderservice.entity.OrderEntity;

public interface OrderDao  extends JpaRepository<OrderEntity, Long>{

}
