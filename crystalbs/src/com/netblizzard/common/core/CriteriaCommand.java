package com.netblizzard.common.core;

import org.hibernate.Criteria;

public abstract interface CriteriaCommand {
	public static final String SORT_DESC = "desc";
	public static final String SORT_ASC = "asc";

	public abstract Criteria execute(Criteria paramCriteria);
}