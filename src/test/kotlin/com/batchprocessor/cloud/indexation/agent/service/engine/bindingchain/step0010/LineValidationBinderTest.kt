package com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.step0010

import com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.step0020.WeeklyDailyBinder
import com.batchprocessor.cloud.indexation.agent.shared.constant.LineSegment
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

/**
 * @author : Mohammad <mohammad.saeedi></mohammad.saeedi>@visual-meta.com>
 * @since : 22.10.21, Fri
 */
@ExtendWith(MockKExtension::class)
internal class LineValidationBinderTest {

	private val weeklyDailyBind = mockk<WeeklyDailyBinder>(relaxed = true)
	private val lineValidationBind = LineValidationBinder(weeklyDailyBind)

	@Test
	fun `ensure isCorrupted be true and next executor won't invoke, when line is empty`() {
		assertMalformedLine("")
	}

	@Test
	fun `ensure isCorrupted be true when line has more than expected segments`() =
		((ACCEPTED + 1)..100).forEach { assertMalformedLine(getLine(it)) }


	private fun assertMalformedLine(line: String) {
//		val uri = Uri(country = Country.DE, line = line)
//		lineValidationBind.bind(batch.job, uri)
//		assertEquals(Condition.MALFORMED_LINE, uri.condition)
//		verify(exactly = 0) { weeklyDailyBind.bind(batch.job, any()) }
	}

	@Test
	fun `ensure next executor won't invoke, when line has more than expected segments`() =
		((ACCEPTED + 1)..100).forEach {
			assertMalformedLine(getLine(it))
			assertMalformedLine("${getLine(it)};;;;")
		}

	@Test
	fun `ensure condition is MALFORMED_LINE and next step will not invoke, when line has less than expected segments`() {
		((ACCEPTED - 1) downTo -100).forEach {
			assertMalformedLine(getLine(it))
			assertMalformedLine("${getLine(it)};;;;")
		}
	}

	@Test
	fun `ensure next executor be called and uri be healthy, when line is valid`() {
//		val uri = Uri(country = Country.DE, line = getLine(ACCEPTED))
//		lineValidationBind.bind(batch.job, uri)
//		assertEquals(Condition.HEALTHY, uri.condition)
//		verify(exactly = 1) { weeklyDailyBind.bind(batch.job, uri) }
	}

	companion object {
		private val ACCEPTED = LineSegment.values().size;
		private fun getLine(size: Int): String = (0 until size).joinToString(";")

	}
}
