package com.batchprocessor.cloud.indexation.agent

import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest

class AgentAppTests {

    val log = LoggerFactory.getLogger(javaClass)

	@Test
	fun should_LoadContext_WhenSpringContainerIsRun() {
	    log.info("Application context loaded successfully.")
	}

}
