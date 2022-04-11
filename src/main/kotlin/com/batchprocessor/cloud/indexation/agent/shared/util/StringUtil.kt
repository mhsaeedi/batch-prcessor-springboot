package com.batchprocessor.cloud.indexation.agent.shared.util

import com.batchprocessor.cloud.indexation.agent.shared.constant.LineSegment

/**
 * @author : Momo
 * @since : 22.10.21, Fri
 *
 **/
const val SEMICOLON = ";"
const val COMMA = ","


fun String.toWeeklyDaily() = when (isMalformedLine()) {
	true -> ""
	false -> trimAndSplit()[LineSegment.WEEKLY_DAILY.ordinal]
}

fun String.toItemCount() = when (isMalformedLine()) {
	true -> ""
	false -> trimAndSplit()[LineSegment.ITEM_COUNT.ordinal]
}

fun String.toShopCount() = when (isMalformedLine()) {
	true -> ""
	false -> trimAndSplit()[LineSegment.SHOP_COUNT.ordinal]
}

fun String.toUriType() = when (isMalformedLine()) {
	true -> ""
	false -> trimAndSplit()[LineSegment.URI_TYPE.ordinal]
}

fun String.toTags() = when (isMalformedLine()) {
	true -> ""
	false -> trimAndSplit()[LineSegment.TAGS.ordinal]
}

private fun String.trimAndSplit() =
	replace(semicolonsInTheEndMatcher, "").replace(whiteSpacesMatcher, "").split(SEMICOLON)

private fun String.isMalformedLine() =
	trimAndSplit().size != LineSegment.values().size

fun String.toStrippedUriType() =
	toUriType().replace(nonNumericsMatcher, "")

fun String.hasRequiredSegments() =
	isBlank().not().and(trimAndSplit().size == LineSegment.values().size)

fun String.removeXmlComments() =
	replace(xmlCommentMatcher, "").trim()

fun String.toSortedTagIds() =
	split(COMMA).map { it.trim().toLong() }.sorted().joinToString()

fun String.removeWhiteSpaces() =
	replace(whiteSpacesMatcher, "")
