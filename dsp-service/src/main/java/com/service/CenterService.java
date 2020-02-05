package com.service;

import com.entity.GoodsCmother;
import com.entity.GoodsComment;
import com.mapper.CenterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangkun
 * @date 2020/2/3
 */
@Service
public class CenterService {
    @Autowired
    CenterMapper centerMapper;

    public CenterMapper getCenterMapper() {
        return centerMapper;
    }

    public void setCenterMapper(CenterMapper centerMapper) {
        this.centerMapper = centerMapper;
    }
}
