package ru.vereshchakov.keep_track

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import
import ru.vereshchakov.keep_track.client.HabrFindLink
import ru.vereshchakov.keep_track.config.QuartzConfig

@SpringBootApplication
@ComponentScan("ru.vereshchakov")
@Import(QuartzConfig::class)
open class Application {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(Application::class.java, *args)
            val habr = HabrFindLink()
            habr.find()
        }
    }
}

