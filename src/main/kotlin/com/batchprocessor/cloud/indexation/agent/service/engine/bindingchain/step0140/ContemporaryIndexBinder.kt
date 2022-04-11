package com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.step0140

import com.batchprocessor.cloud.indexation.agent.service.cache.ContemporaryIndexCache
import com.batchprocessor.cloud.indexation.agent.service.data.Job
import com.batchprocessor.cloud.indexation.agent.service.data.Uri
import com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.Binder
import com.batchprocessor.cloud.indexation.agent.shared.util.toSortedTagIds
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author : Mohammad <mohammad.saeedi@visual-meta.com>
 * @since : 09.11.21, Tue
 *
 **/
@Service
class ContemporaryIndexBinder @Autowired constructor(
	private val contemporaryIndexCache: ContemporaryIndexCache,
) : Binder {
	override fun bind(job: Job, uri: Uri) =
		contemporaryIndexCache.get(job, uri.tags.toSortedTagIds()).let { uri.contemporaryIndexData = it }

}
