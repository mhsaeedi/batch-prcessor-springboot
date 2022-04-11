package com.batchprocessor.cloud.indexation.agent.shared.constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public enum TagType
{

	// NEXT PERSISTENCE_ID MUST BE UNIQUE AND < TagType.values().length !!!

	CATEGORY(0, false), // 0
	GENDER(1, false), //
	STYLE(2, false), //
	SUB_STYLE(3, false), //
	SUB_SUB_STYLE(4, false), //
	SUB_SUB_SUB_STYLE(5, false), // 5
	COLOR(6, true), //
	BRAND(7, true), //
	MATERIAL(8, true), //
	PATTERN(9, true), //
	AUX_1(10, true), // TODO Johannes, AUX_1 tag is actually the sale tag!  10
	SIZE(11, true), //
	SHOP(12, true), // 12
	STRUCTURAL(13, false), // 13
	SERIES(14, true, BRAND), //

	AUX_2(15, true), // AUX_2: zB. VARIETAL  15
	FLAVOR(16, true), // AUX_3
	COUNTRY(17, true), // AUX_4
	AUX_5(18, true), // AUX_5: zB. REGION
	YEAR(19, true), // AUX_6

	SHIPPING_COSTS(20, true), // shipping costs, especially NO_SHIPPING_COSTS  20

	LOOK(21, true), // 21
	COUPON(22, true), //
	BIO(23, true), //
	GRAPE_VARIETY(24, true), //
	REGION(25, true), // 25
	PACKAGE_SIZE(26, true), //
	INGREDIENTS(27, true), //
	MERCHANDISE(28, true), //
	PAYMENT_TYPE(29, true), // credit card, cash, etc.
	PLACEHOLDER_USED_TO_BE_BRANCH(30, false), // 30 - DO NOT DELETE!!! THIS WILL MESS UP THE ORDER.

	WIDTH(31, true), // 31
	HEIGHT(32, true), //
	LENGTH(33, true), //
	CUP_SIZE(34, true), //

	DIAET(35, true), // 35
	VEGY_VEGAN(36, true), //
	SUB_SERIES(37, true, SERIES), // 37
	HEEL_HEIGHT(38, true), //
	SUB_SUB_SUB_SUB_STYLE(39, false), //
	MEMORY_SIZE(40, true), // 40
	SCREEN_SIZE(41, true), //
	SCREEN_RESOLUTION(42, true), //
	NETWORK(43, true), //
	AGE(44, true), //
	THREE_D(45, true), // 45

	ASPECT_RATIO(46, true), //
	CONTRAST_RATIO(47, true), //
	CONNECTIONS(48, true), //
	REFRESH_RATE(49, true), // 49

	SUB_COLOR(50, true, COLOR), // 50
	COLLAR_SIZE(51, true), //
	HATS_SIZE(52, true), //
	SUB_SUB_SERIES(53, true, SUB_SERIES), //
	TROUSER_SIZE(54, true), //
	DIN_SIZE(55, true), //
	THICKNESS(56, true), //
	QUANTITY(57, true), //
	WEIGHT(58, true), //

	STORE(59, true), //
	HARDNESS_GRADE(60, true), //60
	VOLUME(61, true), //
	GRAIN_FREE(62, true), //
	GLUTEN_FREE(63, true), //
	SENSITIVE(64, true), //
	LACTOSE_FREE(65, true), //
	FRUCTOSE_FREE(66, true), //
	ANATOMICAL(67, true), //

	AREA(68, true, COUNTRY), //
	ORIGIN(69, true, AREA), //

	BUNDLE(70, true), //
	PRODUCT_VARIANT(71, true), //

	SHAPE(72, true), //
	WATERPROOF(73, true), //
	BREATHABLE(74, true), //
	WINDPROOF(75, true), //
	TECHNOLOGY(76, true), //

	HOME_AND_AWAY(77, true), //
	SUB_MATERIAL(78, true, MATERIAL), //
	SUB_MERCHANDISE(79, true, MERCHANDISE), //

	PERFORMANCE(80, true), //
	OPERATING_SYSTEM(81, true), //
	PIXELS(82, true), //
	LOCKED_STATUS(83, true), //
	CONTRACT(84, true), //
	INPUT(85, true), //
	SAR_VALUE(86, true), //
	RAM(87, true),

	TOE_STYLE(88, true), //
	INSOLE(89, true), //
	FASTENING(90, true), //
	LACING(91, true), //
	SOLE(92, true), //
	BOOT_FLEX(93, true), //
	LINER(94, true),

	SLEEVE_LENGTH(95, true), //
	SKIRT_LENGTH(96, true), //

	BOTTLER(97, true), //
	BOTTLING_DATE(98, true), //
	BARREL_STYLE(99, true), //
	ALCOHOL_CONTENT(100, true), //
	DOSAGE(101, true), //
	APPLICATION_AREA(102, true), //
	INTERNATIONAL_LOOK(103, true), //
	PARABEN_FREE(104, true), //
	SILICONE_FREE(105, true), //
	FRAGRANCE_FREE(106, true), //
	ALCOHOL_FREE(107, true), //
	ALUMINIUM_FREE(108, true), //

	PLACEHOLDER_USED_TO_BE_INSTALLMENT(109,
		true), //TODO please use this placeholder when adding new tagType before using ones with high persistenceIDs

	REDUCTION(110, true),
	DIAMETER(111, true), //
	PFC_FREE(112, true), //
	SALT_FREE(113, true), //
	SUB_SUB_MERCHANDISE(114, true, SUB_MERCHANDISE), //
	SUB_SUB_SUB_SUB_SUB_STYLE(115, false),

	PRODUCT_SHAPE(116, true),
	PLUS_SIZE(117, true),
	UV_PROTECTION(118, true),
	TOY_THEME(119, true),

	CAMPAIGN(120, true),

	GAUGE(121, true),
	TOY_MELODY(122, true),
	KIDS_SAFETY(123, true),
	FIGURE_SIZE(124, true),
	RAILWAY_ERA(125, true),
	RAILWAY_COMPANY(126, true),
	POWER_SUPPLY(127, true),

	PUZZLE_PIECE_NUMBERS(128, true),
	MOTIVE(129, true),
	GAME_WRITER(130, true),
	AWARD(131, true),
	OCCASION(132, true),
	DRESS_CODE(133, true),
	BACK_STYLE(134, true),
	WATER_COLUMN(135, true),

	FEATURE(136, true),
	MOVEMENT(137, true),
	NECKLINE(138, true),
	BAND_TYPE(139, true),
	BAND_MATERIAL(140, true),
	CASE_MATERIAL(141, true),
	CASE_GLASS(142, true),
	DISCIPLINE(143, true),
	SUPPORT_LEVEL(144, true),
	DISPLAY(145, true),
	HANDMADE(146, true),
	VINTAGE(147, true),
	WATER_RESISTANCE(148, true),
	DIGITS(149, true),
	BAND_WIDTH(150, true),
	CASE_SIZE(151, true),
	ENGRAVED(152, true),
	NICKEL_FREE(153, true),
	STONE(154, true),
	FINISH(155, true),
	KARAT_GOLD(156, true),
	PEOPLE(157, true),
	COMPATIBILITY(158, true),
	NATURAL(159, true),
	SPECIAL_SIZE(160, true),
	SEASON(161, true),
	SUB_STONE(162, true, STONE),
	SUB_OCCASIONS(163, true, OCCASION),
	EDITION(164, true),
	MEASURES(165, true),
	OUTFIT(166, true),
	SET(167, true),
	COLLECTION(168, true),
	BAG_IN_BOX(169, true),
	GIFT_SETS(170, true),
	FAIRTRADE(171, true),
	FOOD_PAIRING(172, true),
	TASTE(173, true),
	HARDNESS_LEVEL(174, true),
	HAIR_TYPE(175, true),
	SKIN_TYPE(176, true),
	CONCERN(177, true),
	BODY_AREA(178, true),
	FORMULATION(179, true),
	FRAGRANCE(180, true),
	CASE_TYPE(181, true),
	CRUELTY_FREE(182, true),
	SKIN_SHADE(183, true),
	BENEFIT(184, true),
	APPLICATOR_TYPE(185, true),
	DURATION(186, true),
	HYPOALLERGENIC(187, true),
	FREE_FROM(188, true),
	SUSTAINABILITY(189, true),
	SKIN_TONE(190, true),
	MATERNITY(191, true),
	HAIR_COLOR(192, true),
	COVERAGE(193, true),

	ONE_SIZE_HEURISTIC(194, true),
	STEMM_SIZE_HEURISTIC(195, true),
	JEANS_SIZE_HEURISTIC(196, true),

	GEMSTONE_SHAPE(197, true),
	SETTING(198, true),
	FINENESS(199, true),
	CLARITY(200, true),
	METHOD(201, true),
	DIAMOND_COLOR(202, true),
	DIAMOND_CARAT(203, true),
	CHAIN_TYPE(204, true),
	DIAMOND_CUT(205, true),
	COATING(206, true),
	DELIVERY_TIME(207, true),

	COMBO_SIZE_HEURISTIC(208, true),
	FURNITURE_SIZE_HEURISTIC(209, true),
	SIZE_RANGE_HEURISTIC(210, true),
	HEEL_HEIGHT_HEURISTIC(211, true),

	SAFETY_CODE(212, true),
	SHAFT_HEIGHT(213, true),
	SHAFT_WIDTH(214, true),
	AGE_GROUP(215, true),

	POSITION(216, true),
	GEAR_NUMBER(217, true),
	BRAKING_SYSTEM(218, true),
	SECOND_HAND(219, true),
	// *************************************************************************
	// IMPORTANT! When adding TagTypes please use up these placeholders first. *
	// *************************************************************************
	/*
	 * Use the first available placeholder, move it above this comments. Afterwards, create a new placeholder at the end of the list.
	 */
	//TODO please use the PLACEHOLDER_USED_TO_BE_INSTALLMENT first
	PLACEHOLDER8(220, true),
	PLACEHOLDER9(221, true),
	PLACEHOLDER10(222, true),
	PLACEHOLDER11(223, true),
	PLACEHOLDER12(224, true),
	PLACEHOLDER13(225, true),
	PLACEHOLDER14(226, true),
	PLACEHOLDER15(227, true),
	PLACEHOLDER32(228, true),
	PLACEHOLDER33(229, true),
	PLACEHOLDER34(230, true),
	PLACEHOLDER35(231, true),
	PLACEHOLDER36(232, true),
	PLACEHOLDER37(233, true),
	PLACEHOLDER38(234, true),
	PLACEHOLDER39(235, true),
	PLACEHOLDER40(236, true),
	PLACEHOLDER41(237, true),
	PLACEHOLDER26(238, true),
	PLACEHOLDER27(239, true),
	PLACEHOLDER28(240, true),
	PLACEHOLDER29(241, true),
	PLACEHOLDER30(242, true),
	PLACEHOLDER31(243, true),
	PLACEHOLDER4(244, true),
	PLACEHOLDER5(245, true),
	PLACEHOLDER6(246, true);

	/**
	 * TagTypes which are parents but no children themselves.
	 */
	public static final EnumSet<TagType> ROOT_PARENT_TAG_TYPES;
	/**
	 * These are the only types allowed as top level tags in the tag tree
	 */
	public static final EnumSet<TagType> TOP_LEVEL_TAGS = EnumSet.of(CATEGORY, STRUCTURAL);
	/**
	 * Items can only have one of these. This is not the same is not-a-filter-tag.
	 */
	public static final EnumSet<TagType> SINGULAR_TAG_TYPES = EnumSet.of(CATEGORY, SHOP, BRAND, SERIES, SUB_SERIES, SUB_SUB_SERIES);
	public static final EnumSet<TagType> SERIES_TAG_TYPES = EnumSet.of(SERIES, SUB_SERIES, SUB_SUB_SERIES);
	public static final EnumSet<TagType> ROOT_TAG_TYPES = EnumSet.of(CATEGORY, GENDER, STYLE, SUB_STYLE, SUB_SUB_STYLE, SUB_SUB_SUB_STYLE,
		SUB_SUB_SUB_SUB_STYLE, BRAND, SERIES, SUB_SERIES, SUB_SUB_SERIES, MERCHANDISE, SUB_MERCHANDISE, SUB_SUB_MERCHANDISE, SHOP, STORE);
	public static final List<TagType> PAGE_TAG_TYPES = Arrays.asList(BRAND, MERCHANDISE, SHOP, STORE, CATEGORY);
	/**
	 * TagTypes which are never assigned by the tagging process.
	 */
	public static final EnumSet<TagType> TAG_TYPES_NEVER_ASSIGNABLE_BY_TAGGING = EnumSet.of(BRAND, SERIES, SHOP, SHIPPING_COSTS, AUX_1, COUPON,
		PAYMENT_TYPE, SUB_SERIES, SUB_SUB_SERIES, STORE, CAMPAIGN, STRUCTURAL, ONE_SIZE_HEURISTIC, STEMM_SIZE_HEURISTIC, JEANS_SIZE_HEURISTIC);
	public static final EnumSet<TagType> QUERY_TAGGING_TAG_TYPES = EnumSet.of(BRAND, SERIES, SHOP, SHIPPING_COSTS, AUX_1, COUPON, PAYMENT_TYPE,
		SUB_SERIES, SUB_SUB_SERIES, STORE, CAMPAIGN);
	/**
	 * CATEGORY, GENDER, STYLE, SUB_STYLE, SUB_SUB_STYLE, SUB_SUB_SUB_STYLE, SUB_SUB_SUB_SUB_STYLE, SUB_SUB_SUB_SUB_SUB_STYLE
	 */
	public static final TagType[] STYLE_TAG_TYPES_MASTER_TAG_IN_HIERARCHICAL_ORDER_DESC = new TagType[] { CATEGORY, GENDER, STYLE, SUB_STYLE,
	                                                                                                      SUB_SUB_STYLE, SUB_SUB_SUB_STYLE,
	                                                                                                      SUB_SUB_SUB_SUB_STYLE,
	                                                                                                      SUB_SUB_SUB_SUB_SUB_STYLE };
	/**
	 * Set of size related tag types
	 */
	public static final EnumSet<TagType> SIZE_TAG_TYPES = EnumSet.of(SIZE, PACKAGE_SIZE, WIDTH, HEIGHT, LENGTH, CUP_SIZE, HEEL_HEIGHT, MEMORY_SIZE,
		SCREEN_SIZE, SCREEN_RESOLUTION, COLLAR_SIZE, HATS_SIZE, TROUSER_SIZE, DIN_SIZE, THICKNESS, QUANTITY, WEIGHT, VOLUME, PLUS_SIZE, DIAMETER);
	public static final EnumSet<TagType> MULTI_TAG_CLASSIFIER_TYPES = EnumSet.of(ONE_SIZE_HEURISTIC, JEANS_SIZE_HEURISTIC, COMBO_SIZE_HEURISTIC,
		STEMM_SIZE_HEURISTIC, FURNITURE_SIZE_HEURISTIC, SIZE_RANGE_HEURISTIC, HEEL_HEIGHT_HEURISTIC);
	public static final EnumSet<TagType> HIGH_PRIO_QUERY_TAG_TYPES = EnumSet.of(TagType.AUX_1, TagType.SHIPPING_COSTS, TagType.COUPON,
		TagType.REDUCTION, TagType.CAMPAIGN);
	/**
	 * display list for Tag Type drop down menu selections in Backoffice for both tag tree and master tag tree
	 */

	public static final EnumSet<TagType> DROP_DOWN_LIST;
	public static final ArrayList<String> DROP_DOWN_LIST_NAMES;
	public static final EnumSet<TagType> STYLE_TAG_TYPES;
	public static final EnumSet<TagType> MERCHANDISE_TAG_TYPES;
	public static final EnumSet<TagType> BRAND_TAG_TYPES;
	/**
	 * Below list is used to make drop down items in TagsTab grey for brands and merchandises as they are not allowed to be created through TagsTab.
	 */
	public static final Set<String> BRAND_AND_MERCHANDISE_TYPES = new HashSet<>();
	public static final EnumSet<TagType> FILTER_TAG_TYPES;
	public static final EnumSet<TagType> SUB_TAG_TYPES;
	private static final Map<TagType, TagType> PARENT_TO_CHILD_TAG_TYPE = new HashMap<>();
	/**
	 * SUB_SUB_SUB_SUB_STYLE, SUB_SUB_SUB_STYLE, SUB_SUB_STYLE, SUB_STYLE, STYLE, GENDER, CATEGORY
	 */
	private static final TagType[] STYLE_TAG_TYPES_IN_HIERARCHICAL_ORDER_ASC = new TagType[] { SUB_SUB_SUB_SUB_SUB_STYLE, SUB_SUB_SUB_SUB_STYLE,
	                                                                                           SUB_SUB_SUB_STYLE, SUB_SUB_STYLE, SUB_STYLE, STYLE,
	                                                                                           GENDER, CATEGORY };
	/**
	 * CATEGORY, GENDER, STYLE, SUB_STYLE, SUB_SUB_STYLE, SUB_SUB_SUB_STYLE, SUB_SUB_SUB_SUB_STYLE
	 */
	private static final TagType[] STYLE_TAG_TYPES_IN_HIERARCHICAL_ORDER_DESC = new TagType[] { CATEGORY, GENDER, STYLE, SUB_STYLE, SUB_SUB_STYLE,
	                                                                                            SUB_SUB_SUB_STYLE, SUB_SUB_SUB_SUB_STYLE,
	                                                                                            SUB_SUB_SUB_SUB_SUB_STYLE };
	/**
	 * CATEGORY, STYLE, SUB_STYLE, SUB_SUB_STYLE, SUB_SUB_SUB_STYLE, SUB_SUB_SUB_SUB_STYLE
	 */
	private static final TagType[] STYLE_TAG_TYPES_DESC_NO_GENDER = new TagType[] { CATEGORY, STYLE, SUB_STYLE, SUB_SUB_STYLE, SUB_SUB_SUB_STYLE,
	                                                                                SUB_SUB_SUB_SUB_STYLE, SUB_SUB_SUB_SUB_SUB_STYLE };
	/**
	 * BRAND and its children
	 */
	private static final Set<TagType> BRAND_RELATED_TYPES = new HashSet<>();
	private static final Map<Integer, TagType> PERSISTENCE_ID_TO_ENUM_CONSTANT_MAP;

	static
	{
		ROOT_PARENT_TAG_TYPES = EnumSet.noneOf(TagType.class);
		for (TagType tagType : values())
		{
			if (tagType.isParent() && !tagType.isChild())
			{
				ROOT_PARENT_TAG_TYPES.add(tagType);
			}
		}
	}

	static
	{
		for (TagType tagType : values())
		{
			if (tagType.isChild())
			{
				PARENT_TO_CHILD_TAG_TYPE.put(tagType.getParent(), tagType);
			}
		}
	}

	static
	{
		DROP_DOWN_LIST = EnumSet.noneOf(TagType.class);
		DROP_DOWN_LIST_NAMES = new ArrayList<>();
		for (TagType t : values())
		{
			if (!t.name().startsWith("PLACEHOLDER"))
			{
				DROP_DOWN_LIST.add(t);
				DROP_DOWN_LIST_NAMES.add(t.name());
			}
		}
	}

	static
	{
		STYLE_TAG_TYPES = EnumSet.noneOf(TagType.class);
		for (TagType tagType : getStyleTagTypesInHierarchicalOrderDesc())
		{
			STYLE_TAG_TYPES.add(tagType);
		}
	}

	static
	{
		MERCHANDISE_TAG_TYPES = EnumSet.noneOf(TagType.class);
		MERCHANDISE_TAG_TYPES.add(MERCHANDISE);
		MERCHANDISE_TAG_TYPES.add(SUB_MERCHANDISE);
		MERCHANDISE_TAG_TYPES.add(SUB_SUB_MERCHANDISE);
	}

	static
	{
		BRAND_TAG_TYPES = EnumSet.noneOf(TagType.class);
		BRAND_TAG_TYPES.add(BRAND);
		BRAND_TAG_TYPES.add(SERIES);
		BRAND_TAG_TYPES.add(SUB_SERIES);
		BRAND_TAG_TYPES.add(SUB_SUB_SERIES);
	}

	static
	{
		BRAND_RELATED_TYPES.addAll(BRAND.getChildrenDescending());
		BRAND_RELATED_TYPES.add(BRAND);
	}

	static
	{
		for (TagType tt : BRAND_RELATED_TYPES)
		{
			BRAND_AND_MERCHANDISE_TYPES.add(tt.name());
		}
		for (TagType tt : MERCHANDISE_TAG_TYPES)
		{
			BRAND_AND_MERCHANDISE_TYPES.add(tt.name());
		}
	}

	static
	{
		FILTER_TAG_TYPES = EnumSet.noneOf(TagType.class);
		for (TagType tagType : values())
		{
			if (tagType.isFilterTag)
			{
				FILTER_TAG_TYPES.add(tagType);
			}
		}
	}

	static
	{
		Map<Integer, TagType> persistenceIdToEnumConstantMap = new HashMap<>();
		for (TagType value : values())
		{
			persistenceIdToEnumConstantMap.put(value.getPersistenceID(), value);
		}
		PERSISTENCE_ID_TO_ENUM_CONSTANT_MAP = Collections.unmodifiableMap(persistenceIdToEnumConstantMap);
	}

	static
	{
		SUB_TAG_TYPES = EnumSet.noneOf(TagType.class);
		for (TagType tagType : values())
		{
			if (tagType.isChild())
			{
				SUB_TAG_TYPES.add(tagType);
			}
		}
	}

	private final int persistenceID;
	private boolean isFilterTag;
	private TagType parent;

	TagType(int persistenceID, boolean isFilterTag)
	{
		this.persistenceID = persistenceID;
		this.isFilterTag = isFilterTag;
	}

	TagType(int persistenceID, boolean isFilterTag, TagType parent)
	{
		this.persistenceID = persistenceID;
		this.isFilterTag = isFilterTag;
		this.parent = parent;
	}

	public static TagType[] getStyleTagTypesInHierarchicalOrderAsc()
	{
		TagType[] publicArray = new TagType[STYLE_TAG_TYPES_IN_HIERARCHICAL_ORDER_ASC.length];
		System.arraycopy(STYLE_TAG_TYPES_IN_HIERARCHICAL_ORDER_ASC, 0, publicArray, 0, STYLE_TAG_TYPES_IN_HIERARCHICAL_ORDER_ASC.length);
		return publicArray;
	}

	public static TagType[] getStyleTagTypesInHierarchicalOrderDesc()
	{
		TagType[] publicArray = new TagType[STYLE_TAG_TYPES_IN_HIERARCHICAL_ORDER_DESC.length];
		System.arraycopy(STYLE_TAG_TYPES_IN_HIERARCHICAL_ORDER_DESC, 0, publicArray, 0, STYLE_TAG_TYPES_IN_HIERARCHICAL_ORDER_DESC.length);
		return publicArray;
	}

	public static TagType[] getStyleTagTypesDescNoGender()
	{
		TagType[] publicArray = new TagType[STYLE_TAG_TYPES_DESC_NO_GENDER.length];
		System.arraycopy(STYLE_TAG_TYPES_DESC_NO_GENDER, 0, publicArray, 0, STYLE_TAG_TYPES_DESC_NO_GENDER.length);
		return publicArray;
	}

	public static boolean isBrandRelated(TagType tagType)
	{
		return BRAND_RELATED_TYPES.contains(tagType);
	}

	public static Set<TagType> getBrandRelatedTypes()
	{
		return new HashSet<>(BRAND_RELATED_TYPES);
	}

	public static TagType fromPersistenceID(final Integer persistenceID)
	{
		TagType ret = PERSISTENCE_ID_TO_ENUM_CONSTANT_MAP.get(persistenceID);
		if (ret == null)
		{
			throw new IllegalArgumentException("An enum constant with persistenceID " + persistenceID + " does not exist in enum TagType");
		}

		return ret;
	}

	public static boolean isStyleTag(TagType type)
	{
		EnumSet<TagType> styleTagsSet = EnumSet.of(STYLE, getStyleTagTypesInHierarchicalOrderAsc());
		return styleTagsSet.contains(type);
	}

	public static TagType getTagTypeFromHierarchies(TagType parentTagType, boolean considerGender)
	{
		TagType guessedTagType;

		if (considerGender)
		{
			guessedTagType = getTagType(parentTagType, TagType.getStyleTagTypesInHierarchicalOrderDesc());
		}
		else
		{
			guessedTagType = getTagType(parentTagType, TagType.getStyleTagTypesDescNoGender());
		}

		if (guessedTagType == null)
		{
			guessedTagType = PARENT_TO_CHILD_TAG_TYPE.get(parentTagType);
		}

		if (guessedTagType == null)
		{
			return TagType.STRUCTURAL;
		}
		else
		{
			return guessedTagType;
		}
	}

	private static TagType getTagType(TagType parentTagType, TagType[] possibleTagTypes)
	{
		for (int i = 0; i < possibleTagTypes.length - 1; ++i)
		{
			if (possibleTagTypes[i] == parentTagType)
			{
				return possibleTagTypes[i + 1];
			}
		}
		return null;
	}

	public int getPersistenceID()
	{
		return persistenceID;
	}

	public boolean isFilterTag()
	{
		return isFilterTag;
	}

	public boolean isChild()
	{
		return this.parent != null;
	}

	public boolean isParent()
	{
		for (TagType tagType : values())
		{
			if (tagType.isChild() && tagType.getParent() == this)
			{
				return true;
			}
		}
		return false;
	}

	public TagType getParent()
	{
		if (this.parent == null)
		{
			throw new IllegalStateException(this + " is no known SubTagType! Use .isChild() to check!");
		}
		return this.parent;
	}

	public boolean isAncestorOf(TagType tagType)
	{
		if (TagType.isBrandRelated(this) && TagType.isBrandRelated(tagType))
		{
			if (tagType.equals(SUB_SUB_SERIES) && !this.equals(SUB_SUB_SERIES))
			{
				return false;
			}
			else
			{
				return tagType.getChildrenDescending().contains(this);
			}
		}
		else
		{
			throw new IllegalStateException(this + " and " + tagType + " are not from the same hierarchical structure !");
		}
	}

	public TagType getChild()
	{
		for (TagType tagType : values())
		{
			if (tagType.isChild() && tagType.getParent() == this)
			{
				return tagType;
			}
		}
		throw new IllegalStateException(this + " is NOT a parent of a SubTagType. Use .isParent() to check!");
	}

	public List<TagType> getChildrenDescending()
	{
		return this.getChildrenDescending(new ArrayList<TagType>(), this);
	}

	/* PRIVATE HELPER METHODS */

	public TagType getRootAncestor()
	{
		return this.getRootAncestor(this);
	}

	public EnumSet<TagType> getAncestors()
	{
		return this.getAncestors(EnumSet.noneOf(TagType.class), this);
	}

	private TagType getRootAncestor(TagType tagType)
	{
		if (tagType.isChild())
		{
			TagType parent = tagType.getParent();

			if (parent.isChild())
			{
				return getRootAncestor(parent);
			}
			else
			{
				return parent;
			}
		}
		else
		{
			throw new IllegalStateException(this + " is no known SubTagType! Use isChild() to check!");
		}
	}

	private EnumSet<TagType> getAncestors(EnumSet<TagType> res, TagType tagType)
	{
		if (tagType.isChild())
		{
			TagType parent = tagType.getParent();
			res.add(parent);
			if (parent.isChild())
			{
				return getAncestors(res, parent);
			}
			else
			{
				return res;
			}
		}
		else
		{
			throw new IllegalStateException(this + " is no known SubTagType! Use isChild() to check!");
		}
	}

	private List<TagType> getChildrenDescending(List<TagType> res, TagType tagType)
	{
		if (tagType.isParent())
		{
			TagType child = tagType.getChild();
			res.add(child);
			if (child.isParent())
			{
				return getChildrenDescending(res, child);
			}
			else
			{
				return res;
			}
		}
		else
		{
			throw new IllegalStateException(this + " is no known parent of another tagType! Use isParent() to check!");
		}
	}

}
