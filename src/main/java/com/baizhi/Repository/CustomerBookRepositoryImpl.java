package com.baizhi.Repository;

import com.baizhi.entity.Poem;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
public class CustomerBookRepositoryImpl implements CustomerBookRepository {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public List<Poem> findByNameAndHighlightAdnPageable(String name, int page, int size) {


        HighlightBuilder.Field nameField = new HighlightBuilder
                .Field("*")
                .preTags("<span style='color:red'>")
                .postTags("</span>").requireFieldMatch(false);


        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()

                .withQuery(QueryBuilders.multiMatchQuery(name, "name", "content", "author"))
                .withPageable(PageRequest.of(page, size))
                .withHighlightFields(nameField)
                .build();

        AggregatedPage<Poem> poems = elasticsearchTemplate.queryForPage(nativeSearchQuery, Poem.class, new SearchResultMapper() {

            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
                SearchHits searchHits = response.getHits();
                SearchHit[] hits = searchHits.getHits();
                ArrayList<Poem> books = new ArrayList<Poem>();
                for (SearchHit hit : hits) {
                    Poem poem = new Poem();
                    //原始map
                    Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                    poem.setId(sourceAsMap.get("id").toString());
                    poem.setName(sourceAsMap.get("name").toString());
                    poem.setAuthor(sourceAsMap.get("author").toString());
                    poem.setContent(sourceAsMap.get("content").toString());
                    //poem.setOrigin(sourceAsMap.get("origin").toString());
                    //poem.setCategoryName(sourceAsMap.get("categoryName").toString());

                    //高亮
                    Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                    System.out.println(highlightFields);
                    if (highlightFields.get("name") != null) {
                        String nameHighlight = highlightFields.get("name").getFragments()[0].toString();
                        poem.setName(nameHighlight);
                    }
                    if (highlightFields.get("content") != null) {
                        String contentHighlight = highlightFields.get("content").getFragments()[0].toString();
                        poem.setContent(contentHighlight);
                    }
                    books.add(poem);
                }
                return new AggregatedPageImpl<T>((List<T>) books);
            }
        });
        return poems.getContent();
    }


}
