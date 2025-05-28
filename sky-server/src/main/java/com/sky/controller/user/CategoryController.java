package com.sky.controller.user;

import com.sky.entity.Category;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController("userCategoryController")
@RequestMapping("/user/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * （根据type）查询分类
     * @return
     */
    @GetMapping("/list")
    public Result<List<Category>> list(Integer type) {
        log.info("根据type[0/1]查询分类：{}",type);
        List<Category> list = categoryService.list(type);
        return Result.success(list);
    }
}
