package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DishMapper {
    /**
     * 插入菜品数据
     * @param dish
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(Dish dish);

    /**
     * 根据菜品id查询菜品数据
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{cateforyId}")
    Integer countByCategoryId(Long categoryId);
}
