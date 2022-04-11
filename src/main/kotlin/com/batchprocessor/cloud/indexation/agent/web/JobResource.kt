package com.batchprocessor.cloud.indexation.agent.web

import com.batchprocessor.cloud.indexation.agent.service.data.Job
import com.batchprocessor.cloud.indexation.agent.service.JobService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * @author : Mohammad <mohammad.saeedi@visual-meta.com>
 * @since : 08.10.21, Fri
 *
 **/
@RestController
@RequestMapping("/job")
class JobResource @Autowired constructor(
	private val jobService: JobService,
) : JobResourceApi {

	@PostMapping
	override fun offer(job: Job): ResponseEntity<Void> = try {
		when {
			jobService.isInvalid(job) -> ResponseEntity.badRequest().build()
			jobService.exist(job.id) -> ResponseEntity.status(HttpStatus.CONFLICT).build()
			else -> submitJob(job)
		}
	} catch (e: Exception) {
		internalServerError("${e.javaClass.simpleName} happened at job offer with job: $job", e)
	}

	private fun submitJob(job: Job): ResponseEntity<Void> {
		jobService.run(job)
		return ResponseEntity.accepted().build()
	}

	@DeleteMapping
	override fun cancel(jobId: Int): ResponseEntity<Void> = try {
		when (jobService.exist(jobId)) {
			true -> cancelTheJob(jobId)
			false -> ResponseEntity.notFound().build()
		}
	} catch (e: Exception) {
		internalServerError("${e.javaClass.simpleName} happened at job cancel with jobId: $jobId", e)
	}

	private fun cancelTheJob(jobId: Int): ResponseEntity<Void> {
		jobService.cancel(jobId)
		return ResponseEntity.accepted().build()
	}


	@GetMapping
	override fun getAllJobStatuses(): ResponseEntity<List<Job>> = try {
		val result = jobService.getStatuses()
		when (result.isEmpty()) {
			true -> ResponseEntity.noContent().build()
			false -> ResponseEntity.ok(result)
		}
	} catch (e: Exception) {
		internalServerError("${e.javaClass.simpleName} happened at getAllJobStatuses", e)
	}

	@GetMapping("/{jobId}")
	override fun getJobStatus(@PathVariable jobId: Int): ResponseEntity<Job> = try {
		when (jobService.exist(jobId)) {
			true -> ResponseEntity.ok(jobService.getStatus(jobId))
			false -> ResponseEntity.notFound().build()
		}
	} catch (e: Exception) {
		internalServerError("${e.javaClass.simpleName} happened at getJobStatus with jobId: $jobId", e)
	}

	companion object {
		private val log: Logger = LoggerFactory.getLogger(JobResource::class.java)
		const val UNEXPECTED_ERROR = "Unexpected error happened"
		private fun <T> internalServerError(errorMessage: String, e: Exception): ResponseEntity<T> {
			log.debug(e.message, e)
			log.error(errorMessage)
			return ResponseEntity.internalServerError().build()
		}
	}

}
