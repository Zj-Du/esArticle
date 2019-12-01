package com.baizhi.dao;

import com.baizhi.entity.Poem;
import com.baizhi.entity.T_poem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PoemDao {

    public List<Poem> selectAll();

    public T_poem selectOne(@Param("name") String name);

    public void add(@Param("t_poem") T_poem t_poem);
}
