SELECT t.id AS tag_id, r.body AS rule_body
FROM rule r
	     INNER JOIN rule_tag rt ON r.id = rt.rule_id
	     INNER JOIN tag t ON t.id = rt.tag_id
WHERE r.body LIKE '%CategoryUrlStructure%'
