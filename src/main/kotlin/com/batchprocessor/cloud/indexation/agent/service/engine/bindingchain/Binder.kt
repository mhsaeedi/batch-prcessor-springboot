package com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain

import com.batchprocessor.cloud.indexation.agent.service.data.Job
import com.batchprocessor.cloud.indexation.agent.service.data.Uri

/**
 * @author : Mohammad <mohammad.saeedi@visual-meta.com>
 * @since : 11.10.21, Mon
 *
 **/
interface Binder {
	fun bind(job: Job, uri: Uri)
}
