package com.batchprocessor.cloud.indexation.agent.shared.util

import aws.sdk.kotlin.runtime.auth.ProfileCredentialsProvider
import aws.sdk.kotlin.services.s3.S3Client
import aws.smithy.kotlin.runtime.content.ByteStream
import com.batchprocessor.cloud.indexation.agent.service.data.Batch
import com.batchprocessor.cloud.indexation.agent.service.data.Job
import com.batchprocessor.cloud.indexation.agent.shared.S3Config
import com.batchprocessor.cloud.indexation.agent.shared.constant.Country
import kotlinx.coroutines.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.File

/**
 * @author Momo
 * @since  12.11.2021
 */
@Service
class S3Util @Autowired constructor(
    s3Config: S3Config,
) {
    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
        private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    }

    private val bucketName = s3Config.bucketName
    private val s3Client = S3Client {
        region = s3Config.region
        credentialsProvider = ProfileCredentialsProvider("rabbits")
    }

    fun uploadBatchJob(batch: Batch): Job = with(batch) {
        val file = File("downloads/kappa.png").readBytes();
        val fileName = "${job.id}-${number + 1}-${ofBatchGroupWithSize}.png"
        val path = "${getPrefix(job.id, job.country)}/"
        return scope.launch {
            val byteStreamOfFile = ByteStream.fromBytes(file)
            val putObjectResponse = s3Client.putObject {
                bucket = bucketName
                key = "${path ?: ""}$fileName"
                body = byteStreamOfFile
            }
            log.info("Successfully uploaded the file with the etag: ${putObjectResponse.eTag}")
        }
    }

    private fun getPrefix(jobId: Int, country: Country): String {
        return "job-${jobId}-${country.name.lowercase()}"
    }

}
