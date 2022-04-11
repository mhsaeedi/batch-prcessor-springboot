package com.batchprocessor.cloud.indexation.agent.repository

import com.batchprocessor.cloud.indexation.agent.shared.constant.TagType

/**
 * @author : Mohammad <mohammad.saeedi@visual-meta.com>
 * @since : 04.11.21, Thu
 *
 **/
data class RuleEntity(
	var id: Long? = null,
	var tags: String? = null,
	var body: String? = null,
)

data class TagEntity(
	var id: Long? = null,
	var displayName: String? = null,
	var type: TagType? = null,
	var parentId: Long? = null,
	var auxData: String? = null,
	var notToDisplay: Boolean? = null,
)

data class TagOrderEntity(
	var tagId: Long? = null,
	var ruleBody: String? = null,
)

data class ContemporaryIndexEntity(
	var daysOnIndex: Long? = null,
	var failedDays: Long? = null,
	var tagSet: String? = null,
)
