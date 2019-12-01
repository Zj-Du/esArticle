package com.baizhi.controller;


import com.baizhi.Repository.CustomerBookRepository;
import com.baizhi.entity.Poem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("es")
public class EsController {

    @Autowired
    private CustomerBookRepository customerBookRepository;

    @RequestMapping("select")
    public List<Poem> select(String name, int page) {
        System.out.println("name = " + name);
        System.out.println("page = " + page);
        List<Poem> poems = customerBookRepository.findByNameAndHighlightAdnPageable(name, page, 6);
        System.out.println("poems = " + poems.size());
        return poems;
    }
}
