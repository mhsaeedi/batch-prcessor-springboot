package com.batchprocessor.cloud.indexation.agent.service.cache

import com.batchprocessor.cloud.indexation.agent.repository.TagEntity
import com.batchprocessor.cloud.indexation.agent.repository.TagRepo
import com.batchprocessor.cloud.indexation.agent.service.data.Job
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author : Momo
 * @since : 26.10.21, Tue
 */
@Service
class TagCache @Autowired constructor(
	private val tagRepo: TagRepo,
) : Cache<Long, TagEntity, TagEntity>() {

	override fun generateKey(value: TagEntity): Long =
		value.id!!

	override fun generateValue(value: TagEntity): TagEntity =
		value

	override fun getAll(job: Job): List<TagEntity> =
		tagRepo.getAll(job.country)


}
