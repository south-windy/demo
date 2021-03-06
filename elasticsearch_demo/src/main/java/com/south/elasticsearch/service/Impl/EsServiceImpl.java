package com.south.elasticsearch.service.Impl;

import com.alibaba.fastjson.JSON;
import com.south.elasticsearch.es_data.PointData;
import com.south.elasticsearch.service.EsService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class EsServiceImpl implements EsService {
    private final RestHighLevelClient restHighLevelClient;

    private final String indexName = "demo-index";

    public EsServiceImpl(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    @Override
    public boolean save(PointData pointData, String indexId) throws IOException {
        String json = JSON.toJSONString(pointData);
        IndexRequest request = new IndexRequest(indexName).source(json, XContentType.JSON);
        log.info("request: " + JSON.toJSONString(request));
        IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        log.info("response: " + JSON.toJSONString(response));
        return true;
    }

    @Override
    public Map<String, Object> query(String indexId) throws IOException {
        /*SearchRequest searchRequest = new SearchRequest(indexId);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //????????????
        searchSourceBuilder.from(1);
        searchSourceBuilder.size(10);
        //????????????
//        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", keyword);
        //????????????
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", "??????");
        searchSourceBuilder.query(matchQueryBuilder);
        //??????????????????
//        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        //????????????
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        //??????????????????
        List<Map<String ,Object>> list = new ArrayList<>();
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            list.add(hit.getSourceAsMap());
            System.out.println(hit.getSourceAsMap().get("name")+"--------------");
        }*/

        GetRequest request = new GetRequest(indexName, indexId);
        log.info("request: " + JSON.toJSONString(request));
        GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
        log.info("response: " + JSON.toJSONString(response));
        return response.getSource();
    }
}
