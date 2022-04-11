package com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.step0120

import com.batchprocessor.cloud.indexation.agent.shared.util.removeWhiteSpaces
import com.batchprocessor.cloud.indexation.agent.shared.util.removeXmlComments
import com.batchprocessor.cloud.indexation.agent.service.cache.RuleCache
import com.batchprocessor.cloud.indexation.agent.service.data.Job
import com.batchprocessor.cloud.indexation.agent.service.data.Tag
import com.batchprocessor.cloud.indexation.agent.service.data.TagComparator
import com.batchprocessor.cloud.indexation.agent.service.data.Uri
import com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.Binder
import com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.step0130.HighBounceRateBinder
import com.batchprocessor.cloud.indexation.agent.shared.util.toSortedTagIds
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

/**
 * @author : Mohammad <mohammad.saeedi@visual-meta.com>
 * @since : 09.11.21, Tue
 *
 **/
@Service
class WindowsBinder @Autowired constructor(
	private val ruleCache: RuleCache,
	private val highBounceRateBinder: HighBounceRateBinder,
) : Binder {

	override fun bind(job: Job, uri: Uri) =
		uri.tagsIncludingHiddenParents.toCollection(TreeSet(TagComparator()))
			.run { lookForWindowRule(uri, this, job) }.also { highBounceRateBinder.bind(job, uri) }

	private fun lookForWindowRule(uri: Uri, tags: TreeSet<Tag>, job: Job) = ruleCache.get(job, tags.toSortedTagIds()).run {
		when (this?.body?.removeXmlComments()?.removeWhiteSpaces()?.contains(windows)) {
			true -> uri.windowsRuleId = this.id
		}
	}

	companion object {
		private const val windows = "<windows"
	}


}

