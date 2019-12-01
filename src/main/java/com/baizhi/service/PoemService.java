package com.baizhi.service;


import com.baizhi.entity.Poem;
import com.baizhi.entity.T_poem;

import java.util.List;

public interface PoemService {

    public List<Poem> selectAll();

    public T_poem selectOne(String name);

    public void add(T_poem t_poem);
}
