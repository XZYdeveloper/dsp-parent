package com.mapper;

import com.entity.Prefer;
import com.entity.Prefer_Cmother;
import com.entity.Prefer_Comment;

import java.util.List;

public interface PreferMapper {
    List<Prefer> getAll();
    List<Prefer> insert_Prefer();
    List<Prefer> update_Prefer();
    List<Prefer> delete_Prefer();

    List<Prefer_Comment> getAllComment();
    List<Prefer_Comment> insert_Prefer_Comment();
    List<Prefer_Comment> update_Prefer_Comment();
    List<Prefer_Comment> delete_Prefer_Comment();


    List<Prefer_Cmother> getAllCmother();
    List<Prefer_Cmother> insert_Prefer_Cmother();
    List<Prefer_Cmother> update_Prefer_Cmother();
    List<Prefer_Cmother> delete_Prefer_Cmother();


}
