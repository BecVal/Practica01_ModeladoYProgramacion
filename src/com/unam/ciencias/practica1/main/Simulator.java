package com.unam.ciencias.practica1.main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.unam.ciencias.practica1.model.User;
import com.unam.ciencias.practica1.model.Plan;
import com.unam.ciencias.practica1.model.Service;
import com.unam.ciencias.practica1.model.services.HvoMax;
import com.unam.ciencias.practica1.model.services.MemeFlix;
import com.unam.ciencias.practica1.model.services.Momazon;
import com.unam.ciencias.practica1.model.services.Spootify;
import com.unam.ciencias.practica1.model.services.ThisneyPlus;
import com.unam.ciencias.practica1.patterns.strategy.payment.FixedPayment;
import com.unam.ciencias.practica1.patterns.strategy.payment.FreePayment;
import com.unam.ciencias.practica1.patterns.strategy.payment.PaymentPromotion;

/**
 * Main simulation class that orchestrates users and services month by month.
 */
public class Simulator {

    private final List<User> users = new ArrayList<>();
    private final List<Service> services = new ArrayList<>();
    private final Map<String, Service> serviceMap = new HashMap<>();
    private final DecimalFormat moneyFmt = new DecimalFormat("$0.00");

    public Simulator() {
        // Create services
        Service meme = new MemeFlix();
        Service moma = new Momazon();
        Service spoo = new Spootify();
        Service thisney = new ThisneyPlus();
        Service hvo = new HvoMax();

        // Fixed order of charges
        services.add(meme);
        services.add(moma);
        services.add(spoo);
        services.add(thisney);
        services.add(hvo);

        serviceMap.put("MemeFlix", meme);
        serviceMap.put("Momazon", moma);
        serviceMap.put("Spootify", spoo);
        serviceMap.put("ThisneyPlus", thisney);
        serviceMap.put("HvoMax", hvo);

        // Users with initial money
        User alicia = new User("Alicia", 15000);
        User bob    = new User("Bob", 2400);
        User cesar  = new User("César", 5000);
        User diego  = new User("Diego", 9000);
        User erika  = new User("Erika", 10000);
        User fausto = new User("Fausto", 5000);

        users.add(alicia);
        users.add(bob);
        users.add(cesar);
        users.add(diego);
        users.add(erika);
        users.add(fausto);
    }

    public void runSimulation() {
        try (BufferedWriter out = new BufferedWriter(new FileWriter("simulation_output.txt"))) {
            // Initialize subscriptions
            initializeSubscriptions(out);

            out.write("=== Streaming Services Simulation ===\n");

            for (int month = 1; month <= 12; month++) {
                out.write("\n------------------------------\n");
                out.write("Month " + month + "\n");
                out.write("------------------------------\n");

                applySchedule(month, out);

                // Cada servicio cobra a sus suscriptores
                for (Service service : services) {
                    out.write("\n --- " + service.getName() + " charging ---\n");
                    service.chargeSubscribers(out);
                    
                    // También mostrar recomendaciones para los usuarios suscritos
                    for (User user : users) {
                        Plan plan = user.getPlanForService(service);
                        if (plan != null) {
                            String recommendation = service.getRecommendationOfTheMonth();
                            out.write("Recommendation from " + service.getName() + " to " + 
                                    user.getName() + ": " + recommendation + "\n");
                        }
                    }
                }
            }

            out.write("\n=== End of Simulation ===\n");
        } catch (IOException e) {
            throw new RuntimeException("Error writing simulation file", e);
        }
    }

    private void initializeSubscriptions(BufferedWriter out) throws IOException {
        Service meme = serviceMap.get("MemeFlix");
        Service moma = serviceMap.get("Momazon");
        Service spoo = serviceMap.get("Spootify");
        Service thisney = serviceMap.get("ThisneyPlus");
        Service hvo = serviceMap.get("HvoMax");

        // Alicia - all services with most expensive plan
        users.get(0).subscribe(meme, new Plan(new FixedPayment(200.0, "MemeFlix for 4 devices")), out);
        users.get(0).subscribe(moma, new Plan(new FixedPayment(150.0, "Momazon Premium")), out);
        users.get(0).subscribe(spoo, new Plan(new FixedPayment(80.0, "Spootify Premium")), out);
        users.get(0).subscribe(thisney, new Plan(new PaymentPromotion(160.0, 130.0, 3, "Thisney Plus")), out);
        users.get(0).subscribe(hvo, new Plan(new PaymentPromotion(140.0, 0.0, 3, "HVO Max")), out);

        // Bob - all services with most expensive plan
        users.get(1).subscribe(meme, new Plan(new FixedPayment(200.0, "MemeFlix for 4 devices")), out);
        users.get(1).subscribe(moma, new Plan(new FixedPayment(150.0, "Momazon Premium")), out);
        users.get(1).subscribe(spoo, new Plan(new FixedPayment(80.0, "Spootify Premium")), out);
        users.get(1).subscribe(thisney, new Plan(new PaymentPromotion(160.0, 130.0, 3, "Thisney Plus")), out);
        users.get(1).subscribe(hvo, new Plan(new PaymentPromotion(140.0, 0.0, 3, "HVO Max")), out);

        // César - only Thisney+ and HVO Max
        users.get(2).subscribe(thisney, new Plan(new PaymentPromotion(160.0, 130.0, 3, "Thisney Plus")), out);
        users.get(2).subscribe(hvo, new Plan(new PaymentPromotion(140.0, 0.0, 3, "HVO Max")), out);

        // Diego - HVO Max, Momazon Premium, Spootify Free
        users.get(3).subscribe(hvo, new Plan(new PaymentPromotion(140.0, 0.0, 3, "HVO Max")), out);
        users.get(3).subscribe(moma, new Plan(new FixedPayment(150.0, "Momazon Premium")), out);
        users.get(3).subscribe(spoo, new Plan(new FreePayment()), out);

        // Erika - MemeFlix 4 devices, Spootify Free, HVO Max
        users.get(4).subscribe(meme, new Plan(new FixedPayment(200.0, "MemeFlix for 4 devices")), out);
        users.get(4).subscribe(spoo, new Plan(new FreePayment()), out);
        users.get(4).subscribe(hvo, new Plan(new PaymentPromotion(140.0, 0.0, 3, "HVO Max")), out);

        // Fausto - Thisney+ and HVO Max
        users.get(5).subscribe(thisney, new Plan(new PaymentPromotion(160.0, 130.0, 3, "Thisney Plus")), out);
        users.get(5).subscribe(hvo, new Plan(new PaymentPromotion(140.0, 0.0, 3, "HVO Max")), out);
    }

