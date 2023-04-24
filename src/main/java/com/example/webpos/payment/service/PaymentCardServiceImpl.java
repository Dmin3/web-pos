package com.example.webpos.payment.service;

import com.example.webpos.order.domain.OrderItem;
import com.example.webpos.order.domain.OrderStatus;
import com.example.webpos.order.domain.Orders;
import com.example.webpos.order.exception.OrdersNotFoundException;
import com.example.webpos.order.repository.OrderItemRepository;
import com.example.webpos.order.repository.OrdersRepository;
import com.example.webpos.payment.domain.Payment;
import com.example.webpos.payment.domain.PaymentType;
import com.example.webpos.payment.dto.PaymentRes;
import com.example.webpos.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentCardServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrdersRepository ordersRepository;

    @Override
    public PaymentRes pay(Long orderId) {
        Orders orders = ordersRepository.findById(orderId).orElseThrow(OrdersNotFoundException::new);
        orders.setOrderStatus(OrderStatus.FINISH);

        List<OrderItem> orderItems = orderItemRepository.findByOrdersId(orderId);

        int sum = orderItems.stream().mapToInt(orderItem -> orderItem.getItem().getPrice() * orderItem.getAmount()).sum();

        Payment payment = Payment.builder().paymentType(getPaymentType()).totalPrice(sum).orders(orders).build();

        return PaymentRes.of(paymentRepository.save(payment));
    }

    @Override
    public PaymentType getPaymentType() {
        return PaymentType.CARD;
    }
}
