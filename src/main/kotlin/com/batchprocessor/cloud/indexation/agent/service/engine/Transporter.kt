package com.batchprocessor.cloud.indexation.agent.service.engine

import com.batchprocessor.cloud.indexation.agent.service.data.Batch
import com.batchprocessor.cloud.indexation.agent.shared.util.S3Util
import kotlinx.coroutines.Job
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

/**
 * @author : Mohammad <mohammad.saeedi></mohammad.saeedi>@visual-meta.com>
 * @since : 27.10.21, Wed
 */
@Service
class Transporter @Autowired constructor(
    private val s3Util: S3Util,
){

	@Async
	fun transfer(batch: Batch) {
        val key = "${batch.job.id}-${batch.number}"
		transfers.putIfAbsent(key, s3Util.uploadBatchJob(batch))
        log.info("Transfer batch: ${batch.number}, country: ${batch.job.country}, jobId: ${batch.job.id}, size: ${batch.uris.size}")
	}

	@Scheduled(fixedRate = 10000)
	fun report() {
        log.info("======= Transfer Batches info =======")
		for ((key, job) in transfers) {
            if (job.isActive) log.info("Transfer of batch: $key is Active")
            if (job.isCancelled) log.info("Transfer of batch: $key is Cancelled")
            if (job.isCompleted) {
                log.info("Transfer of batch: $key is Completed")
                transfers.remove(key)
            }
		}
        log.info("=====================================")
	}

	companion object {
		private val log = LoggerFactory.getLogger(Transporter::class.java)
		private val transfers = ConcurrentHashMap<String, Job>()
	}

}
