package com.mapper;

import com.entity.TopCategory;
import com.entity.UnderlyingCategory;

import java.util.List;

/**
 * @author 杨可
 */

public interface CategoryMapper {

    /**
     * 查询所有一级分类
     * @return TopCategory
     */
    List<TopCategory> getAllTopCategory();

    /**
     * 增加一个一级分类
     * @param topcategory
     */
    void saveTopCategory(TopCategory topcategory);

    /**
     * 根据id删除一个一级分类
     * @param id
     */
    void deleteTopCategory(Integer id);

    /**
     * 更改一个一级分类
     * @param topcategory
     */
    void updateTopCategory(TopCategory topcategory);

    /**
     * 查询所有二级分类
     * @return UnderlyingCategory
     */
    List<UnderlyingCategory> getAllUnderlyingCategory();

    /**
     * 根据一级的id查询二级分类
     * @param id
     * @return List<UnderlyingCategory>
     */
    List<UnderlyingCategory> findById(Integer id);

}
