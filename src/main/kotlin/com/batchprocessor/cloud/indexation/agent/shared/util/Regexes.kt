package com.batchprocessor.cloud.indexation.agent.shared.util

import com.batchprocessor.cloud.indexation.agent.shared.constant.WeeklyDaily

/**
 * @author : Momo
 * @since : 25.10.21, Mon
 *
 **/
val anyNumericMatcher = "^[1-9]\\d*\$".toRegex() // any number
val weeklyDailyMatcher = String.format("^[0-%s]\$", WeeklyDaily.values().size - 1).toRegex() // [0,size-1]
val uriTypeMatcher = "^(?=.*1)[01](,[01]){8}\$".toRegex() // 1,0,1,0,1,0,1,0,1 -> 9 digit with "," as separator and at least one "1"
val semicolonsInTheEndMatcher = ";+$".toRegex()
val nonNumericsMatcher = "[^\\d.]".toRegex()
val whiteSpacesMatcher = "\\s".toRegex()
val commaSeparateNumbersMatcher = "^\\d+([,]\\d+)*\$".toRegex()
val xmlCommentMatcher = "<!--[\\s\\S]*?-->".toRegex()

