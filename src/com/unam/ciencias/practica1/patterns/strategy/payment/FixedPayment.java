package com.unam.ciencias.practica1.patterns.strategy.payment;

/**
 * Payment strategy with a fixed monthly cost.
 */
public class FixedPayment implements PaymentStrategy {

	/** Fixed monthly cost. */
	private final double cost;

	/** Description of the plan. */
	private final String description;

	/**
	 * Creates a fixed payment strategy.
	 * @param cost monthly cost
	 * @param description plan description
	 */
	public FixedPayment(double cost, String description) {
		this.cost = cost;
		this.description = description;
	}

	/**
	 * Returns the fixed cost regardless of months subscribed.
	 * @param monthsSubscribe number of months subscribed (ignored)
	 * @return fixed monthly cost
	 */
	@Override
	public double calculateCost(int monthsSubscribe) {
		return cost;
	}

	/**
	 * Returns the description of the plan.
	 * @return description string
	 */
	@Override
	public String obtainDescription() {
		return description;
	}
}
