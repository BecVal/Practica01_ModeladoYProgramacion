package com.unam.ciencias.practica1.patterns.strategy.payment;

/**
 * Payment strategy with a monthly cost.
 */
public class FixedPayment implements PaymentStrategy {

	private final double cost;
	private final String description;

	/**
	 * Creates a payment strategy.
	 * @param cost monthly cost
	 * @param description plan description
	 */
	public FixedPayment(double cost, String description) {
		this.cost = cost;
		this.description = description;
	}

	/**
	 * Returns the cost regardless of months subscribed.
	 * @param monthsSubscribe number of months subscribed
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
