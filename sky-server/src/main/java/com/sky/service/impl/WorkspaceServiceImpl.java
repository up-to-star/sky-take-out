package com.sky.service.impl;

import com.sky.entity.Orders;
import com.sky.mapper.DishMapper;
import com.sky.mapper.OrderMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.mapper.UserMapper;
import com.sky.service.WorkspaceService;
import com.sky.vo.BusinessDataVO;
import com.sky.vo.DishOverViewVO;
import com.sky.vo.OrderOverViewVO;
import com.sky.vo.SetmealOverViewVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class WorkspaceServiceImpl implements WorkspaceService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private SetmealMapper setmealMapper;


    @Override
    public BusinessDataVO getBusinessData(LocalDateTime begin, LocalDateTime end) {
        Map map = new HashMap();
        map.put("begin", begin);
        map.put("end", end);
        Integer totalOrderCount = orderMapper.getCntByMap(map);
        totalOrderCount = totalOrderCount == null ? 0 : totalOrderCount;
        map.put("status", Orders.COMPLETED);
        Integer validOrderCount = orderMapper.getCntByMap(map);
        validOrderCount = validOrderCount == null ? 0 : validOrderCount;
        Double totalTurnover = orderMapper.sumByMap(map);
        totalTurnover = totalTurnover == null ? 0.0 : totalTurnover;

        Integer newUsers = userMapper.countUserByMap(map);

        return BusinessDataVO
                .builder()
                .validOrderCount(validOrderCount)
                .turnover(totalTurnover)
                .orderCompletionRate(totalTurnover != 0 ? validOrderCount * 1.0 / totalOrderCount : 0.0)
                .unitPrice(validOrderCount != 0 ? totalTurnover / validOrderCount : 0.0)
                .newUsers(newUsers)
                .build();
    }

    @Override
    public OrderOverViewVO getOrderOverview(LocalDateTime begin, LocalDateTime end) {
        Map map = new HashMap();
        map.put("begin", begin);
        map.put("end", end);
        Integer allOrders = orderMapper.getCntByMap(map);
        map.put("status", Orders.CANCELLED);
        Integer cancelledOrders = orderMapper.getCntByMap(map);
        map.put("status", Orders.COMPLETED);
        Integer completedOrders = orderMapper.getCntByMap(map);
        map.put("status", Orders.TO_BE_CONFIRMED);
        Integer waitingOrders = orderMapper.getCntByMap(map);
        map.put("status", Orders.CONFIRMED);
        Integer deliveredOrders = orderMapper.getCntByMap(map);
        return OrderOverViewVO
                .builder()
                .allOrders(allOrders)
                .cancelledOrders(cancelledOrders)
                .completedOrders(completedOrders)
                .waitingOrders(waitingOrders)
                .deliveredOrders(deliveredOrders)
                .build();
    }

    @Override
    public DishOverViewVO getDishOverview() {
        Map map = new HashMap();
        map.put("status", 0);

        Integer discontinued = dishMapper.getCntByMap(map);
        map.put("status", 1);

        Integer sold = dishMapper.getCntByMap(map);

        return DishOverViewVO
                .builder()
                .discontinued(discontinued)
                .sold(sold)
                .build();
    }

    @Override
    public SetmealOverViewVO getSetmealOverview() {
        Map map = new HashMap();
        map.put("status", 0);
        Integer discontinued = setmealMapper.getCntByMap(map);
        map.put("status", 1);
        Integer sold = setmealMapper.getCntByMap(map);
        return SetmealOverViewVO
                .builder()
                .discontinued(discontinued)
                .sold(sold)
                .build();
    }
}
