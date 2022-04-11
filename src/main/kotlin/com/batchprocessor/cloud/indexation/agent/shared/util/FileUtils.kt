package com.batchprocessor.cloud.indexation.agent.shared.util

import com.ctc.wstx.stax.WstxInputFactory
import com.ctc.wstx.stax.WstxOutputFactory
import com.fasterxml.jackson.dataformat.xml.XmlFactory
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.batchprocessor.cloud.indexation.agent.service.data.Job
import com.batchprocessor.cloud.indexation.agent.shared.AgentConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.File
import javax.xml.stream.XMLInputFactory

/**
 * @author : Momo
 * @since : 11.10.21, Mon
 *
 **/
@Service
class FileUtil @Autowired constructor(
	private val agentConfig: AgentConfig,
) {
	fun readLines(job: Job) =
		ArrayList(File(String.format(agentConfig.feedLocation ?: "", job.country.name.lowercase())).readLines())

}

private val dummy = object {}.javaClass
fun read(path: String): String = dummy.getResource(path)?.readText() ?: ""

val xmlMapper = XmlMapper(XmlFactory(WstxInputFactory().apply {
	this.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, false)
}, WstxOutputFactory()))
