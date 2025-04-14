package com.study.spring.batch.config;

import org.quartz.spi.JobFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Quartz Scheduler 설정 클래스
 * - Spring과 Quartz 통합
 * - Spring Bean을 사용할 수 있도록 JobFactory 설정
 */
@Configuration
public class QuartzConfig {

    /**
     * Spring 의존성 주입 (DI)가 가능한 AutowiringSpringBeanJobFactory Bean 생성
     * @param applicationContext
     * @return
     */
    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    /**
     * Quartz의 SchedulerFactoryBean 생성
     * - 이 Bean을 통해 Job 등록/실행 가능
     * - JobFactory를 설정하여 DI 지원
     * @param jobFactory
     * @return
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory) {
        // SchedulerFactoryBean - Spring에서 Quartz를 설정하고 관리할 수 있도록 함
        // => Quartz Scheduler가 Spring Context 안에 살아있게 되고 어디서든 Scheduler Bean을 주입받아서 컨트롤 가능
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();

        // DI 가능한 JobFactory(AutowiringSpringBeanJobFactory) 연결
        schedulerFactoryBean.setJobFactory(jobFactory);

        return schedulerFactoryBean;
    }
}