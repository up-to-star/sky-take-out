package com.sky.service.impl;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import com.sky.mapper.UserMapper;
import com.sky.service.WorkspaceService;
import com.sky.vo.BusinessDataVO;
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
}
