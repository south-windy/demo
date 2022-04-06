package com.south.elasticsearch.controller;

import com.south.elasticsearch.es_data.PointData;
import com.south.elasticsearch.service.EsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1/es")
@Slf4j
@Api(tags = "ES服务")
public class EsController {

    private final EsService esService;

    @ApiOperation(value = "hello", httpMethod = "GET",
            notes = "ES检查")
    @GetMapping(value = "/hello")
    public Map<String, Object> hello() {

        Map<String, Object> query = new HashMap<>();
        try {
            PointData data1 = PointData.builder()
                    .id(14L)
                    .name("琪琪")
                    .desc("琪琪是名JAVA开发工程师")
                    .build();
            String indexId = "test001";
            esService.save(data1, indexId);

            PointData data2 = PointData.builder()
                    .id(13L)
                    .name("龙六")
                    .desc("龙六是一名数据产品经理")
                    .build();
            indexId = "test002";
            esService.save(data2, indexId);

            query = esService.query(indexId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return query;
    }
}
