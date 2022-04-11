package com.batchprocessor.cloud.indexation.agent.service.data

/**
 * @author : Momo
 * @since : 01.11.21, Mon
 *
 **/

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

/**
 * @author : Momo
 * @since : 28.10.21, Thu
 *
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "rule")
data class Rule(
	@field:JacksonXmlProperty(localName = "index")
	var index: Index? = null,
	@field:JacksonXmlProperty(localName = "CategoryUrlStructure")
	var categoryUrlStructure: CategoryUrlStructure? = null,
	@field:JacksonXmlProperty(localName = "canonical")
	var canonical: Canonical? = null,
	@field:JacksonXmlProperty(localName = "windows")
	var windows: Windows? = null,
	@field:JacksonXmlProperty(localName = "IndexableTagTypes")
	var indexableTagTypes: IndexableTagTypes? = null,
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("windows")
data class Windows(
	@field:JacksonXmlProperty(localName = "title", isAttribute = true)
	var title: String? = null,
	@field:JacksonXmlElementWrapper(useWrapping = false)
	@field:JacksonXmlProperty(localName = "window")
	var windowList: List<Window> = ArrayList(),
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("TagType")
data class Window(
	@field:JacksonXmlProperty(localName = "tagId", isAttribute = true)
	var name: String? = null,
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("Index")
data class Index(
	@field:JacksonXmlProperty(localName = "value", isAttribute = true)
	var value: Boolean? = null,
	@field:JacksonXmlProperty(localName = "inheritable", isAttribute = true)
	var inheritable: Boolean? = null,
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "CategoryUrlStructure")
data class CategoryUrlStructure(
	@field:JacksonXmlElementWrapper(useWrapping = false)
	@field:JacksonXmlProperty(localName = "TagType")
	var tagType: List<TagType> = ArrayList(),
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "IndexableTagTypes")
data class IndexableTagTypes(
	@field:JacksonXmlElementWrapper(useWrapping = false)
	@field:JacksonXmlProperty(localName = "TagType")
	var tagTypes: List<String> = ArrayList(),
	@field:JacksonXmlProperty(localName = "inheritable", isAttribute = true)
	var inheritable: Boolean? = null,
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("TagType")
data class TagType(
	@field:JacksonXmlProperty(localName = "name", isAttribute = true)
	var name: String = "",
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("canonical")
data class Canonical(
	@field:JacksonXmlProperty(localName = "tagTypes", isAttribute = true)
	var tagTypes: String? = null,
	@field:JacksonXmlProperty(localName = "inheritable", isAttribute = true)
	var inheritable: Boolean? = null,
)
