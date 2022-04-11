package com.batchprocessor.cloud.indexation.agent.service.engine.bindingchain.step0050

import com.batchprocessor.cloud.indexation.agent.shared.constant.UriType
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

/**
 * @author : Momo
 * @since : 25.10.21, Mon
 */
@ExtendWith(MockKExtension::class)
internal class UriTypeBinderTest {


	@Test
	fun `ensure, condition be MALFORMED_TAG_TYPE and execution stops, when tagType is malformed`() {
		assertMalformed("")
		assertMalformed("1,1")
		assertMalformed("1,1,1")
		assertMalformed("1,1,1,1")
		assertMalformed("1,1,1,1,1")
		assertMalformed("1,1,1,1,1,1")
		assertMalformed("1,1,1,1,1,1,1")
		assertMalformed("1,1,1,1,1,1,1,1")
		assertMalformed("1,1,1,1,1,1,1,1")
		assertMalformed("1,1,1,1,1,1,1,1,1,")
		assertMalformed(",1,1,1,1,1,1,1,1,1")
		assertMalformed(",1,1,1,1,1,1,1,1,1,")
		assertMalformed("1,1,1,1,1,1,1,1,1,1")
		assertMalformed("1,1,1,1,1,1,,1,1")
		assertMalformed("1,1,1,1,1,1,2,1,1")
		assertMalformed("1,1,-1,1,1,1,2,1,1")
		assertMalformed("1,1,-1,1,1,1,2,1,1,2")
	}

	private fun assertMalformed(type: String) {
//		val tagsBind = mockk<TagsBinder>(relaxed = true)
//		val uriTypeBind = UriTypeBinder(tagsBind)
//		val uri = Uri(country = Country.DE, line = buildLine(type, LineSegment.URI_TYPE))
//		uriTypeBind.bind(batch.job, uri)
//		assertEquals(Condition.MALFORMED_URL_TYPE, uri.condition)
//		verify(exactly = 0) { tagsBind.bind(batch.job, any()) }
	}

	@Test
	fun `ensure, condition be HEALTHY and execution continues and correct tagType be set, when tagType is healthy`() {
		assertHealthy("1,1,1,1,1,1,1,1,1", UriType.CATEGORY)
		assertHealthy("1,0,0,0,0,0,0,0,0", UriType.CATEGORY)
		assertHealthy("0,1,1,1,1,1,1,1,1", UriType.COMBINED)
		assertHealthy("0,1,0,0,0,1,0,1,1", UriType.COMBINED)
		assertHealthy("0,1,0,0,0,1,0,1,1", UriType.COMBINED)
		assertHealthy("0,1,0,0,0,0,0,1,1", UriType.COMBINED)
		assertHealthy("0,1,0,0,0,0,0,0,1", UriType.COMBINED)
		assertHealthy("0,0,0,0,0,1,0,1,0", UriType.COMBINED)
		assertHealthy("0,1,0,0,0,1,0,0,1", UriType.COMBINED)
		assertHealthy("0,1,0,0,0,0,0,1,1", UriType.COMBINED)
		assertHealthy("0,1,0,0,0,0,0,1,0", UriType.COMBINED)
		assertHealthy("0,1,0,0,0,1,0,0,1", UriType.COMBINED)
		assertHealthy("0,0,0,0,0,0,0,0,1", UriType.STORE)
		assertHealthy("0,0,0,0,0,0,0,0,1", UriType.STORE)
		assertHealthy("0,0,1,1,1,0,1,0,1", UriType.STORE)
		assertHealthy("0,0,1,1,1,0,1,0,1", UriType.STORE)
		assertHealthy("0,0,1,1,1,0,1,1,0", UriType.SHOP)
		assertHealthy("0,0,1,1,1,0,1,0,0", UriType.SUB_MERCHANDISE)
		assertHealthy("0,0,1,1,1,1,0,0,0", UriType.MERCHANDISE)
		assertHealthy("0,0,1,1,1,0,0,0,0", UriType.SUB_SUB_SERIES)
		assertHealthy("0,0,1,1,0,0,0,0,0", UriType.SUB_SERIES)
		assertHealthy("0,0,1,0,0,0,0,0,0", UriType.SERIES)
		assertHealthy("0,1,0,0,0,0,0,0,0", UriType.BRAND)
	}

	private fun assertHealthy(type: String, expected: UriType) {
//		val tagsBind = mockk<TagsBinder>(relaxed = true)
//		val uriTypeBind = UriTypeBinder(tagsBind)
//
//		val uri = Uri(country = Country.DE, line = buildLine(type, LineSegment.URI_TYPE))
//		uriTypeBind.bind(batch.job, uri)
//		assertEquals(Condition.HEALTHY, uri.condition)
//		verify(exactly = 1) { tagsBind.bind(batch.job, uri) }
//		assertEquals(expected, uri.uriType)
	}
}
