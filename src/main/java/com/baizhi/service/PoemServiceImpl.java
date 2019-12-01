package com.baizhi.service;


import com.baizhi.Repository.BookRepository;
import com.baizhi.dao.PoemDao;
import com.baizhi.entity.Poem;
import com.baizhi.entity.T_poem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Transactional
@Service
public class PoemServiceImpl implements PoemService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PoemDao poemDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Poem> selectAll() {
        List<Poem> poems = poemDao.selectAll();
        for (Poem poem : poems) {
            bookRepository.save(poem);
        }
        return poems;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public T_poem selectOne(String name) {
        T_poem poem = poemDao.selectOne(name);
        return poem;
    }

    @Override
    public void add(T_poem t_poem) {
        String id = UUID.randomUUID().toString();
        t_poem.setId(id);
        poemDao.add(t_poem);
    }
}
