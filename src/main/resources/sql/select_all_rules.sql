SELECT r.id,
       GROUP_CONCAT(tag_id ORDER BY tag_id) AS tags,
       r.body
FROM rule_tag rt
	     INNER JOIN rule r ON r.id = rt.rule_id
GROUP BY rule_id
