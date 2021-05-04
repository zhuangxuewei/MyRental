package com.prudential.rental.common.mybatis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.prudential.rental.common.Page.Page;
import com.prudential.rental.common.Page.PageCondition;

public class Criteria extends PageCondition {
	private static final long serialVersionUID = 1L;
	protected boolean distinct;
	protected String locale;
	protected List<Condition> oredCriteria;
	public Criteria() {
		this.oredCriteria = new ArrayList();
	}
	public Criteria(Page page) {
		super(page);
		this.oredCriteria = new ArrayList();
	}

	public String getLocale() {
		return this.locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	public boolean isDistinct() {
		return this.distinct;
	}

	public List<Condition> getOredCriteria() {
		return this.oredCriteria;
	}

	public void or(Condition condition) {
		this.oredCriteria.add(condition);
	}

	public Condition or() {
		Condition condition = createConditionInternal();
		this.oredCriteria.add(condition);
		return condition;
	}

	public Condition createConditon() {
		Condition condition = createConditionInternal();
		if (this.oredCriteria.size() == 0) {
			this.oredCriteria.add(condition);
		}
		return condition;
	}

	protected Condition createConditionInternal() {
		Condition condition = new Condition();
		return condition;
	}

	public void clear() {
		super.clear();
		this.oredCriteria.clear();
		this.distinct = false;
		this.locale = null;
	}

	public static class Criterion implements Serializable {
		private static final long serialVersionUID = 1L;
		private final String condition;
		private Object value;
		private Object secondValue;
		private boolean noValue;
		private boolean singleValue;
		private boolean betweenValue;
		private boolean listValue;
		private boolean dateValue;
		private final String typeHandler;

		protected Criterion(String condition) {
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if ((value instanceof List))
				this.listValue = true;
			else if ((value instanceof Date))
				this.dateValue = true;
			else
				this.singleValue = true;
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue,
				String typeHandler) {
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}

		public String getCondition() {
			return this.condition;
		}

		public Object getValue() {
			return this.value;
		}

		public Object getSecondValue() {
			return this.secondValue;
		}

		public boolean isNoValue() {
			return this.noValue;
		}

		public boolean isSingleValue() {
			return this.singleValue;
		}

		public boolean isBetweenValue() {
			return this.betweenValue;
		}

		public boolean isListValue() {
			return this.listValue;
		}

		public boolean isDateValue() {
			return this.dateValue;
		}

		public String getTypeHandler() {
			return this.typeHandler;
		}
	}

	public static class Condition implements Serializable {
		private static final long serialVersionUID = 1L;
		private static final String LIKE = " like";
		private static final String NOT_LIKE = " not like";
		private static final String ANY_MATCH = "%";
		private static final String UPPER_LEFT = "upper(";
		private static final String UPPER_RIGHT = ") like";
		protected List<Criteria.Criterion> criterions = new ArrayList();

		public boolean isValid() {
			return this.criterions.size() > 0;
		}

		public List<Criteria.Criterion> getCriterions() {
			return this.criterions;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				return;
			}
			this.criterions.add(new Criteria.Criterion(condition));
		}

		protected void addCriterion(String condition, Object value,
				String property) {
			if (value == null) {
				return;
			}
			this.criterions.add(new Criteria.Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1,
				Object value2, String property) {
			if ((value1 == null) || (value2 == null)) {
				return;
			}
			this.criterions.add(new Criteria.Criterion(condition, value1,
					value2));
		}

		public Condition andIsNull(String columnName) {
			addCriterion(columnName + " is null");
			return this;
		}

		public Condition andIsNotNull(String columnName) {
			addCriterion(columnName + " is not null");
			return this;
		}

		public Condition andEqualTo(String columnName, Object value) {
			addCriterion(columnName + " =", value, columnName);
			return this;
		}

		public Condition andNotEqualTo(String columnName, Object value) {
			addCriterion(columnName + " <>", value, columnName);
			return this;
		}

		public Condition andGreaterThan(String columnName, Object value) {
			addCriterion(columnName + " >", value, columnName);
			return this;
		}

		public Condition andGreaterThanOrEqualTo(String columnName, Object value) {
			addCriterion(columnName + " >=", value, columnName);
			return this;
		}

		public Condition andLessThan(String columnName, Object value) {
			addCriterion(columnName + " <", value, columnName);
			return this;
		}

		public Condition andLessThanOrEqualTo(String columnName, Object value) {
			addCriterion(columnName + " <=", value, columnName);
			return this;
		}

		public Condition andBetween(String columnName, Object value1,
				Object value2) {
			addCriterion(columnName + " between", value1, value2, columnName);
			return this;
		}

		public Condition andNotBetween(String columnName, Object value1,
				Object value2) {
			addCriterion(columnName + " not between", value1, value2,
					columnName);
			return this;
		}

		public Condition andIn(String columnName, List<Object> values) {
			if ((values != null) && (values.size() > 0))
				addCriterion(columnName + " in", values, columnName);
			else {
				addCriterion("1 != 1");
			}
			return this;
		}

		public Condition andNotIn(String columnName, List<Object> values) {
			if ((values != null) && (values.size() > 0)) {
				addCriterion(columnName + " not in", values, columnName);
			}
			return this;
		}

		public Condition andLeftLike(String columnName, String value) {
			if (StringUtils.isNotBlank(value)) {
				addCriterion(columnName + " like", value + "%", columnName);
			}
			return this;
		}

		public Condition andRightLike(String columnName, String value) {
			if (StringUtils.isNotBlank(value)) {
				addCriterion(columnName + " like", "%" + value, columnName);
			}
			return this;
		}

		public Condition andLike(String columnName, String value) {
			if (StringUtils.isNotBlank(value)) {
				addCriterion(columnName + " like", "%" + value + "%",
						columnName);
			}
			return this;
		}

		public Condition andLeftNotLike(String columnName, String value) {
			if (StringUtils.isNotBlank(value)) {
				addCriterion(columnName + " not like", value + "%", columnName);
			}
			return this;
		}

		public Condition andRightNotLike(String columnName, String value) {
			if (StringUtils.isNotBlank(value)) {
				addCriterion(columnName + " not like", "%" + value, columnName);
			}
			return this;
		}

		public Condition andNotLike(String columnName, String value) {
			if (StringUtils.isNotBlank(value)) {
				addCriterion(columnName + " not like", "%" + value + "%",
						columnName);
			}
			return this;
		}

		public Condition andLikeInsensitive(String columnName, String value) {
			if (StringUtils.isNotBlank(value)) {
				addCriterion("upper(" + columnName + ") like",
						"%" + value.toUpperCase() + "%", columnName);
			}
			return this;
		}

		public Condition andLeftLikeInsensitive(String columnName, String value) {
			if (StringUtils.isNotBlank(value)) {
				addCriterion("upper(" + columnName + ") like",
						value.toUpperCase() + "%", columnName);
			}
			return this;
		}

		public Condition andRightLikeInsensitive(String columnName, String value) {
			if (StringUtils.isNotBlank(value)) {
				addCriterion("upper(" + columnName + ") like",
						"%" + value.toUpperCase(), columnName);
			}
			return this;
		}
	}
}