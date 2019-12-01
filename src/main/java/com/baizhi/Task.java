package com.baizhi;

import com.baizhi.service.PoemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Task {

    @Autowired
    private PoemService poemService;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 20000)
    public void testTasks() {
        System.out.println("定时任务执行时间：" + dateFormat.format(new Date()));
        // List<Poem> poems = poemService.selectAll();
    }

}
