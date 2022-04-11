package com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.step0070

import com.batchprocessor.cloud.indexation.agent.service.cache.TagOrderCache
import com.batchprocessor.cloud.indexation.agent.service.data.Job
import com.batchprocessor.cloud.indexation.agent.service.data.Uri
import com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.Binder
import com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.step0080.HiddenTagsBinder
import com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.step0090.RedirectTagBinder
import com.batchprocessor.cloud.indexation.agent.shared.constant.Condition
import com.batchprocessor.cloud.indexation.agent.shared.constant.UriType
import com.batchprocessor.cloud.indexation.agent.shared.util.toCategoryTag
import com.batchprocessor.cloud.indexation.agent.shared.util.toSetOfTagTypesExceptCategory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author : Momo
 * @since : 11.10.21, Mon
 *
 **/
@Service
class TagTypeOrderBinder @Autowired constructor(
	private val hiddenTagsBinder: HiddenTagsBinder,
	private val tagOrderCache: TagOrderCache,
	private val redirectTagBinder: RedirectTagBinder,
) : Binder {

	override fun bind(job: Job, uri: Uri) =
		when {
			UriType.CATEGORY != uri.uriType -> redirectTagBinder.bind(job, uri)
			typeOrderIsValid(job, uri) -> hiddenTagsBinder.bind(job, uri)
			else -> uri.condition = Condition.INVALID_CATEGORY_TAG_ORDER
		}

	private fun typeOrderIsValid(job: Job, uri: Uri): Boolean =
		uri.toCategoryTag()?.run { tagOrderCache.get(job, this.id) }
			.run { this?.containsAll(uri.toSetOfTagTypesExceptCategory()) == true }


}
