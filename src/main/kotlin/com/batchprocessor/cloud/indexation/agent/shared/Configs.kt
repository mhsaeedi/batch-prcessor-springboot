package com.batchprocessor.cloud.indexation.agent.shared

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * @author : Mohammad <mohammad.saeedi@visual-meta.com>
 * @since : 12.11.21, Fri
 *
 **/
@Component
@ConfigurationProperties(prefix = "agent", ignoreUnknownFields = false)
class AgentConfig {
	var feedLocation: String? = null
	var environment:String? = null
}

@Component
@ConfigurationProperties(prefix = "vm.database", ignoreUnknownFields = false)
class VmDatabaseConfig {
	var connectionUrl = ""
	var username = ""
	var password = ""
	var driverClassName = ""
}

@Component
@ConfigurationProperties(prefix = "s3", ignoreUnknownFields = false)
class S3Config {
    var bucketName: String = "rabbits-indexation"
    var region: String = "eu-central-1"
}
