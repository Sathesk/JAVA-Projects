package com.microservice.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {

    private Long orderId;
    private Long productId;
    private Integer quntity;
    private double totalPrice;

    //product details

    private String productName;
    private double productPrice;


}
