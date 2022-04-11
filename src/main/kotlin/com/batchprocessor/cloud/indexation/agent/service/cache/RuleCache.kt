package com.batchprocessor.cloud.indexation.agent.service.cache

import com.batchprocessor.cloud.indexation.agent.repository.RuleEntity
import com.batchprocessor.cloud.indexation.agent.repository.RuleRepo
import com.batchprocessor.cloud.indexation.agent.service.data.Job
import com.batchprocessor.cloud.indexation.agent.service.data.RuleData
import com.batchprocessor.cloud.indexation.agent.shared.util.toData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author : Momo
 * @since : 26.10.21, Tue
 */
@Service
class RuleCache @Autowired constructor(
	private val ruleRepo: RuleRepo,
) : Cache<String, RuleEntity, RuleData>() {

	override fun generateKey(value: RuleEntity): String =
		(value.tags ?: "").trim()

	override fun generateValue(value: RuleEntity): RuleData =
		value.toData()

	override fun getAll(job: Job): List<RuleEntity> =
		ruleRepo.getAll(job.country)


}
