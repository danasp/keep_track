package ru.vereshchakov.keep_track.job

import org.quartz.JobExecutionContext
import org.springframework.scheduling.quartz.QuartzJobBean
import ru.vereshchakov.keep_track.FindTopicsService

/**
 * User: Danila Vereshchakov
 * Date: 01.10.2017
 */
open class FindTopicsJob : QuartzJobBean() {

    lateinit var findTopicsService: FindTopicsService

    override fun executeInternal(context: JobExecutionContext?) {
        println("Hey!")
        findTopicsService.processData()
    }
}