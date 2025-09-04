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
 * Clase principal que simula el comportamiento de usuarios y servicios
 * de streaming durante un año (12 meses).
 * <p>
 * Crea instancias de varios servicios (MemeFlix, Spootify, Momazon, ThisneyPlus, HvoMax),
 * registra usuarios y suscripciones, aplica cambios mensuales en las suscripciones
 * y genera recomendaciones de contenido para cada usuario.
 * </p>
 * <p>
 * La simulación escribe todos los eventos (cobros, suscripciones, cancelaciones y recomendaciones)
 * en un archivo de texto llamado {@code simulation_output.txt}.
 * </p>
 * 
 * Ejemplo de uso:
 * <pre>
 *     Simulator simulator = new Simulator();
 *     simulator.runSimulation();
 * </pre>
 * 
 * @author César
 * @version 1.0
 */
public class Simulator {

    private final List<User> users = new ArrayList<>();
    private final List<Service> services = new ArrayList<>();
    private final Map<String, Service> serviceMap = new HashMap<>();
    private final DecimalFormat moneyFmt = new DecimalFormat("$0.00");

    /**
     * Constructor de {@code Simulator}.
     * <p>
     * Inicializa los servicios de streaming, los usuarios y registra las
     * instancias de cada servicio en un mapa para acceso rápido por nombre.
     * </p>
     */
    public Simulator() {
        Service meme = new MemeFlix();
        Service moma = new Momazon();
        Service spoo = new Spootify();
        Service thisney = new ThisneyPlus();
        Service hvo = new HvoMax();

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

    /**
     * Ejecuta la simulación completa durante 12 meses.
     * <p>
     * Cada mes se aplican cambios de suscripción según la programación,
     * se cobran los servicios a los usuarios y se generan recomendaciones de contenido.
     * Todos los eventos se escriben en {@code simulation_output.txt}.
     * </p>
     */
    public void runSimulation() {
        try (BufferedWriter out = new BufferedWriter(new FileWriter("simulation_output.txt"))) {
            initializeSubscriptions(out);

            out.write("=== Streaming Services Simulation ===\n");

            for (int month = 1; month <= 12; month++) {
                out.write("\n------------------------------\n");
                out.write("Month " + month + "\n");
                out.write("------------------------------\n");

                applySchedule(month, out);

                for (Service service : services) {
                    out.write("\n --- " + service.getName() + " charging ---\n");
                    service.chargeSubscribers(out);
                    
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

    /**
     * Inicializa las suscripciones de los usuarios al inicio de la simulación.
     * 
     * @param out {@link BufferedWriter} donde se registran los eventos de suscripción
     * @throws IOException si ocurre un error al escribir en el archivo
     */
    private void initializeSubscriptions(BufferedWriter out) throws IOException {
        Service meme = serviceMap.get("MemeFlix");
        Service moma = serviceMap.get("Momazon");
        Service spoo = serviceMap.get("Spootify");
        Service thisney = serviceMap.get("ThisneyPlus");
        Service hvo = serviceMap.get("HvoMax");

        users.get(0).subscribe(meme, new Plan(new FixedPayment(200.0, "MemeFlix for 4 devices")), out);
        users.get(0).subscribe(moma, new Plan(new FixedPayment(150.0, "Momazon Premium")), out);
        users.get(0).subscribe(spoo, new Plan(new FixedPayment(80.0, "Spootify Premium")), out);
        users.get(0).subscribe(thisney, new Plan(new PaymentPromotion(160.0, 130.0, 3, "Thisney Plus")), out);
        users.get(0).subscribe(hvo, new Plan(new PaymentPromotion(140.0, 0.0, 3, "HVO Max")), out);

        users.get(1).subscribe(meme, new Plan(new FixedPayment(200.0, "MemeFlix for 4 devices")), out);
        users.get(1).subscribe(moma, new Plan(new FixedPayment(150.0, "Momazon Premium")), out);
        users.get(1).subscribe(spoo, new Plan(new FixedPayment(80.0, "Spootify Premium")), out);
        users.get(1).subscribe(thisney, new Plan(new PaymentPromotion(160.0, 130.0, 3, "Thisney Plus")), out);
        users.get(1).subscribe(hvo, new Plan(new PaymentPromotion(140.0, 0.0, 3, "HVO Max")), out);

        users.get(2).subscribe(thisney, new Plan(new PaymentPromotion(160.0, 130.0, 3, "Thisney Plus")), out);
        users.get(2).subscribe(hvo, new Plan(new PaymentPromotion(140.0, 0.0, 3, "HVO Max")), out);

        users.get(3).subscribe(hvo, new Plan(new PaymentPromotion(140.0, 0.0, 3, "HVO Max")), out);
        users.get(3).subscribe(moma, new Plan(new FixedPayment(150.0, "Momazon Premium")), out);
        users.get(3).subscribe(spoo, new Plan(new FreePayment()), out);

        users.get(4).subscribe(meme, new Plan(new FixedPayment(200.0, "MemeFlix for 4 devices")), out);
        users.get(4).subscribe(spoo, new Plan(new FreePayment()), out);
        users.get(4).subscribe(hvo, new Plan(new PaymentPromotion(140.0, 0.0, 3, "HVO Max")), out);

        users.get(5).subscribe(thisney, new Plan(new PaymentPromotion(160.0, 130.0, 3, "Thisney Plus")), out);
        users.get(5).subscribe(hvo, new Plan(new PaymentPromotion(140.0, 0.0, 3, "HVO Max")), out);
    }
    
    /**
     * Aplica cambios de suscripción específicos de cada mes.
     * <p>
     * Incluye cancelaciones, nuevas suscripciones y upgrades.
     * </p>
     * 
     * @param month mes actual de la simulación (1 a 12)
     * @param out {@link BufferedWriter} donde se registran los cambios
     * @throws IOException si ocurre un error al escribir en el archivo
     */
    private void applySchedule(int month, BufferedWriter out) throws IOException {
        User bob    = find("Bob");
        User cesar  = find("César");
        User diego  = find("Diego");
        User erika  = find("Erika");
        User fausto = find("Fausto");

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

        if (month == 7) {
            cesar.subscribe(serviceMap.get("Spootify"), new Plan(new FixedPayment(80.0, "Spootify Premium")), out);
            out.write("César subscribed to Spootify Premium.\n");
        }

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
    /**
     * Busca un usuario por su nombre.
     * 
     * @param name nombre del usuario a buscar
     * @return la instancia de {@link User} correspondiente
     * @throws java.util.NoSuchElementException si no se encuentra un usuario con ese nombre
     */
    private User find(String name) {
        return users.stream()
                .filter(u -> u.getName().equals(name))
                .findFirst()
                .orElseThrow();
    }
}