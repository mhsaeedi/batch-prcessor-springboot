package com.batchprocessor.cloud.indexation.agent

import com.batchprocessor.cloud.indexation.agent.service.JobService
import com.batchprocessor.cloud.indexation.agent.service.data.Job
import com.batchprocessor.cloud.indexation.agent.shared.constant.Country
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor


@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties
@EnableScheduling
class AgentApp @Autowired constructor(
	private val jobService: JobService,
) : CommandLineRunner {


	@Bean
	@Primary
	fun executor(): ThreadPoolTaskExecutor = with(ThreadPoolTaskExecutor()) {
		corePoolSize = 4 * Runtime.getRuntime().availableProcessors()
		maxPoolSize = corePoolSize
		setQueueCapacity(Int.MAX_VALUE)
		setThreadNamePrefix(THREAD_PREFIX)
		initialize()
		return this
	}

	override fun run(vararg args: String?) {
		// agent serves functionalities through rest endpoints
		// Uncomment for a local run
        val job1 = Job(1, Country.DE, 10000, 10, 50, 0.65)
//        val job2 = Job(2, Country.DE, 10000, 10, 50, 0.65)
		jobService.run(job1)
//		jobService.run(job2)
	}


	companion object {
		private const val THREAD_PREFIX = "agent-"

		@JvmStatic
		fun main(args: Array<String>) {
			runApplication<AgentApp>(*args)

		}
	}

}
