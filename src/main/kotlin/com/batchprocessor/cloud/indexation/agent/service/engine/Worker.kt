package com.batchprocessor.cloud.indexation.agent.service.engine

import com.batchprocessor.cloud.indexation.agent.service.data.Batch
import com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.FirstUriBinder
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

/**
 * @author : Momo
 * @since : 11.10.21, Mon
 *
 **/
@Service
class Worker @Autowired constructor(
	private val firstUriBinder: FirstUriBinder,
	private val transporter: Transporter,
) {

	/**
	 * Processes each batch asynchronously,
	 * binds all required data
	 * and finally delivers the prepared batch to transporter
	 */
	@Async
	fun asyncProcess(batch: Batch) = with(batch) {
		log.info("Start batch: $number of $ofBatchGroupWithSize, Country: ${job.country}, JobId: ${job.id}, size: ${uris.size}")
			.also { uris.parallelStream().forEach { firstUriBinder.bind(job, it) } }
			.also { transporter.transfer(batch) }
	}

	companion object {
		private val log = LoggerFactory.getLogger(Worker::class.java)
	}

}
