package ru.vereshchakov.keep_track.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.quartz.CronTriggerFactoryBean
import org.springframework.scheduling.quartz.JobDetailFactoryBean
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean
import org.springframework.scheduling.quartz.SchedulerFactoryBean
import ru.vereshchakov.keep_track.job.FindTopicsJob
import ru.vereshchakov.keep_track.FindTopicsService

/**
 * User: Danila Vereshchakov
 * Date: 14.10.2017
 */

@Configuration
open class QuartzConfig {

    @Bean
    open fun dataService() = FindTopicsService()

    @Bean
    open fun jobDetailFactoryBean(): JobDetailFactoryBean {
        val factoryBean: JobDetailFactoryBean = JobDetailFactoryBean()
        factoryBean.setJobClass(FindTopicsJob::class.java)
        factoryBean.setJobDataAsMap(mapOf("findTopicsService" to dataService()))
        return factoryBean
    }

    //use this factory if you want to run appropriate method in any class and don't want create small Job class
    @Bean
    open fun methodInvokingJobDetailFactoryBean(): MethodInvokingJobDetailFactoryBean {
        var factoryBean: MethodInvokingJobDetailFactoryBean = MethodInvokingJobDetailFactoryBean()
        factoryBean.targetObject = FindTopicsService()
        factoryBean.targetMethod = "processData"
        factoryBean.setConcurrent(false)
        factoryBean.`object`
        return factoryBean
    }

    @Value("\${quartz.find.topics.job}")
    lateinit var findTopicsJob: String

    @Bean
    open fun findTopicsCronTriggerFactoryBean(): CronTriggerFactoryBean {
        var cronTrigger = CronTriggerFactoryBean()
        cronTrigger.setJobDetail(jobDetailFactoryBean().`object`)
        cronTrigger.setCronExpression(findTopicsJob)
        return cronTrigger
    }

    @Bean
    open fun schedulerFactoryBean(): SchedulerFactoryBean {
        var scheduler = SchedulerFactoryBean()
        scheduler.setTriggers(findTopicsCronTriggerFactoryBean().`object`)
        //put here all other triggers
        return scheduler
    }
}