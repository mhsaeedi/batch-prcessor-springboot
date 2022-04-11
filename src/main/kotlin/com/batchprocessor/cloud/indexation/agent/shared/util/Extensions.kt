package com.batchprocessor.cloud.indexation.agent.shared.util


import com.batchprocessor.cloud.indexation.agent.repository.ContemporaryIndexEntity
import com.batchprocessor.cloud.indexation.agent.repository.RuleEntity
import com.batchprocessor.cloud.indexation.agent.repository.TagEntity
import com.batchprocessor.cloud.indexation.agent.service.data.ContemporaryIndexData
import com.batchprocessor.cloud.indexation.agent.service.data.RuleData
import com.batchprocessor.cloud.indexation.agent.service.data.Tag
import com.batchprocessor.cloud.indexation.agent.service.data.Uri
import com.batchprocessor.cloud.indexation.agent.shared.constant.TagType
import com.batchprocessor.cloud.indexation.agent.shared.constant.UriType
import java.util.*
import java.util.stream.Collectors.toSet

/**
 * @author : Momo
 * @since : 01.11.21, Mon
 *
 **/
fun Uri.toDeepestStyleTag(): Tag? = tags
	.filter { TagType.getStyleTagTypesInHierarchicalOrderAsc().toSet().contains(it.type) }.maxByOrNull { it.type.ordinal }

fun Uri.toSetOfTagTypesExceptCategory(): Set<String> = tags.asSequence().filter { toCategoryTag()?.type != it.type }.map { tag -> tag.type.name }.toSet()

fun Uri.toCategoryTag(): Tag? = tags.firstOrNull { TagType.CATEGORY == it.type }

fun TreeSet<Tag>.toSortedTagIds() = map { it.id }.joinToString()

fun UriType.isCategory() = UriType.CATEGORY == this

fun TagEntity.toData(): Tag = Tag(
	id = id!!,
	displayName = displayName,
	type = type!!,
	parentId = parentId,
	notToDisplay = notToDisplay!!,
	isRedirect = false,
)

fun RuleEntity.toData() = RuleData(
	id = id!!,
	tags = tags ?: "",
	body = body ?: "",
)


fun ContemporaryIndexEntity.toData() = ContemporaryIndexData(
	failedDays = failedDays!!,
	daysOnIndex = daysOnIndex!!,
	tagSet = tagSet!!
)
