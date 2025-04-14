package com.study.spring.batch.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/**
 * Spring Job의 의존성 주입을 Quartz Job에 적용하기 위한 커스텀 JobFactory
 */
public class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {

    // Spring Bean 강제 주입을 위한 beanFactory
    private AutowireCapableBeanFactory beanFactory;

    /**
     * ApplicationContext에서 BeanFactory를 받아옴
     * @param applicationContext
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.beanFactory = applicationContext.getAutowireCapableBeanFactory();
    }

    /**
     * Quartz Job 인스턴스 생성 및 의존성 주입 수행
     * @param bundle
     * @return
     * @throws Exception
     */
    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object jobInstance = super.createJobInstance(bundle);           // JobInstance 생성
        beanFactory.autowireBean(jobInstance);                          // Spring Bean 주입 수행
        return super.createJobInstance(bundle);
    }
}
