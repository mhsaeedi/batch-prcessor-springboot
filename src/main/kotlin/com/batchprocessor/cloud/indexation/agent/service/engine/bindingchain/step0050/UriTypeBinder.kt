package com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.step0020

import com.batchprocessor.cloud.indexation.agent.shared.util.toStrippedUriType
import com.batchprocessor.cloud.indexation.agent.shared.util.toUriType
import com.batchprocessor.cloud.indexation.agent.service.data.Job
import com.batchprocessor.cloud.indexation.agent.service.data.Uri
import com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.Binder
import com.batchprocessor.cloud.indexation.agent.shared.constant.Condition
import com.batchprocessor.cloud.indexation.agent.shared.constant.UriType
import com.batchprocessor.cloud.indexation.agent.shared.util.uriTypeMatcher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author : Mohammad <mohammad.saeedi@visual-meta.com>
 * @since : 11.10.21, Mon
 *
 **/
@Service
class UriTypeBinder @Autowired constructor(
	private val tagsBinder: TagsBinder,
) : Binder {
	override fun bind(job: Job, uri: Uri) =
		when (uriTypeMatcher.matches(uri.line.toUriType())) {
			false ->
				uri.condition = Condition.MALFORMED_URL_TYPE
			true ->
				uri.line.toStrippedUriType().findUriType()
					.apply { uri.uriType = this }
					.run { tagsBinder.bind(job, uri) }
		}

	private fun String.findUriType() = when {
		one == this[UriType.CATEGORY.ordinal] -> UriType.CATEGORY
		isCombined(this) -> UriType.COMBINED
		one == this[UriType.STORE.ordinal] -> UriType.STORE
		one == this[UriType.SHOP.ordinal] -> UriType.SHOP
		one == this[UriType.SUB_MERCHANDISE.ordinal] -> UriType.SUB_MERCHANDISE
		one == this[UriType.MERCHANDISE.ordinal] -> UriType.MERCHANDISE
		one == this[UriType.SUB_SUB_SERIES.ordinal] -> UriType.SUB_SUB_SERIES
		one == this[UriType.SUB_SERIES.ordinal] -> UriType.SUB_SERIES
		one == this[UriType.SERIES.ordinal] -> UriType.SERIES
		one == this[UriType.BRAND.ordinal] -> UriType.BRAND
		else -> UriType.UNKNOWN
	}


	companion object {
		private const val one = '1'
		private fun isCombined(type: String) = listOf(
			type[UriType.BRAND.ordinal],
			type[UriType.MERCHANDISE.ordinal],
			type[UriType.SHOP.ordinal],
			type[UriType.STORE.ordinal]).filter { it == one }.size > 1
	}

}
