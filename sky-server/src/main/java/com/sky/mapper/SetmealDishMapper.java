package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    /**
     * 根据菜品id查询对应套餐id
     * 多对多
     * 可能返回多个套餐
     */
    // select setmeal_id from setmeal_dish where dish_id in (1,2,3,...)
    List<Long> getSetmealIdsByDishId(List<Long> dishIds);


    @Select("select * from setmeal_dish where setmeal_id = #{setmealId}")
    List<SetmealDish> getBySetmealId(Long setmealId);
}
