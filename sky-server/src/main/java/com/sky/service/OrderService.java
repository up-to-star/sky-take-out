package com.sky.service;

import com.sky.dto.*;
import com.sky.result.PageResult;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;

public interface OrderService {
    OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO);

    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception;

    /**
     * 支付成功，修改订单状态
     * @param outTradeNo
     */
    void paySuccess(String outTradeNo);


    PageResult historyPageQuery(OrdersPageQueryDTO ordersPageQueryDTO);

    OrderVO orderDetailById(Long id);

    void userCancelOrder(Long id) throws Exception;

    void repetion(Long id);

    PageResult orderSearchCondition(OrdersPageQueryDTO ordersPageQueryDTO);

    OrderStatisticsVO getStatictics();

    OrderVO getDetail(Long id);

    void confirm(OrdersConfirmDTO ordersConfirmDTO);

    void reject(OrdersRejectionDTO ordersRejectionDTO);

    void adminCancel(OrdersCancelDTO ordersCancelDTO);

    void delivery(Long id);

    void complete(Long id);

    void reminder(Long orderId);
}
