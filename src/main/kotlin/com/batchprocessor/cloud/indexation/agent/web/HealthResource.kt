package com.batchprocessor.cloud.indexation.agent.web

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author : Mohammad <mohammad.saeedi@visual-meta.com>
 * @since : 03.09.21, Fri
 *
 **/
@RestController
@RequestMapping("/health")
@Tag(name = "Health", description = "Health check operations")
class HealthResource {

	@Operation(summary = "Check if service is alive", description = "Returns alive!!")
	@ApiResponse(
		responseCode = "200", description = "If service is alive.",
		content = [
			Content(
				mediaType = "String",
				examples = [ExampleObject(value = "alive!!")]
			)
		]
	)
	@GetMapping("/liveness")
	fun getLiveness() = ResponseEntity.ok("alive!!")


	@Operation(summary = "Check if service is ready", description = "Returns ready!!")
	@ApiResponse(
		responseCode = "200", description = "If service is ready.",
		content = [
			Content(
				mediaType = "String",
				examples = [ExampleObject(value = "ready!!")]
			)
		]
	)
	@GetMapping("/readiness")
	fun getReadiness() = ResponseEntity.ok("ready!!")
}
