package com.study.spring.batch.jobs;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
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
        // JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        String jobName = jobDataMap.getString("jobName");

        if (jobName == null) {
            throw new JobExecutionException("jobName이 존재하지 않습니다.");
        }

        log.info("Quartz에서 실행할 Spring Batch Job: {}", jobName);

        try {
            org.springframework.batch.core.Job job = jobLocator.getJob(jobName);

            // ㅓ
            JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

            jobLauncher.run(job, jobParameters);
        } catch (NoSuchJobException e) {
            throw new JobExecutionException("등록되지 않은 Job입니다: " + jobName, e);
        } catch (JobExecutionAlreadyRunningException e) {
            throw new JobExecutionException("이미 실행중인 Job입니다: " + jobName, e);
        } catch (JobRestartException e) {
            throw new JobExecutionException("Job을 재시작할 수 없습니다: " + jobName, e);
        } catch (JobInstanceAlreadyCompleteException e) {
            throw new JobExecutionException("이미 완료된 Job입니다: " + jobName, e);
        } catch (JobParametersInvalidException e) {
            throw new JobExecutionException("유효하지 않은 JobParameters입니다: " + jobName, e);
        } catch (Exception e) {
            throw new JobExecutionException("Spring Batch Job 실행 중 예기치 않은 예외 발생: " + jobName, e);
        }
    }
}
