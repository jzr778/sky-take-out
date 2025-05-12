package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.SetmealVO;
import org.springframework.stereotype.Service;

public interface SetmealService {

    /**
     * 根据套餐id查询套餐
     * @param id
     * @return
     */
    SetmealVO getById(Long id);

    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     * @param setmealDTO
     * @return
     */
    void saveWithDish(SetmealDTO setmealDTO);
}
