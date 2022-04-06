package com.south.elasticsearch.service;

import com.south.elasticsearch.es_data.PointData;

import java.io.IOException;
import java.util.Map;

public interface EsService {

    boolean save(PointData pointData, String indexId) throws IOException;

    Map<String, Object> query(String indexId) throws IOException;
}
