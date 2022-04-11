package com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.step0090

import com.batchprocessor.cloud.indexation.agent.service.cache.TagCache
import com.batchprocessor.cloud.indexation.agent.service.data.Job
import com.batchprocessor.cloud.indexation.agent.service.data.Tag
import com.batchprocessor.cloud.indexation.agent.service.data.Uri
import com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.Binder
import com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.step0100.IndexRuleBinder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author : Momo
 * @since : 11.10.21, Mon
 *
 **/
@Service
class RedirectTagBinder @Autowired constructor(
	private val tagCache: TagCache,
	private val indexRuleBind: IndexRuleBinder,
) : Binder {

	override fun bind(job: Job, uri: Uri) =
		uri.tags.forEach { setIsRedirect(job, it) }.also { indexRuleBind.bind(job, uri) }

	private fun setIsRedirect(job: Job, tag: Tag) =
		tagCache.get(job, tag.id)!!
			.run { this.auxData?.trim()?.lowercase()?.startsWith(replaceTag) == true }.also { tag.isRedirect = it }


	companion object {
		private const val replaceTag = "<replace>"
	}
}
