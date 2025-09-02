package com.unam.ciencias.practica1.model;

import com.unam.ciencias.practica1.patterns.strategy.payment.PaymentStrategy;

/**
 * Represents a subscription plan.
 * Tracks subscribed months and uses a PaymentStrategy
 * to calculate costs and provide a description.
 */
public class Plan {

	/** Number of months subscribed. */
	private int monthsSubscribed;

	/** Payment strategy associated with this plan. */
	private final PaymentStrategy strategy;

	/**
	 * Creates a new plan with the given payment strategy.
	 * @param strategy payment strategy
	 * @throws IllegalArgumentException if strategy is null
	 */
	public Plan(PaymentStrategy strategy) {
		if (strategy == null) {
			throw new IllegalArgumentException("Strategy cannot be null");
		}
		this.strategy = strategy;
		this.monthsSubscribed = 0;
	}

	/**
	 * Returns the cost for the next month.
	 * @return monthly cost
	 */
	public double getCost() {
		return strategy.calculateCost(monthsSubscribed + 1);
	}

	/**
	 * Returns the description of the plan.
	 * @return plan description
	 */
	public String getDescription() {
		return strategy.obtainDescription();
	}

	/**
	 * Increases the subscribed months count by one.
	 */
	public void increaseMonth() {
		monthsSubscribed++;
	}

	/**
	 * Returns the total number of subscribed months.
	 * @return subscribed months
	 */
	public int getMonthsSubscribed() {
		return monthsSubscribed;
	}
}
