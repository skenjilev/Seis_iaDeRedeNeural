package levi2;

import org.json.JSONObject;
import levi.Neuronio;

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
                    default: System.out.println("erro, fale denovo a carta"); i--;
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
            if (rng <= resfinal / 2) {
                System.out.println("apostar");
            } else {
                System.out.println("Carta da mesa porfavor ('n' para nenhuma)");
                String carta = scanner.nextLine();
                int cartaV;
                switch (carta) {
                    case "4": cartaV = 1; break;
                    case "5": cartaV = 2; break;
                    case "6": cartaV = 3; break;
                    case "7": cartaV = 4; break;
                    case "q": cartaV = 5; break;
                    case "j": cartaV = 6; break;
                    case "k": cartaV = 7; break;
                    case "a", "1": cartaV = 8; break;
                    case "2": cartaV = 9; break;
                    case "3": cartaV = 10; break;
                    case "ouro", "ouros": cartaV = 11; break;
                    case "espada", "espadas": cartaV = 12; break;
                    case "copa", "copas": cartaV = 13; break;
                    case "zap", "pau", "paus": cartaV = 14; break;
                    case "n": cartaV = 0; break;
                    default: cartaV = 0; System.out.println("erro pegando \"Nenhuma\"");
                }
                if (cartaV > valores[0]) {

                }
            }
    }
}
