package com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.step0110

import com.batchprocessor.cloud.indexation.agent.shared.util.removeXmlComments
import com.batchprocessor.cloud.indexation.agent.service.cache.RuleCache
import com.batchprocessor.cloud.indexation.agent.service.data.*
import com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.Binder
import com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.step0120.WindowsBinder
import com.batchprocessor.cloud.indexation.agent.shared.util.toSortedTagIds
import com.batchprocessor.cloud.indexation.agent.shared.util.xmlMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

/**
 * @author : Mohammad <mohammad.saeedi></mohammad.saeedi>@visual-meta.com>
 * @since : 09.11.21, Tue
 */
@Service
class IndexableTagTypesRuleBinder @Autowired constructor(
	private val ruleCache: RuleCache,
	private val windowsBinder: WindowsBinder,
) : Binder {
	// fixme: this step is not working
	override fun bind(job: Job, uri: Uri) =
		uri.tagsIncludingHiddenParents.toCollection(TreeSet(TagComparator()))
			.run { lookForIndexableTagTypesRule(uri, this, job) }.also { windowsBinder.bind(job, uri) }


	private fun lookForIndexableTagTypesRule(uri: Uri, tags: TreeSet<Tag>, job: Job) {
		if (tags.isEmpty()) return
		val isFirstLook = tags.size == uri.tagsIncludingHiddenParents.size
		val ruleData = ruleCache.get(job, tags.toSortedTagIds())
		tags.pollLast() // make tags ready for next possible recursive look
		if (ruleData?.body.isNullOrBlank()) {
			lookForIndexableTagTypesRule(uri, tags, job) // No rule? look for next parent
			return
		}

		// get indexableTagTypes rule
		val indexableTagTypes = xmlMapper.readValue(ruleData!!.body.removeXmlComments(), Rule::class.java).indexableTagTypes
		val tagTypes = indexableTagTypes?.tagTypes
		val inheritable = indexableTagTypes?.inheritable ?: true
		when {
			tagTypes.isNullOrEmpty() -> lookForIndexableTagTypesRule(uri, tags, job)
			!isFirstLook && !inheritable -> lookForIndexableTagTypesRule(uri, tags, job)
			else -> {
				IndexableTagTypesRule(ruleData.id, tagTypes.toHashSet()).let { uri.indexableTagTypesRule = it }
			}
		}
	}

}

