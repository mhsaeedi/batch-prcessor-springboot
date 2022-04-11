package com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.step0030

import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

/**
 * @author : Momo
 * @since : 22.10.21, Fri
 */
@ExtendWith(MockKExtension::class)
internal class ItemCountBinderTest {

	@Test
	@Disabled("Not yet implemented")
	fun `ensure MALFORMED_ITEM_COUNT be set and next executor won't call, when itemCount is not valid`() {
		assertFailure("")
		(-1 downTo -1000000 step 10000).map { toString() }.forEach(this::assertFailure)
	}

	private fun assertFailure(invalidItemCount: String) {
	}

	@Test
	@Disabled("Not yet implemented")
	fun `ensure itemCount set correctly and next executor calls, when itemCount is valid`() =
		(0..1000000 step 10000).map { toString() }.forEach(this::assertItemCount)


	private fun assertItemCount(itemCount: String) {
	}

}
