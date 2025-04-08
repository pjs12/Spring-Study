package com.study.spring.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.spring.sample.entity.SampleEntity;

public interface SampleRepository extends JpaRepository<SampleEntity, Long> {
}
