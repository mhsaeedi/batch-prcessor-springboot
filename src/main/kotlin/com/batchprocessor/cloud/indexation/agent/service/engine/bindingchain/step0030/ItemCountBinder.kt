package com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.step0020

import com.batchprocessor.cloud.indexation.agent.shared.util.toItemCount
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
class ItemCountBinder @Autowired constructor(
	private val shopCountBinder: ShopCountBinder,
) : Binder {
	override fun bind(job: Job, uri: Uri) =
		when (anyNumericMatcher.matches(uri.line.toItemCount())) {
			true ->
				uri.line.toItemCount().toInt()
					.apply { uri.itemCount = this }
					.run { shopCountBinder.bind(job, uri) }
			false ->
				uri.condition = Condition.MALFORMED_ITEM_COUNT
		}
}
