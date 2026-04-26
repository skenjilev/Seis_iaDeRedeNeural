package levi;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        while (true) {
            Path caminho = Paths.get("src", "levi", "pesos.json");
            String content = new String(Files.readAllBytes(caminho));
            JSONObject json = new JSONObject(content);

            Neuronio neua = null;
            Neuronio neub = null;

            double res;
            double pesoa;
            double pesob;
            double rppa;
            double rppb;
            double aValor;
            double bValor;
            double cValor;
            double dValor;

            Neuronio neuronioa = new Neuronio();
            Neuronio neuroniob = new Neuronio();

            int traindur;
            int zeros = 0;
            int pertos = 0;
            int longe = 0;
            int loops = 0;

            Scanner scanner = new Scanner(System.in);
            String traindurs = scanner.nextLine();
            try {
                if (traindurs.equalsIgnoreCase("end")
                        | traindurs.equalsIgnoreCase("fim")
                        | traindurs.equalsIgnoreCase("parar")
                        | traindurs.equalsIgnoreCase("para"))
                {
                    break;
                } else {
                    traindur = Integer.parseInt(traindurs);
                }
            } catch (Exception e) {
                Exception exception = new Exception("Erro ao pegar a duração do treino");
                System.out.println(exception.getMessage());
                System.out.println(' ');
                traindur = 1;
            }


            for (int loop = 1; loop <= traindur; loop++) {
                switch ((int) (Math.random() * 10)) {
                    case 1:
                        aValor = 14;
                        bValor = 9;
                        cValor = 2;
                        dValor = 25;
                        res = 75;
                        break;
                    case 2:
                        aValor = 2;
                        bValor = 12;
                        cValor = 14;
                        dValor = 0;
                        res = 100;
                        break;
                    case 3:
                        aValor = 10;
                        bValor = 1;
                        cValor = 10;
                        dValor = 10;
                        res = 80;
                        break;
                    case 4:
                        aValor = 9;
                        bValor = 7;
                        cValor = 5;
                        dValor = 50;
                        res = 50;
                        break;
                    case 5:
                        aValor = 13;
                        bValor = 1;
                        cValor = 5;
                        dValor = 75;
                        res = 90;
                        break;
                    case 6:
                        aValor = 2;
                        bValor = 5;
                        cValor = 2;
                        dValor = 0;
                        res = 10;
                        break;
                    case 7:
                        aValor = 1;
                        bValor = 4;
                        cValor = 7;
                        dValor = 50;
                        res = 30;
                        break;
                    default:
                        aValor = 12;
                        bValor = 9;
                        cValor = 3;
                        dValor = 35;
                        res = 70;
                        break;
                }


                neuronioa.Neuroniar(aValor, bValor, cValor, dValor, "a");
                neuroniob.Neuroniar(aValor, bValor, cValor, dValor, "b");

                double ra = neuronioa.processar();
                double rpa = json.optDouble("rpa", Math.random());

                double rb = neuroniob.processar();
                double rpb = json.optDouble("rpb", Math.random());

                rppa = ra * rpa;
                rppb = rb * rpb;
                double resfinal = rppa + rppb;

                double erro = res - resfinal;
                double erroManeiroa = erro * rpa;
                double erroManeirob = erro * rpb;

                neuronioa.consertar(erroManeiroa);
                neuroniob.consertar(erroManeirob);

                pesoa = rpa + 0.0001 * erro * ra;
                pesob = rpb + 0.0001 * erro * rb;

                if (pesoa > 20.0) pesoa = 20.0;
                if (pesoa < -20.0) pesoa = -20.0;
                if (pesob > 20.0) pesob = 20.0;
                if (pesob < -20.0) pesob = -20.0;

                json.put("rpa", pesoa);
                json.put("rpb", pesob);

                if ((int) (erro) == 0) {
                    zeros++;
                }
                boolean bas = (int) (Math.sqrt(Math.pow(erro, 2))) < 19;
                boolean aas = (int) (Math.sqrt(Math.pow(erro, 2))) != 0;
                if (bas && aas) {
                    pertos++;
                }
                if ((int) (Math.sqrt(Math.pow(erro, 2))) >= 20) {
                    longe++;
                }

                System.out.println("Loop: " + loop +
                        " | Previsão: " + resfinal +
                        " | Erro: " + erro +
                        " | Resultado: " + res +
                        " | Zeros: " + zeros +
                        " | Pertos: " + pertos +
                        " | Longes: " + longe);
                neua = neuronioa;
                neub = neuroniob;
                loops++;
            }

            double percAcerto = (loops > 0) ? ((double) zeros / loops) * 100 : 0;
            double percPerto = (loops > 0) ? ((double) pertos / loops) * 100 : 0;
            double percLonge = (loops > 0) ? ((double) longe / loops) * 100 : 0;

            System.out.println("Acertos: " + percAcerto + "%" + " | Pertos: "
                    + percPerto + "%" + " | Longes: " + percLonge + "%");

            assert neua != null;
            double[] neuar = neua.finalizar();
            double[] neubr = neub.finalizar();

            json.put("p1a", neuar[0]);
            json.put("p2a", neuar[1]);
            json.put("p3a", neuar[2]);
            json.put("p4a", neuar[4]);
            json.put("v1a", neuar[3]);

            json.put("p1b", neubr[0]);
            json.put("p2b", neubr[1]);
            json.put("p3b", neubr[2]);
            json.put("p4b", neubr[4]);
            json.put("v1b", neubr[3]);

            FileWriter fileWriter = new FileWriter(caminho.toFile());
            fileWriter.write(json.toString(4));
            fileWriter.close();
        }
    }
}
