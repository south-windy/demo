package com.south.elasticsearch.es_data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PointData {

    private Long id;

    private String name;

    private String desc;
}
