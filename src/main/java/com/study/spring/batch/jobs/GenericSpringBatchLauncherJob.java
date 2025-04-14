package com.study.spring.batch.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class GenericSpringBatchLauncherJob implements Job {
    private final JobLauncher jobLauncher;
    private final JobLocator jobLocator;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

    }
}