    private void applySchedule(int month, BufferedWriter out) throws IOException {
        User bob    = find("Bob");
        User cesar  = find("César");
        User diego  = find("Diego");
        User erika  = find("Erika");
        User fausto = find("Fausto");

        // Bob
        if (month == 4) {
            bob.cancelSubscription(serviceMap.get("ThisneyPlus"), out);
            bob.cancelSubscription(serviceMap.get("HvoMax"), out);
            out.write("Bob cancelled ThisneyPlus and HvoMax.\n");
        }
        if (month == 5) {
            bob.cancelSubscription(serviceMap.get("MemeFlix"), out);
            bob.cancelSubscription(serviceMap.get("Momazon"), out);
            out.write("Bob cancelled MemeFlix and Momazon.\n");
        }

        // César
        if (month == 7) {
            cesar.subscribe(serviceMap.get("Spootify"), new Plan(new FixedPayment(80.0, "Spootify Premium")), out);
            out.write("César subscribed to Spootify Premium.\n");
        }

        // Diego
        if (month == 6) {
            diego.subscribe(serviceMap.get("ThisneyPlus"), new Plan(new PaymentPromotion(160.0, 130.0, 3, "Thisney Plus")), out);
            out.write("Diego subscribed to ThisneyPlus.\n");
        }
        if (month == 7) {
            diego.subscribe(serviceMap.get("MemeFlix"), new Plan(new FixedPayment(120.0, "MemeFlix for 1 device")), out);
            diego.cancelSubscription(serviceMap.get("Momazon"), out);
            diego.subscribe(serviceMap.get("Spootify"), new Plan(new FixedPayment(80.0, "Spootify Premium")), out);
            out.write("Diego subscribed to MemeFlix(1), upgraded Spootify to Premium, cancelled Momazon.\n");
        }

        // Erika
        if (month == 3) {
            erika.cancelSubscription(serviceMap.get("HvoMax"), out);
            erika.subscribe(serviceMap.get("ThisneyPlus"), new Plan(new PaymentPromotion(160.0, 130.0, 3, "Thisney Plus")), out);
            out.write("Erika cancelled HvoMax, subscribed to ThisneyPlus.\n");
        }
        if (month == 6) {
            erika.cancelSubscription(serviceMap.get("MemeFlix"), out);
            erika.cancelSubscription(serviceMap.get("Spootify"), out);
            erika.cancelSubscription(serviceMap.get("ThisneyPlus"), out);
            out.write("Erika cancelled all services.\n");
        }
        if (month == 10) {
            erika.subscribe(serviceMap.get("Momazon"), new Plan(new FixedPayment(150.0, "Momazon Premium")), out);
            erika.subscribe(serviceMap.get("HvoMax"), new Plan(new PaymentPromotion(140.0, 0.0, 3, "HVO Max")), out);
            erika.subscribe(serviceMap.get("ThisneyPlus"), new Plan(new PaymentPromotion(160.0, 130.0, 3, "Thisney Plus")), out);
            out.write("Erika subscribed again to Momazon Premium, HvoMax and ThisneyPlus.\n");
        }

        // Fausto
        if (month == 4) {
            fausto.cancelSubscription(serviceMap.get("ThisneyPlus"), out);
            fausto.cancelSubscription(serviceMap.get("HvoMax"), out);
            fausto.subscribe(serviceMap.get("MemeFlix"), new Plan(new FixedPayment(120.0, "MemeFlix for 1 device")), out);
            out.write("Fausto switched to MemeFlix(1).\n");
        }
        if (month == 5) {
            fausto.subscribe(serviceMap.get("ThisneyPlus"), new Plan(new PaymentPromotion(160.0, 130.0, 3, "Thisney Plus")), out);
            fausto.subscribe(serviceMap.get("HvoMax"), new Plan(new PaymentPromotion(140.0, 0.0, 3, "HVO Max")), out);
            out.write("Fausto subscribed again to ThisneyPlus and HvoMax.\n");
        }
        if (month == 6) {
            fausto.cancelSubscription(serviceMap.get("ThisneyPlus"), out);
            fausto.cancelSubscription(serviceMap.get("HvoMax"), out);
            fausto.cancelSubscription(serviceMap.get("MemeFlix"), out);
            out.write("Fausto cancelled all services again.\n");
        }
    }

    private User find(String name) {
        return users.stream()
                .filter(u -> u.getName().equals(name))
                .findFirst()
                .orElseThrow();
    }
}