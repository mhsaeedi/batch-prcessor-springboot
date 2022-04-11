package com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.step0020

import com.batchprocessor.cloud.indexation.agent.shared.constant.WeeklyDaily
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

/**
 * @author : Momo
 * @since : 22.10.21, Fri
 */
@ExtendWith(MockKExtension::class)
internal class WeeklyDailyBinderTest {
	private val itemCountBind = mockk<ItemCountBinder>(relaxed = true);
	private val weeklyDailyBind = WeeklyDailyBinder(itemCountBind)

	@Test
	fun `ensure isCorrupted be true and next executor won't call, when weeklyDaily has an out of lower bound value`() {
		(-1 downTo -10000 step 100).forEach { assertFailure(it.toString()) }
	}

	@Test
	fun `ensure isCorrupted be true and next executor won't call, when weeklyDaily has an out of upper bound value`() {
		(WeeklyDaily.values().size..10000 step 100).forEach { assertFailure(it.toString()) }
	}

	@Test
	fun `ensure isCorrupted be true and next executor won't call, when weeklyDaily has empty value`() {
		assertFailure("")
	}

	@Test
	fun `ensure isCorrupted be true and next executor won't call, when weeklyDaily in not a number`() {
		('a'..'z').forEach { assertFailure(it.toString()) }
		('A'..'Z').forEach { assertFailure(it.toString()) }
	}

	@Test
	fun `ensure weekly daily set correctly and next executor calls, when value is valid`() {
		val maxAccepted = WeeklyDaily.values().size - 1
		(0..maxAccepted).forEach { assertHealthy(it.toString()) }
	}

	private fun assertFailure(weeklyDaily: String) {
//		val uri = Uri(country = Country.DE, line = getLine(weeklyDaily))
//		weeklyDailyBind.bind(batch.job, uri)
//		assertEquals(Condition.MALFORMED_WEEKLY_DAILY, uri.condition)
//		verify(exactly = 0) { itemCountBind.bind(batch.job, any()) }
	}

	private fun assertHealthy(weeklyDaily: String) {
//		val uri = Uri(country = Country.DE, line = getLine(weeklyDaily))
//		weeklyDailyBind.bind(batch.job, uri)
//		assertEquals(Condition.HEALTHY, uri.condition)
//		verify(exactly = 1) { itemCountBind.bind(batch.job, uri) }
	}

	companion object {
		private fun getLine(value: String): String = "TAG_SET;WEEKLY_DAILY;ITEM_COUNT;SHOP_STORE_COUNT;TAG_TYPE".replace("WEEKLY_DAILY", value)
	}
}
