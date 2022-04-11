package com.batchprocessor.cloud.indexation.agent.service.cache

import com.batchprocessor.cloud.indexation.agent.service.data.Job
import com.batchprocessor.cloud.indexation.agent.shared.constant.Country
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

/**
 * @author : Mohammad <mohammad.saeedi></mohammad.saeedi>@visual-meta.com>
 * @since : 26.10.21, Tue
 */
abstract class Cache<KEY, ENTITY, VALUE> {
	private val cache = ConcurrentHashMap<Country, HashMap<KEY, VALUE>>()

	fun get(job: Job, input: KEY): VALUE? = with(job) {
		when (cacheNeedsUpdate(country)) {
			false -> getAndUpdateAccessTime(country, input)
			true -> {
				locks.putIfAbsent(country, ReentrantLock())
				locks[country]?.withLock { if (cacheNeedsUpdate(country)) fillInTheCache(this) }
				getAndUpdateAccessTime(country, input)
			}
		}
	}

	private fun getAndUpdateAccessTime(country: Country, key: KEY): VALUE? =
		cache[country]?.get(key).also { timeOfLastAccess[country] = Calendar.getInstance() }


	private fun fillInTheCache(job: Job): Unit =
		with(job) {
			log.info("${getCacheName()} needs update, country: $country")
				.also {
					getAll(this).asSequence().filter { null != generateKey(it) }
						.associateTo(HashMap()) { generateKey(it) to generateValue(it) }
						.also { cache[country] = it }.also { timeOfCreation[country] = Calendar.getInstance() }
				}
		}

	private fun getCacheName(): String = this.javaClass.simpleName


	private fun cacheNeedsUpdate(country: Country) =
		cache[country].let {
			null == it || timeOfCreation[country]?.before(Calendar.getInstance().add(Calendar.MILLISECOND, fixedRate)) ?: true
		}

	protected abstract fun generateKey(value: ENTITY): KEY
	protected abstract fun generateValue(value: ENTITY): VALUE
	protected abstract fun getAll(job: Job): List<ENTITY>

	@Scheduled(fixedRate = fixedRate.toLong())
	fun removeObsoleteCaches() =
		timeOfLastAccess.filter { (_, accessTime) ->
			accessTime.before(Calendar.getInstance().add(Calendar.MILLISECOND, fixedRate))
		}
			.map { it.key }.forEach { cache.remove(it) }


	companion object {

		private const val fixedRate = 900_000
		private val locks = ConcurrentHashMap<Country, ReentrantLock>()
		private val timeOfCreation = ConcurrentHashMap<Country, Calendar>()
		private val timeOfLastAccess = ConcurrentHashMap<Country, Calendar>()
		private val log = LoggerFactory.getLogger(Cache::class.java)


	}


}
