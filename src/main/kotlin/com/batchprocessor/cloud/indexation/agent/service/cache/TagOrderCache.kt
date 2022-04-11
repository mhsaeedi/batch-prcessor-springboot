package com.batchprocessor.cloud.indexation.agent.service.cache

import com.batchprocessor.cloud.indexation.agent.shared.util.removeXmlComments
import com.batchprocessor.cloud.indexation.agent.repository.TagOrderEntity
import com.batchprocessor.cloud.indexation.agent.repository.TagOrderRepo
import com.batchprocessor.cloud.indexation.agent.service.data.Job
import com.batchprocessor.cloud.indexation.agent.service.data.Rule
import com.batchprocessor.cloud.indexation.agent.shared.util.xmlMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author : Momo
 * @since : 28.10.21, Thu
 *
 **/
@Service
class TagOrderCache @Autowired constructor(
	private val tagOrderRepo: TagOrderRepo,
) : Cache<Long, TagOrderEntity, LinkedHashSet<String>>() {

	override fun generateKey(value: TagOrderEntity): Long =
		value.tagId!!

	override fun generateValue(value: TagOrderEntity): LinkedHashSet<String> =
		xmlMapper.readValue(value.ruleBody?.removeXmlComments(), Rule::class.java).categoryUrlStructure?.tagType
			?.map { it.name }
			?.toCollection(LinkedHashSet()) ?: LinkedHashSet()

	override fun getAll(job: Job): List<TagOrderEntity> =
		tagOrderRepo.getAll(job.country)
}
