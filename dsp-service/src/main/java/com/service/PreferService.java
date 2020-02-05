package com.service;

import com.mapper.PreferMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PreferService<PreferMapper> {
    @Autowired
    private PreferMapper prefer_mapper;
    private PreferMapper prefer_cmother_mapper;
    private PreferMapper prefer_comment_mapper;

    public PreferMapper getPrefer_content_mapper()
    {
        return prefer_comment_mapper;
    }

    public PreferMapper getPrefer_cmother_mapper()
    {
        return prefer_cmother_mapper;
    }

    public PreferMapper getPrefer_mapper()
    {
        return prefer_mapper;
    }
}
