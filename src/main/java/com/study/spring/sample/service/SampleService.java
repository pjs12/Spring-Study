package com.study.spring.sample.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.study.spring.common.BaseService;
import com.study.spring.sample.dto.SampleResponse;
import com.study.spring.sample.entity.SampleEntity;
import com.study.spring.sample.mapper.SampleMapper;
import com.study.spring.sample.repository.SampleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SampleService extends BaseService {
    private final SampleRepository sampleRepository;
    private final SampleMapper sampleMapper;

    public String getSampleString() {
        return "sampleText";
    }

    /**
     * Sample 전체 조회
     * @return
     */
    public List<SampleResponse> getAllSample() {
        List<SampleEntity> voList = sampleRepository.findAll();

        return voList.stream()
            .map(sampleMapper::sampleEntityToSampleResponse)
            .toList();
    }

    /**
     * Sample id별 단건 조회
     * @param id
     * @return
     */
    public SampleResponse getSampleById(Long id) {
        SampleEntity vo = sampleRepository.findById(id).orElse(null);
        return sampleMapper.sampleEntityToSampleResponse(vo);
    }
}
