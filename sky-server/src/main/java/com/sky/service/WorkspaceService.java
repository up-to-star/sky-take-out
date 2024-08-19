package com.sky.service;

import com.sky.vo.BusinessDataVO;
import com.sky.vo.OrderOverViewVO;

import java.time.LocalDateTime;

public interface WorkspaceService {

    BusinessDataVO getBusinessData(LocalDateTime begin, LocalDateTime end);

    OrderOverViewVO getOrderOverview(LocalDateTime begin, LocalDateTime end);
}
