package com.baizhi.controller;

import com.baizhi.Repository.BookRepository;
import com.baizhi.entity.Poem;
import com.baizhi.entity.T_poem;
import com.baizhi.service.PoemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("poem")
public class PoemController {

    @Autowired
    private PoemService poemService;

    @Autowired
    private BookRepository bookRepository;

    @ResponseBody
    @RequestMapping("saveAll")
    public void saveAll() {
        List<Poem> poems = poemService.selectAll();
    }

    @ResponseBody
    @RequestMapping("selectOne")
    public T_poem selectOne(String name) {
        System.out.println("name = " + name);
        T_poem poem = poemService.selectOne(name);
        System.out.println("poem = " + poem);
        return poem;
    }

    @RequestMapping("add")
    public String add(T_poem t_poem) {
        poemService.add(t_poem);
        Poem poem = new Poem();
        poem.setId(t_poem.getId());
        poem.setName(t_poem.getName());
        poem.setAuthor(t_poem.getAuthor());
        poem.setContent(t_poem.getContent());
        poem.setCategoryName("诗句");
        poem.setOrigin("诗句");
        poem.setHref("sssss");
        poem.setOrigin("诗句");
        poem.setAuthordes("诗句");
        bookRepository.save(poem);
        return "redirect:/index.jsp";
    }

}
