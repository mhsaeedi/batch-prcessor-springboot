package com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.step0040

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

/**
 * @author : Mohammad <mohammad.saeedi></mohammad.saeedi>@visual-meta.com>
 * @since : 27.10.21, Wed
 */
internal class ShopCountBinderTest {
	@Test
	@Disabled("Not yet implemented")
	fun `ensure MALFORMED_SHOP_COUNT be set and next executor won't call, when shopCount is not valid`() {
		assertFailure("")
		(-1 downTo -1000000 step 10000).map { toString() }.forEach(this::assertFailure)
	}

	private fun assertFailure(invalidShopCount: String) {
	}

	@Test
	@Disabled("Not yet implemented")
	fun `ensure shopCount set correctly and next executor calls, when shopCount is valid`() =
		(0..1000000 step 10000).map { toString() }.forEach(this::assertShopCount)


	private fun assertShopCount(shopCount: String) {
	}
}
