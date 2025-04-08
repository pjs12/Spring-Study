package com.study.spring.sample.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.spring.common.BaseController;
import com.study.spring.sample.dto.SampleResponse;
import com.study.spring.sample.service.SampleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/sample")
@RequiredArgsConstructor
public class SampleController extends BaseController {
    private final SampleService sampleService;

    @GetMapping("")
    public ResponseEntity<String> getSample() {
        String result = sampleService.getSampleString();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SampleResponse>> getAllSample() {
        List<SampleResponse> result = sampleService.getAllSample();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SampleResponse> getSampleById(@PathVariable Long id) {
        SampleResponse result = sampleService.getSampleById(id);
        return ResponseEntity.ok(result);
    }
}
