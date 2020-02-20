package com.mapper;

import com.entity.Goods;
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
     * 根据一级的id查询二级分类
     * @param id
     * @return List<UnderlyingCategory>
     */
    List<UnderlyingCategory> findUnderlyingById(Integer id);

    /**
     * 根据二级分类的id查询商品
     * @param id
     * @return List<Goods>
     */
    List<Goods> findGoodsById(Integer id);


}
