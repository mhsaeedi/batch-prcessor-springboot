package com.batchprocessor.cloud.indexation.agent

import com.batchprocessor.cloud.indexation.agent.shared.constant.LineSegment
import com.batchprocessor.cloud.indexation.agent.shared.util.whiteSpacesMatcher

/**
 * @author : Momo
 * @since : 25.10.21, Mon
 *
 **/
fun buildLine(replacement: String, segment: LineSegment): String =
	LineSegment.values().joinToString(";", postfix = ";").replace(whiteSpacesMatcher, "").replace(segment.name, replacement)
