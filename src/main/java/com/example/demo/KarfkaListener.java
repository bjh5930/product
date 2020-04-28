package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KarfkaListener {

    @Autowired
    ProductRepository productRepository;

    @StreamListener(Processor.INPUT)
    public void onEventByString(@Payload OrderPlaced orderplaced){
        if( orderplaced.getEventType().equals("OrderPlaced")){
            System.out.println("======================");
            System.out.println("재고량 수정 - 기존데이터가 없으니 현재는 그냥 저장로직만 수행");
            Product p = new Product();
            p.setName(orderplaced.getProductName());
            p.setStock(orderplaced.getQty());
            productRepository.save(p);
            System.out.println("======================");
        }

//        System.out.println(productChanged);
    }

}
