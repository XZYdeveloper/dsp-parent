package com.mapper;

import com.entity.HistoryResponse.Goods;

import java.util.List;

public interface HistoryMapper {
    Goods getResponseGoodsById(String id);
}
