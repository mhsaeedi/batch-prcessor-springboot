package com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain

import com.batchprocessor.cloud.indexation.agent.service.data.Job
import com.batchprocessor.cloud.indexation.agent.service.data.Uri
import com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.step0010.LineValidationBinder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author : Momo
 * @since : 11.10.21, Mon
 *
 **/
@Service
class FirstUriBinder @Autowired constructor(
	private val lineValidationBinder: LineValidationBinder,
) : Binder {

	/**
	 * Does nothing, it is a marker and knows how to start binding
	 */
	override fun bind(job: Job, uri: Uri) =
		lineValidationBinder.bind(job, uri)
}

