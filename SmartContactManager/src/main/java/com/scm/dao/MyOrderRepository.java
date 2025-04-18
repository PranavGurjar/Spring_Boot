package com.scm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.entities.MyOrder;

public interface MyOrderRepository extends JpaRepository<MyOrder, Long> {
	
	public MyOrder findByOrderId(String orderId);
}
