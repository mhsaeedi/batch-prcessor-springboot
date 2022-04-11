package com.batchprocessor.cloud.indexation.agent.service.engine

import com.batchprocessor.cloud.indexation.agent.service.data.Batch
import com.batchprocessor.cloud.indexation.agent.service.data.Job
import com.batchprocessor.cloud.indexation.agent.service.data.Uri
import com.batchprocessor.cloud.indexation.agent.shared.util.FileUtil
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

/**
 * @author : Momo
 * @since : 08.10.21, Fri
 *
 **/
@Service
class Master @Autowired constructor(
	private val fileUtil: FileUtil,
	private val worker: Worker,
) {


	@Async
	fun asyncRun(job: Job): CompletableFuture<String> =
		fileUtil.readLines(job).chunked(job.desiredSizeOfEachBatch)// divide lines into multiple batches
			.also {
				log.info("Start job. Country: ${job.country}, jobId: ${job.id}, batches: ${it.size} Uris: ${it.sumOf { eachBatch -> eachBatch.size }}")
			}.run {
				map { unprocessedLines -> unprocessedLines.map { line -> Uri(line = line) } }
					.mapIndexed { batchNumber, uris -> Batch(job, batchNumber, uris, size) } // prepare required data for each batch
					.forEach(worker::asyncProcess) // start processing each batch asynchronously
				CompletableFuture.completedFuture("done")  // we do not use this return yet, however we will need it in the future
			}

	companion object {
		private val log = LoggerFactory.getLogger(Master::class.java)
	}

}
