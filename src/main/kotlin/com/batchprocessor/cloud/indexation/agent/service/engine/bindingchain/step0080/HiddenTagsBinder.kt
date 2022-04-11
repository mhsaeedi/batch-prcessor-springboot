package com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.step0080

import com.batchprocessor.cloud.indexation.agent.service.cache.TagCache
import com.batchprocessor.cloud.indexation.agent.service.data.Job
import com.batchprocessor.cloud.indexation.agent.service.data.Tag
import com.batchprocessor.cloud.indexation.agent.service.data.Uri
import com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.Binder
import com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.step0090.RedirectTagBinder
import com.batchprocessor.cloud.indexation.agent.shared.constant.TagType
import com.batchprocessor.cloud.indexation.agent.shared.util.isCategory
import com.batchprocessor.cloud.indexation.agent.shared.util.toData
import com.batchprocessor.cloud.indexation.agent.shared.util.toDeepestStyleTag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author : Momo
 * @since : 11.10.21, Mon
 *
 **/
@Service
class HiddenTagsBinder @Autowired constructor(
	private val tagCache: TagCache,
	private val redirectTagBinder: RedirectTagBinder,
) : Binder {

	override fun bind(job: Job, uri: Uri) {
		if (uri.uriType.isCategory()) addHiddenTags(job, uri)
		redirectTagBinder.bind(job, uri)
	}

	private fun addHiddenTags(job: Job, uri: Uri) =
		uri.tagsIncludingHiddenParents.addAll(uri.tags)
			.also {
				addHiddenStyleTags(uri, uri.toDeepestStyleTag(), job)
			}.also {
				addHiddenBrandTags(uri, job)
			}


	private fun addHiddenBrandTags(uri: Uri, job: Job) =
		TagType.BRAND.childrenDescending.asSequence()
			.mapNotNull { brandType ->
				uri.tags.find { brandType == it.type }
			}
			.filter { brandTag ->
				null == uri.tags.firstOrNull { it.type == brandTag.type.parent }
			}.mapNotNull { it.parentId }
			.mapNotNull { tagCache.get(job, it) }
			.forEach {
				uri.tagsIncludingHiddenParents.add(it.toData())
			}


	private fun addHiddenStyleTags(uri: Uri, tag: Tag?, job: Job, counter: Int = 0) {
		if (tag?.parentId == null || TagType.STRUCTURAL == tag.type || counter >= 17) return

		tagCache.get(job, tag.parentId!!)?.run { toData() }.apply { uri.tagsIncludingHiddenParents.add(this!!) }
			.run { addHiddenStyleTags(uri, this, job, counter + 1) }
	}


}
