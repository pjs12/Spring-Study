package com.study.spring.batch.config;

import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.JobRegistrySmartInitializingSingleton;
import org.springframework.batch.core.configuration.support.MapJobRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    /**
     * Job을 등록/조회/관리하는 인터페이스
     * 하지만 직접 Job을 등록할 일은 없음 -> 개발자들이 Job Class를 생성하고 빈으로 등록하면 자동으로 JobRegistry에 등록
     * @return
     */
    @Bean
    public JobRegistry jobRegistry() {
        // JobRegistry의 일반적인 구현체
        // Map<String, JobFactory> 구조로 Job 관리
        return new MapJobRegistry();
    }

    /**
     * Spring Context 초기화 과정에서 모든 Job 빈을 찾아 JobRegistry에 자동 등록
     * @param jobRegistry
     * @return
     */
    @Bean
    public JobRegistrySmartInitializingSingleton jobRegistrySmartInitializingSingleton(JobRegistry jobRegistry) {
        return new JobRegistrySmartInitializingSingleton(jobRegistry);
    }
}
