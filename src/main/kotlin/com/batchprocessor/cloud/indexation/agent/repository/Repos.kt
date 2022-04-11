package com.batchprocessor.cloud.indexation.agent.repository

import com.batchprocessor.cloud.indexation.agent.repository.Queries.queryContemporaryTags
import com.batchprocessor.cloud.indexation.agent.repository.Queries.queryRules
import com.batchprocessor.cloud.indexation.agent.repository.Queries.queryTagOrder
import com.batchprocessor.cloud.indexation.agent.repository.Queries.queryTags
import com.batchprocessor.cloud.indexation.agent.shared.constant.Country
import com.batchprocessor.cloud.indexation.agent.shared.util.read
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

/**
 * @author : Mohammad <mohammad.saeedi@visual-meta.com>
 * @since : 08.11.21, Mon
 *
 **/
private object Queries {
	val queryTags = read("/sql/select_all_tags.sql")
	val queryRules = read("/sql/select_all_rules.sql")
	val queryTagOrder = read("/sql/select_category_url_structures.sql")
	val queryContemporaryTags = read("/sql/select_all_contemporary_tags.sql")

}

@Repository
class RuleRepo @Autowired constructor(
	private val dataSourcePool: DataSourcePool,
) {
	fun getAll(country: Country): List<RuleEntity> =
		dataSourcePool.getDataSource(country)?.let {
			JdbcTemplate(it).query(queryRules, BeanPropertyRowMapper(RuleEntity::class.java))
		} ?: listOf()
}

@Repository
class TagOrderRepo @Autowired constructor(
	private val dataSourcePool: DataSourcePool,
) {

	fun getAll(country: Country): List<TagOrderEntity> =
		dataSourcePool.getDataSource(country)?.let {
			JdbcTemplate(it).query(queryTagOrder, BeanPropertyRowMapper(TagOrderEntity::class.java))
		} ?: listOf()
}

@Repository
class TagRepo @Autowired constructor(
	private val dataSourcePool: DataSourcePool,
) {

	fun getAll(country: Country): List<TagEntity> =
		dataSourcePool.getDataSource(country)?.let {
			JdbcTemplate(it).query(queryTags, BeanPropertyRowMapper(TagEntity::class.java))
		} ?: listOf()
}

@Repository
class ContemporaryIndexRepo @Autowired constructor(
	private val dataSourcePool: DataSourcePool,
) {

	fun getAll(country: Country): List<ContemporaryIndexEntity> =
		dataSourcePool.getDataSource(country)?.let {
			JdbcTemplate(it).query(queryContemporaryTags, BeanPropertyRowMapper(ContemporaryIndexEntity::class.java))
		} ?: listOf()

}


