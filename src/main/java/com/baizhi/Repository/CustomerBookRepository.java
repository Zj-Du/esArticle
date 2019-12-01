package com.baizhi.Repository;

import com.baizhi.entity.Poem;

import java.util.List;

public interface CustomerBookRepository {
    List<Poem> findByNameAndHighlightAdnPageable(String name, int page, int size);
}
