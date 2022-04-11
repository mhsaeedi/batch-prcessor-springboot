package com.batchprocessor.cloud.indexation.agent.service.cache

import com.batchprocessor.cloud.indexation.agent.shared.util.toSortedTagIds
import com.batchprocessor.cloud.indexation.agent.service.data.HighBounceRateTag
import com.batchprocessor.cloud.indexation.agent.service.data.Job
import com.batchprocessor.cloud.indexation.agent.shared.AgentConfig
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.*

/**
 * @author : Mohammad <mohammad.saeedi></mohammad.saeedi>@visual-meta.com>
 * @since : 26.10.21, Tue
 */
@Service
class HighBounceRateCache @Autowired constructor(
	private val agentConfig: AgentConfig,
) : Cache<String, String, String>() {

	override fun generateKey(value: String): String = value.toSortedTagIds()

	override fun generateValue(value: String): String = value.toSortedTagIds()

	override fun getAll(job: Job): List<String> = createEndPoint(job).let { endPoint ->
		try {
			restTemplate.getForEntity(endPoint, Array<HighBounceRateTag>::class.java).body
				?.mapNotNull { it.tagSet }?.map { it.toSortedTagIds() } ?: listOf()
		} catch (e: Exception) {
			log.error("${e::class.java.simpleName} happened while fetching high bounce rate tags")
			listOf()
		}
	}

	private fun createEndPoint(job: Job) = with(job) {
		StringBuilder().append("http://api.donald.").append(agentConfig.environment)
			.append(".vm/indexation-data/").append(country.name.lowercase())
			.append("/tagSet/organic-bounce-rate-35-days?minLandings=").append(minLandingKey)
			.append("&maxLandings=").append(maxLandingKey)
			.append("&minBounceRate=").append(minBounceRateKey)
			.append("&maxBounceRate=").append("1.1").toString()
	}

	companion object {
		private val log = LoggerFactory.getLogger(HighBounceRateCache::class.java)
		private val restTemplate = RestTemplate()

	}

}
