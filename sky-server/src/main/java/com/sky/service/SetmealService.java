package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;

import java.util.List;

public interface SetmealService {
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    void saveWithDish(SetmealDTO setmealDTO);

    SetmealVO getByIdWithDish(Long id);

    void startOrStop(Integer status, Long id);

    void updateWithDish(SetmealDTO setmealDTO);

    void deleteByIds(List<Long> ids);

    List<Setmeal> list(Setmeal setmeal);

    List<DishItemVO> getDishItemById(Long id);
}
