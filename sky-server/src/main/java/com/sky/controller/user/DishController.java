package com.sky.controller.user;


import com.sky.entity.Dish;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userDishController")
@RequestMapping("/user/dish")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 根据分类id查询菜品
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    public Result<List<DishVO>> list(Long categoryId){
        log.info("根据分类id查询菜品：{}",categoryId);

        // 构造redis中的key：dish_id
        String key = "dish_" + categoryId;

        // 查询redis中是否有存在菜品数据
        List<DishVO> list = (List<DishVO>) redisTemplate.opsForValue().get(key);
        if(list != null && list.size() > 0){
            // 存在则直接返回
            return Result.success(list);
        }

        // 不存在，查询数据库，并将数据保存到redis中
        list = dishService.listWithFlavors(categoryId);
        redisTemplate.opsForValue().set(key,list);

        return Result.success(list);
    }
}
