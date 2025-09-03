package com.unam.ciencias.practica1.model.services;

import com.unam.ciencias.practica1.patterns.strategy.payment.*;
import com.unam.ciencias.practica1.model.Plan;
import com.unam.ciencias.practica1.model.Service;
import java.util.Random;
import java.util.ArrayList;

public class HvoMax extends Service {
  private ArrayList<String> recommendations;

  public HvoMax() {
    super("HvoMax");
    availablePlans.add(new Plan(new PaymentPromotion(140, 0.0, 3, "Plan con Promoción")));
    availablePlans.add(new Plan(new FixedPayment(140.0, "Plan Básico")));

    recommendations = new ArrayList<>();
    recommendations.add("");
    recommendations.add("");
    recommendations.add("");
    recommendations.add("");
    recommendations.add("");
    recommendations.add("");
    recommendations.add("");
    recommendations.add("");
    recommendations.add("");
    recommendations.add("");
    recommendations.add("");
    recommendations.add("");
    
  }
  
  @Override
  public String getRecommendationOfTheMonth() {
    Random random = new Random();
    return "Este mes te recomendamos la serie: ' " + recommendations.get(random.nextInt(recommendations.size())) + " '";
  }
}
