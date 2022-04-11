package com.batchprocessor.cloud.indexation.agent.service.data

import com.batchprocessor.cloud.indexation.agent.shared.constant.*
import com.batchprocessor.cloud.indexation.agent.shared.constant.TagType
import java.util.*

/**
 * @author : Mohammad <mohammad.saeedi@visual-meta.com>
 * @since : 04.11.21, Thu
 *
 **/
data class Batch(
	val job: Job,
	val number: Int,
	val uris: List<Uri>,
	val ofBatchGroupWithSize: Int,
)

data class Job(
	val id: Int,
	val country: Country,
	val desiredSizeOfEachBatch: Int,
	val minLandingKey: Int,
	val maxLandingKey: Int,
	val minBounceRateKey: Double,
)

data class Tag(
	var id: Long,
	var displayName: String?,
	var type: TagType,
	var parentId: Long?,
	var isRedirect: Boolean,
	var notToDisplay: Boolean,
	)

data class IndexableTagTypesRule(
	var id: Long,
	var tagTypes: HashSet<String>,
)

data class Uri(
	val tags: TreeSet<Tag> = TreeSet(TagComparator()),
	val tagsIncludingHiddenParents: TreeSet<Tag> = TreeSet(TagComparator()),
	var condition: Condition = Condition.HEALTHY,
	var isHighBounceRate: Boolean = false,
	var weeklyDaily: WeeklyDaily? = null,
	var itemCount: Int = 0,
	var shopCount: Int = 0,
	var uriType: UriType = UriType.UNKNOWN,
	val line: String,
	var indexRule: IndexRule? = null,
	var indexableTagTypesRule: IndexableTagTypesRule? = null,
	var windowsRuleId: Long? = null,
	var contemporaryIndexData: ContemporaryIndexData? = null,
)

data class IndexRule(
	var id: Long,
	var value: Boolean,
	var inheritable: Boolean,
)

class TagComparator : Comparator<Tag> {
	override fun compare(firstTag: Tag, secondTag: Tag) = firstTag.type.persistenceID - secondTag.type.persistenceID
}

data class HighBounceRateTag(
	var tagSet: String? = null,
	var url: String? = null,
	var totalOrganicLandings: Int = 0,
	var bounceRate: Double = 0.0,
	var threshold: Double = 0.0,
	var earnings: Double = 0.0,
)

data class RuleData(
	var id: Long,
	var tags: String,
	var body: String,
)

data class ContemporaryIndexData(
	var daysOnIndex: Long,
	var failedDays: Long,
	var tagSet: String,
)
