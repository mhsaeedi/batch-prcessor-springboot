package com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.step0020

import com.batchprocessor.cloud.indexation.agent.shared.util.COMMA
import com.batchprocessor.cloud.indexation.agent.shared.util.toTags
import com.batchprocessor.cloud.indexation.agent.service.cache.TagCache
import com.batchprocessor.cloud.indexation.agent.service.data.Job
import com.batchprocessor.cloud.indexation.agent.service.data.Uri
import com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.Binder
import com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.step0070.TagTypeOrderBinder
import com.batchprocessor.cloud.indexation.agent.shared.constant.Condition
import com.batchprocessor.cloud.indexation.agent.shared.util.commaSeparateNumbersMatcher
import com.batchprocessor.cloud.indexation.agent.shared.util.toData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author : Momo
 * @since : 11.10.21, Mon
 *
 **/
@Service
class TagsBinder @Autowired constructor(
	private val tagTypeOrderBinder: TagTypeOrderBinder,
	private val tagCache: TagCache,
) : Binder {
	override fun bind(job: Job, uri: Uri) =
		when (commaSeparateNumbersMatcher.matches(uri.line.toTags())) {
			false -> uri.condition = Condition.MALFORMED_TAGS
			true -> uri.line.toTags().split(COMMA).map { tagCache.get(job, it.toLong()) }.let { tags ->
				when {
					null in tags -> uri.condition = Condition.CONTAINS_NULL_TAGS
					else -> tags.map { it!!.toData() }.apply { uri.tags.addAll(this) }.run { tagTypeOrderBinder.bind(job, uri) }
				}
			}
		}


}
