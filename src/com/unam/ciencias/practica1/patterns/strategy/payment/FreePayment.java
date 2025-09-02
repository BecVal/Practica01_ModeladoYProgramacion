package com.unam.ciencias.practica1.patterns.strategy.payment;

/**
 * Payment strategy with no cost (free version).
 */
public class FreePayment implements PaymentStrategy {

	/**
	 * Always returns 0 as this plan is free.
	 * @param monthsSubscribe number of months subscribed (ignored)
	 * @return 0.0
	 */
	@Override
	public double calculateCost(int monthsSubscribe) {
		return 0.0;
	}

	/**
	 * Returns the description of the free plan.
	 * @return description string
	 */
	@Override
	public String obtainDescription() {
		return "Free version";
	}
}
