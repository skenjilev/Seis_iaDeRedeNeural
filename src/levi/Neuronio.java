package levi;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Neuronio {
    Path caminho = Paths.get("src", "levi", "pesos.json");
    String content = new String(Files.readAllBytes(caminho));
    JSONObject json = new JSONObject(content);

    double Valor1;
    double Valor2;
    double Valor3;
    double Valorv;
    double Valor4;

    double Valor1a;
    double Valor2a;
    double Valor3a;
    double Valor4a;

    double Valor1aa;
    double Valor2aa;
    double Valor3aa;
    double Valor4aa;

    String id;

    public Neuronio() throws IOException {
    }

    public void Neuroniar(double aValor, double bValor, double cValor,double dValor, String id) {
        this.Valor1 = aValor;
        this.Valor2 = bValor;
        this.Valor3 = cValor;
        this.Valor4 = dValor;

        this.Valor1aa = aValor;
        this.Valor2aa = bValor;
        this.Valor3aa = cValor;
        this.Valor4aa = cValor;

        this.id = id;
    }

    public double processar() {
        try {
            Valor1a = json.getDouble("p" + id);
            Valor1 *= Valor1a;
        } catch (Exception e) {
            Valor1 = Math.random();
            System.out.println("erro ao tentar pegar p" + id);
        }
        try {
            Valor2a = json.getDouble("p" + id);
            Valor2 *= Valor2a;
        } catch (Exception e) {
            Valor2 = Math.random();
            System.out.println("erro ao tentar pegar p" + id);
        }
        try {
            Valor3a = json.getDouble("p" + id);
            Valor3 *= Valor3a;
        } catch (Exception e) {
            Valor3 = Math.random();
            System.out.println("erro ao tentar pegar p" + id);
        }
        try {
            Valor4a = json.getDouble("p4" + id);
            Valor4 *= Valor4a;
        } catch (Exception e) {
            Valor4 = Math.random();
            System.out.println("erro ao tentar pegar p4" + id);
        }
        try {
            Valorv = json.getDouble("v1" + id);
        } catch (Exception e) {
            Valorv = Math.random();
            System.out.println("erro ao tentar pegar v1" + id);
        }
        return Valor1 + Valor2 + Valor3 + Valorv;
    }
    public void consertar(double erro) {
        json.put("p" + id, ((Valor1a + Valor2a + Valor3a) / 3) + 0.00001 * erro * ((Valor1aa + Valor2aa + Valor3aa) / 3));
        json.put("p4" + id, Valor4a + 0.00001 * erro * Valor4aa);
        json.put("v1" + id, Valorv + 0.00001 * erro);
    }
    public double[] finalizar() {
        double[] resposta = new double[3];
        resposta[0] = json.getDouble("p" + id);
        resposta[1] = json.getDouble("v1" + id);
        resposta[2] = json.getDouble("p4" + id);
        return resposta;
    }
}
