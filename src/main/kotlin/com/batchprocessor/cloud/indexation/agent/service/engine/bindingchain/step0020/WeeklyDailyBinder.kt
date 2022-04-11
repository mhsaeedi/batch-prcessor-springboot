package com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.step0020

import com.batchprocessor.cloud.indexation.agent.shared.util.toWeeklyDaily
import com.batchprocessor.cloud.indexation.agent.service.data.Job
import com.batchprocessor.cloud.indexation.agent.service.data.Uri
import com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.Binder
import com.batchprocessor.cloud.indexation.agent.shared.constant.Condition
import com.batchprocessor.cloud.indexation.agent.shared.constant.WeeklyDaily
import com.batchprocessor.cloud.indexation.agent.shared.util.weeklyDailyMatcher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author : Mohammad <mohammad.saeedi@visual-meta.com>
 * @since : 11.10.21, Mon
 *
 **/
@Service
class WeeklyDailyBinder @Autowired constructor(
	private val itemCountBinder: ItemCountBinder,
) : Binder {

	override fun bind(job: Job, uri: Uri) =
		when (weeklyDailyMatcher.matches(uri.line.toWeeklyDaily())) {
			true ->
				WeeklyDaily.values()[uri.line.toWeeklyDaily().toInt()]
					.apply { uri.weeklyDaily = this }
					.run { itemCountBinder.bind(job, uri) }
			false ->
				uri.condition = Condition.MALFORMED_WEEKLY_DAILY
		}
}
