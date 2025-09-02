package com.unam.ciencias.practica1.patterns.strategy.payment;

/**
 * Strategy interface for calculating subscription costs
 * and providing plan descriptions.
 */
public interface PaymentStrategy {

	/**
	 * Calculates the monthly cost based on subscription duration.
	 * @param monthsSubscribe number of months subscribed
	 * @return monthly cost
	 */
	double calculateCost(int monthsSubscribe);

	/**
	 * Returns the description of the plan.
	 * @return description string
	 */
	String obtainDescription();
}
