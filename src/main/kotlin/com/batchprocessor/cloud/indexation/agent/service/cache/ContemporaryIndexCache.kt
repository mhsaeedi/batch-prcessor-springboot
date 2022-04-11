package com.batchprocessor.cloud.indexation.agent.service.cache

import com.batchprocessor.cloud.indexation.agent.shared.util.toSortedTagIds
import com.batchprocessor.cloud.indexation.agent.repository.ContemporaryIndexEntity
import com.batchprocessor.cloud.indexation.agent.repository.ContemporaryIndexRepo
import com.batchprocessor.cloud.indexation.agent.service.data.ContemporaryIndexData
import com.batchprocessor.cloud.indexation.agent.service.data.Job
import com.batchprocessor.cloud.indexation.agent.shared.util.toData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author : Momo
 * @since : 09.11.21, Tue
 *
 **/
@Service
class ContemporaryIndexCache @Autowired constructor(
	private val contemporaryIndexRepo: ContemporaryIndexRepo,
) : Cache<String, ContemporaryIndexEntity, ContemporaryIndexData>() {

	override fun generateKey(value: ContemporaryIndexEntity) =
		value.tagSet!!.toSortedTagIds()

	override fun generateValue(value: ContemporaryIndexEntity) =
		value.toData()

	override fun getAll(job: Job): List<ContemporaryIndexEntity> =
		contemporaryIndexRepo.getAll(job.country)


}
