package com.study.spring.sample.mapper;

import org.mapstruct.Mapper;

import com.study.spring.sample.dto.SampleResponse;
import com.study.spring.sample.entity.SampleEntity;

@Mapper(componentModel = "spring")
public interface SampleMapper {
    SampleEntity sampleResponseToSampleEntity(SampleResponse sampleResponse);
    SampleResponse sampleEntityToSampleResponse(SampleEntity sampleEntity);
}
