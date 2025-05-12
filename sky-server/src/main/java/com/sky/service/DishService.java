package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DishService {

    /**
     * 新增菜品和对应口味
     * @param dishDTO
     */
    public void saveWithflavors(DishDTO dishDTO);

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 菜品批量删除
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * 根据id查询菜品
     * （返回的VO中包含CategoryName和Flavor）
     * @param id
     * @return
     */
    DishVO getByIdWithCategoryNameAndFlavor(Long id);

    void updateWithFlavors(DishDTO dishDTO);

    /**
     * 根据分类id查询菜品
     * @param categoryId
     * @return
     */
    List<Dish> list(Long categoryId);

    /**
     * 启售停售菜品
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);
}
