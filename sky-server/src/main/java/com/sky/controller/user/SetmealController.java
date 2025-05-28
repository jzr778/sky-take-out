package com.sky.controller.user;


import com.sky.entity.Setmeal;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.DishItemVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userSetmealController")
@RequestMapping("/user/setmeal")
@Slf4j
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    /**
     * 根据分类id查询套餐
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    public Result<List<Setmeal>> list(Long categoryId){
        log.info("根据分类id查询套餐：{}",categoryId);

        List<Setmeal> list = setmealService.getByCategoryId(categoryId);
        return Result.success(list);
    }

    /**
     * 根据套餐id查询包含的菜品数据
     * @param id
     * @return
     */
    @GetMapping("/dish/{id}")
    public Result<List<DishItemVO>> getDishItemById(@PathVariable Long id){
        log.info("根据套餐id查询菜品选项：{}",id);

        List<DishItemVO> list = setmealService.getDishItemById(id);

        return Result.success(list);
    }
}
