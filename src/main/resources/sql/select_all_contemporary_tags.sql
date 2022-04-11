SELECT tsi.tag_set                                      AS tagSet,
       tsi.days_on_index                                AS daysOnIndex,
       tsi.old_guard_protection_consecutive_failed_days AS failedDays
FROM tag_set_indexation tsi
