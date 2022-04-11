package com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.step0130

import com.batchprocessor.cloud.indexation.agent.service.cache.HighBounceRateCache
import com.batchprocessor.cloud.indexation.agent.service.data.Job
import com.batchprocessor.cloud.indexation.agent.service.data.Uri
import com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.Binder
import com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.step0140.ContemporaryIndexBinder
import com.batchprocessor.cloud.indexation.agent.shared.util.toSortedTagIds
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author : Mohammad <mohammad.saeedi@visual-meta.com>
 * @since : 09.11.21, Tue
 *
 **/
@Service
class HighBounceRateBinder @Autowired constructor(
	private val highBounceRateCache: HighBounceRateCache,
	private val contemporaryIndexBinder: ContemporaryIndexBinder,
) : Binder {
	override fun bind(job: Job, uri: Uri) =
		(null != highBounceRateCache.get(job, uri.tags.toSortedTagIds()))
			.let { uri.isHighBounceRate = it }.also { contemporaryIndexBinder.bind(job, uri) }

}
