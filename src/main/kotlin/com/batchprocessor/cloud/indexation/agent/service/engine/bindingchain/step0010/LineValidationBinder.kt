package com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.step0010

import com.batchprocessor.cloud.indexation.agent.shared.util.hasRequiredSegments
import com.batchprocessor.cloud.indexation.agent.service.data.Job
import com.batchprocessor.cloud.indexation.agent.service.data.Uri
import com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.Binder
import com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.step0020.WeeklyDailyBinder
import com.batchprocessor.cloud.indexation.agent.shared.constant.Condition
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author : Mohammad <mohammad.saeedi@visual-meta.com>
 * @since : 11.10.21, Mon
 *
 **/
@Service
class LineValidationBinder @Autowired constructor(
	private val weeklyDailyBinder: WeeklyDailyBinder,
) : Binder {
	override fun bind(job: Job, uri: Uri) =
		when (uri.line.hasRequiredSegments()) {
			true -> weeklyDailyBinder.bind(job, uri)
			false -> uri.condition = Condition.MALFORMED_LINE
		}

}
