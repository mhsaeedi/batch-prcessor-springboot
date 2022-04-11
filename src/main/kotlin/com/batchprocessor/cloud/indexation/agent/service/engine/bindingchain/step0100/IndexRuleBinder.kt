package com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.step0100

import com.batchprocessor.cloud.indexation.agent.shared.util.removeXmlComments
import com.batchprocessor.cloud.indexation.agent.service.cache.RuleCache
import com.batchprocessor.cloud.indexation.agent.service.data.*
import com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.Binder
import com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.step0110.IndexableTagTypesRuleBinder
import com.batchprocessor.cloud.indexation.agent.shared.util.toSortedTagIds
import com.batchprocessor.cloud.indexation.agent.shared.util.xmlMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

/**
 * @author : Mohammad <mohammad.saeedi@visual-meta.com>
 * @since : 08.11.21, Mon
 *
 **/
@Service
class IndexRuleBinder @Autowired constructor(
	private val ruleCache: RuleCache,
	private val indexableTagTypesRuleBinder: IndexableTagTypesRuleBinder,
) : Binder {

	//fixme: this step is not working
	override fun bind(job: Job, uri: Uri) =
		uri.tagsIncludingHiddenParents.toCollection(TreeSet(TagComparator()))
			.run { lookForIndexRule(uri, this, job) }.also { indexableTagTypesRuleBinder.bind(job, uri) }

	private fun lookForIndexRule(uri: Uri, tags: TreeSet<Tag>, job: Job) {
		if (tags.isEmpty()) return
		val isFirstLook = tags.size == uri.tagsIncludingHiddenParents.size
		val ruleData = ruleCache.get(job, tags.toSortedTagIds())
		tags.pollLast() // make tags ready for next possible recursive look
		if (ruleData?.body.isNullOrBlank()) {
			lookForIndexRule(uri, tags, job) // No rule? look for next parent
			return
		}

		// get index rule
		val index = xmlMapper.readValue(ruleData!!.body.removeXmlComments(), Rule::class.java).index
		val value = index?.value
		val inheritable = index?.inheritable
		when {
			null == value && null == inheritable -> lookForIndexRule(uri, tags, job)
			!isFirstLook && true != inheritable -> lookForIndexRule(uri, tags, job)
			else -> IndexRule(ruleData.id, value == true, inheritable == true).let { uri.indexRule = it }
		}
	}


}


