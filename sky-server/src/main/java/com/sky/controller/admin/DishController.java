package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 菜品管理
 */

@RestController
@RequestMapping("/admin/dish")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;
    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping
    public Result save(@RequestBody DishDTO dishDTO) {
        log.info("新增菜品:{}", dishDTO);
        dishService.saveWithflavors(dishDTO);

        // 清理缓存数据
        String key = "dish_" + dishDTO.getCategoryId();
        cleanCache(key);

        return Result.success();
    }

    /**
     * 菜品分页查询
     */
    @GetMapping("/page")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {
        log.info("菜品分页查询:{}", dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 菜品批量删除，多个菜品的id以逗号隔开，如1,2,3
     * @RequestParam MVC框架动态解析请求参数并绑定到方法形参上
     *
     * @param ids
     * @return
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Long> ids){
        log.info("菜品批量删除:{}", ids);
        dishService.deleteBatch(ids);

        // 批量删除影响的菜品可能影响多个分类，因此需要清理所有分类缓存数据
        cleanCache("dish_*");

        return Result.success();
    }

    /**
     * 根据id查询菜品
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<DishVO> getById(@PathVariable Long id){
        log.info("根据id查询菜品：{}",id);
        DishVO dishVO = dishService.getByIdWithCategoryNameAndFlavor(id);
        return Result.success(dishVO);
    }

    /**
     * 修改菜品
     * @param dishDTO
     * @return
     */
    @PutMapping
    public Result update(@RequestBody DishDTO dishDTO){
        log.info("修改菜品信息：{}",dishDTO);
        dishService.updateWithFlavors(dishDTO);

        // 修改菜品分类会影响两个分类，太麻烦了直接清理全部缓存
        cleanCache("dish_*");

        return Result.success();
    }

    /**
     * 根据分类id查询菜品
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    public Result<List<Dish>> list(Long categoryId){
        log.info("根据分类id查询菜品：{}",categoryId);
        List<Dish> list = dishService.list(categoryId);
        return Result.success(list);
    }

    /**
     * 菜品起售停售
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    public Result startOrStop(@PathVariable Integer status,Long id){
        log.info("菜品状态修改：{}",status);
        dishService.startOrStop(status,id);

        // 如果精确清理，则根据菜品id查询菜品分类id
        cleanCache("dish_*");

        return Result.success();
    }

    /**
     * 清理缓存数据
     * @param pattern
     */
    private void cleanCache(String pattern) {
        Set keys = redisTemplate.keys(pattern);
        log.info("清理缓存：{}",keys);
        redisTemplate.delete(keys);
    }

}
