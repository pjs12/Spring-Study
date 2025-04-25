package com.study.spring.batch.jobs;

import java.util.Arrays;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class TestJobConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final String CLASS_NAME = this.getClass().getSimpleName();

    @Bean
    public Job job() {
        log.info(">>>> {} Job 생성", CLASS_NAME);
        return new JobBuilder(CLASS_NAME + "Job", jobRepository)
            .start(this.step())
            .build();
    }

    @Bean
    public Step step() {
        log.info(">>>> {} Step 생성", CLASS_NAME);
        return new StepBuilder(CLASS_NAME + "Step", jobRepository)
            .<Integer, String>chunk(10, transactionManager)
            .reader(this.reader())
            .processor(processor())
            .writer(writer())
            .build();
    }

    @Bean
    @StepScope
    public ItemReader<Integer> reader() {
        return new ListItemReader<>(Arrays.asList(123123, 2, 3));
    }

    private ItemProcessor<Integer, String> processor() {
        return String::valueOf;
    }

    private ItemWriter<String> writer() {
        return items -> items.forEach(log::info);
    }
}
