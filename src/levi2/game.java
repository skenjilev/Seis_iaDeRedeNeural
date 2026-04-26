package levi2;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.random.RandomGenerator;

public class game {
    public static void main(String[] args) throws IOException {
            Path caminho = Paths.get("src", "levi", "pesos.json");
            String content = new String(Files.readAllBytes(caminho));
            JSONObject json = new JSONObject(content);
            RandomGenerator generator = RandomGenerator.of("L128X256MixRandom");


            double rppa;
            double rppb;
            double[] valores = new double[4];

            Neuronio neuronioa = new Neuronio();
            Neuronio neuroniob = new Neuronio();

            Scanner scanner = new Scanner(System.in);
            String[] cartas = new String[3];
            System.out.println("Cartas:");
            cartas[0] = scanner.nextLine();
            cartas[1] = scanner.nextLine();
            cartas[2] = scanner.nextLine();

            for (int i = 0; i < 3; i++) {
                switch (cartas[i].toLowerCase()) {
                    case "4": valores[i] = 1; break;
                    case "5": valores[i] = 2; break;
                    case "6": valores[i] = 3; break;
                    case "7": valores[i] = 4; break;
                    case "q": valores[i] = 5; break;
                    case "j": valores[i] = 6; break;
                    case "k": valores[i] = 7; break;
                    case "a", "1": valores[i] = 8; break;
                    case "2": valores[i] = 9; break;
                    case "3": valores[i] = 10; break;
                    case "ouro", "ouros": valores[i] = 11; break;
                    case "espada", "espadas": valores[i] = 12; break;
                    case "copa", "copas": valores[i] = 13; break;
                    case "zap", "pau", "paus": valores[i] = 14; break;
                }
            }
            System.out.println("Parceiro");
            valores[3] = Double.parseDouble(scanner.nextLine());

            neuronioa.Neuroniar(valores[0], valores[1], valores[2], valores[3], "a");
            neuroniob.Neuroniar(valores[0], valores[1], valores[2], valores[3], "b");

            double ra = neuronioa.processar();
            double rpa = json.optDouble("rpa", Math.random());

            double rb = neuroniob.processar();
            double rpb = json.optDouble("rpb", Math.random());

            rppa = ra * rpa;
            rppb = rb * rpb;
            double resfinal = rppa + rppb;

            int rng = generator.nextInt(100) + 1;
            System.out.println(resfinal);
            System.out.println(' ');
            if (rng <= resfinal) {
                System.out.println("apostar");
            } else if (rng <= resfinal * 2) {
                System.out.println("carta alta");
            } else {
                System.out.println("carta baixa");
            }
    }
}
