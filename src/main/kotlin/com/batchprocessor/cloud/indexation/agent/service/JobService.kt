package com.batchprocessor.cloud.indexation.agent.service

import com.batchprocessor.cloud.indexation.agent.service.data.Job
import com.batchprocessor.cloud.indexation.agent.service.engine.Master
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author : Mohammad <mohammad.saeedi@visual-meta.com>
 * @since : 08.10.21, Fri
 *
 **/
@Service
class JobService @Autowired constructor(
	private val master: Master,
) {

	/**
	 * Run a collection job asynchronously
	 */
	fun run(job: Job) = master.asyncRun(job)
	fun isInvalid(job: Job): Boolean = TODO("Implement or remove")
	fun exist(jobId: Int): Boolean = TODO("Implement or remove")
	fun cancel(jobId: Int): Boolean = TODO("Implement or remove")
	fun getStatuses(): List<Job> = TODO("Implement or remove")
	fun getStatus(jobId: Int): Job = TODO("Implement or remove")
}




