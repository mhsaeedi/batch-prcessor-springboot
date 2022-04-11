package com.batchprocessor.cloud.indexation.agent.repository

import com.batchprocessor.cloud.indexation.agent.shared.constant.Country
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component
import javax.sql.DataSource


/**
 * @author : Mohammad <mohammad.saeedi@visual-meta.com>
 * @since : 26.10.21, Tue
 *
 **/
@Configuration
class DataSourceConfig {
	@Bean("deDataSource")
	@ConfigurationProperties("de.datasource")
	@Primary
	fun dataSource(): DataSource? {
		return DataSourceBuilder.create().build()
	}
}

@Component
class DataSourcePool @Autowired constructor(
	@Qualifier("deDataSource") private val deDataSource: DataSource,
) {
	private val pool = hashMapOf<Country, DataSource>()

	init {
		pool[Country.DE] = deDataSource
	}

	fun getDataSource(country: Country) = pool[country]

}
