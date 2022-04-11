package com.batchprocessor.cloud.indexation.agent.web

import com.batchprocessor.cloud.indexation.agent.service.data.Job
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable

/**
 * @author : Momo
 * @since : 18.10.21, Mon
 *
 **/
@Tag(name = "Job", description = "Job api")
interface JobResourceApi {
	@Operation(
		summary = "Add a new job",
		description = "Registers a data job for request, no guarantee for immediate execution"
	)
	@ApiResponses(
		value = [
			ApiResponse(responseCode = "202", description = "If job is accepted"),
			ApiResponse(responseCode = "400", description = "Invalid parameter sent by client"),
			ApiResponse(responseCode = "409", description = "Reject job because it already exists"),
			ApiResponse(responseCode = "500", description = JobResource.UNEXPECTED_ERROR),
		]
	)
	fun offer(job: Job): ResponseEntity<Void>


	@Operation(
		summary = "Cancel a job",
		description = "Cancels a job, no guarantee for immediate cancel, or even cancel at some point"
	)
	@ApiResponses(
		value = [
			ApiResponse(responseCode = "202", description = "If canceling a job is accepted"),
			ApiResponse(responseCode = "404", description = "No job with sent id exists"),
			ApiResponse(responseCode = "500", description = JobResource.UNEXPECTED_ERROR),
		]
	)
	fun cancel(jobId: Int): ResponseEntity<Void>

	@Operation(
		summary = "Returns a list of status for running jobs",
		description = "Client may use this operation to get a complete list of statuses for running data collection jobs"
	)
	@ApiResponses(
		value = [
			ApiResponse(
				responseCode = "200", description = "Return status of existing jobs",
				content = [Content(mediaType = "application/json", array = ArraySchema(schema = Schema(implementation = Job::class)))]
			),
			ApiResponse(
				responseCode = "204", description = "If there are no jobs",
				content = [Content(examples = [ExampleObject(value = "")])]
			),
			ApiResponse(
				responseCode = "500", description = JobResource.UNEXPECTED_ERROR,
				content = [Content(examples = [ExampleObject(value = "")])]
			),
		]
	)
	fun getAllJobStatuses(): ResponseEntity<List<Job>>

	@Operation(
		summary = "Returns a list of status for running jobs",
		description = "Client may use this operation to get a complete list of statuses for running data collection jobs"
	)
	@ApiResponses(
		value = [
			ApiResponse(
				responseCode = "200", description = "Returns the job status",
				content = [Content(mediaType = "application/json", schema = Schema(implementation = Job::class))]
			),
			ApiResponse(
				responseCode = "404", description = "There is no job associated with sent id",
				content = [Content(examples = [ExampleObject(value = "")])]
			),
			ApiResponse(
				responseCode = "500", description = JobResource.UNEXPECTED_ERROR,
				content = [Content(examples = [ExampleObject(value = "")])]
			),
		]
	)
	fun getJobStatus(@PathVariable jobId: Int): ResponseEntity<Job>
}
