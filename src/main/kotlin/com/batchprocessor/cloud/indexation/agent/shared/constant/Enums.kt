package com.batchprocessor.cloud.indexation.agent.shared.constant

/**
 * @author : Momo
 * @since : 25.10.21, Mon
 *
 **/
enum class WeeklyDaily {
	WEEKLY, DAILY, WEEKLY_PROTECTED
}

enum class Country {
	DE
}

enum class LineSegment {

	/**
	 * IMPORTANT NOTE
	 * Order is important
	 * We use order finding corresponding segment in daily url list
	 */
	TAGS,
	WEEKLY_DAILY,
	ITEM_COUNT,
	SHOP_COUNT,
	URI_TYPE,
}

enum class UriType {
	/**
	 * IMPORTANT NOTE
	 * Order is important
	 * We use order finding corresponding segment in daily url list
	 */
	CATEGORY,
	BRAND,
	SERIES,
	SUB_SERIES,
	SUB_SUB_SERIES,
	MERCHANDISE,
	SUB_MERCHANDISE,
	SHOP,
	STORE,
	COMBINED,
	UNKNOWN,
}

enum class Condition {
	HEALTHY,
	MALFORMED_LINE,
	MALFORMED_WEEKLY_DAILY,
	MALFORMED_ITEM_COUNT,
	MALFORMED_SHOP_COUNT,
	MALFORMED_URL_TYPE,
	MALFORMED_TAGS,
	CONTAINS_NULL_TAGS,
	INVALID_CATEGORY_TAG_ORDER,
}
