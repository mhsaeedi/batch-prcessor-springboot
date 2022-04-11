package com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.step0020

import com.batchprocessor.cloud.indexation.agent.shared.util.toShopCount
import com.batchprocessor.cloud.indexation.agent.service.data.Job
import com.batchprocessor.cloud.indexation.agent.service.data.Uri
import com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.Binder
import com.batchprocessor.cloud.indexation.agent.shared.constant.Condition
import com.batchprocessor.cloud.indexation.agent.shared.util.anyNumericMatcher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author : Momo
 * @since : 11.10.21, Mon
 *
 **/
@Service
class ShopCountBinder @Autowired constructor(
	private val uriTypeBinder: UriTypeBinder,
) : Binder {
	override fun bind(job: Job, uri: Uri) =
		when (anyNumericMatcher.matches(uri.line.toShopCount())) {
			true ->
				uri.line.toShopCount().toInt()
					.apply { uri.shopCount = this }
					.run { uriTypeBinder.bind(job, uri) }
			false ->
				uri.condition = Condition.MALFORMED_SHOP_COUNT
		}
}
