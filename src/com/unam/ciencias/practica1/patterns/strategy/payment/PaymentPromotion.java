package com.unam.ciencias.practica1.patterns.strategy.payment;

/**
 * Payment with a promotional price for the first months, then a normal cost afterwards.
 */
public class PaymentPromotion implements PaymentStrategy {

	private final double normalCost;
	private final double promotionalCost;
	private final int promotionMonths;
	private final String description;

	/**
	 * Creates a payment promotion strategy.
	 * @param normalCost monthly cost after promotion
	 * @param promotionalCost monthly cost during promotion
	 * @param promotionMonths number of months the promotion lasts
	 * @param description plan description
	 */
	public PaymentPromotion(double normalCost, double promotionalCost, int promotionMonths, String description) {

		this.normalCost = normalCost;
		this.promotionalCost = promotionalCost;
		this.promotionMonths = promotionMonths;
		this.description = description;

	}

	/**
	 * Returns the promotional cost if within the period, otherwise returns the normal cost.
	 * @param monthsSubscribe number of months subscribed
	 * @return monthly cost based on subscription length
	 */
	@Override
	public double calculateCost(int monthsSubscribe) {
		if (monthsSubscribe <= promotionMonths) return promotionalCost;
		return normalCost;
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
