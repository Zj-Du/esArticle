package com.baizhi.Repository;

import com.baizhi.entity.Poem;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

public interface BookRepository extends ElasticsearchCrudRepository<Poem, String> {
}
