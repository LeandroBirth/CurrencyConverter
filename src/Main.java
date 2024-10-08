import com.conversorDeMoedas.APIimporter;
import com.conversorDeMoedas.MoedaResponse;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String continuar;
        String idioma;


        System.out.println("Escolha o idioma / Choose your language:\n" +
                "1 - Português (PT-BR)\n"+
                "2 - English (ENG)");
        idioma = scanner.nextInt() == 1 ? "PT-BR" : "ENG";
        scanner.nextLine();

        System.out.println(idioma.equals("PT-BR") ?
                "\n"+
                "Bem-Vindo ao conversor de moedas!\n" +
                        "*********************************\n" +
                        "Esse conversor aceita:\n"+
                        "Reais (BRL)\n" +
                        "Dólares Americanos (USD)\n" +
                        "Dólares Canadenses (CAD)\n" +
                        "Euro (EUR)\n" +
                        "Libra Esterlina (GBP)\n"+
                        "Pesos Mexicanos (MXN)\n" +
                        "Pesos Argentinos (ARS)\n" +
                        "*********************************"
                : "\n" +
                "Welcome to the currency converter!\n"+
                        "*********************************\n" +
                        "This converter accepts:\n" +
                        "Brazilian Reais (BRL)\n" +
                        "US Dollars (USD)\n" +
                        "Canadian Dollars (CAD)\n"+
                        "Euros (EUR)\n" +
                        "British Pounds (GBP)\n" +
                        "Mexican Pesos (MXN)\n" +
                        "Argentinian Pesos (ARS)\n"+
                        "*********************************");

        do {
            System.out.print(idioma.equals("PT-BR") ? "Digite a moeda de origem (ex: USD): " : "Enter the base currency (e.g., USD): ");
            String acronimoMoeda1 = scanner.nextLine().toUpperCase();
            System.out.print(idioma.equals("PT-BR") ? "Digite a moeda para conversão (ex: BRL): " : "Enter the target currency (e.g., BRL): ");
            String acronimoMoeda2 = scanner.nextLine().toUpperCase();
            System.out.print(idioma.equals("PT-BR") ? "Digite o valor que deseja converter: " : "Enter the amount you want to convert: ");
            double valorParaConverter = scanner.nextDouble();
            scanner.nextLine();

            try {
                MoedaResponse moedaResponse = APIimporter.getMoedaResponse(acronimoMoeda1);
                double taxaConversao = moedaResponse.getConversion_rates().getRateFor(acronimoMoeda2);
                double valorConvertido = valorParaConverter * taxaConversao;
                System.out.println(idioma.equals("PT-BR") ?
                        "A taxa de conversão de " + acronimoMoeda1 + " para " + acronimoMoeda2 + " é: " + taxaConversao + "\nO valor convertido é: " + valorConvertido
                        :
                        "The conversion rate from " + acronimoMoeda1 + " to " + acronimoMoeda2 + " is: " + taxaConversao + "\nThe converted amount is: " + valorConvertido);

            } catch (NoSuchFieldException e) {
                System.out.println(idioma.equals("PT-BR") ?
                        "Moeda " + acronimoMoeda2 + " não encontrada."
                        :
                        "Currency " + acronimoMoeda2 + " not found.");
            } catch (IllegalAccessException e) {
                System.out.println(idioma.equals("PT-BR") ?
                        "Erro ao acessar a taxa de conversão: " + e.getMessage()
                        :
                        "Error accessing the conversion rate: " + e.getMessage());
            } catch (Exception e) {
                System.out.println(idioma.equals("PT-BR") ?
                        "Erro ao processar a requisição: " + e.getMessage()
                        :
                        "Error processing the request: " + e.getMessage());}
            System.out.println(idioma.equals("PT-BR") ? "Deseja realizar outra conversão? Digite sim ou nao:" : "Do you want to perform another conversion? Type yes or no:");
            continuar = scanner.nextLine().trim().toLowerCase();

        }
        while (continuar.equals(idioma.equals("PT-BR") ? "sim" : "yes"));

        System.out.println(idioma.equals("PT-BR") ? "Obrigado! Aplicação concluída." : "Thank you! Application finished.");
        scanner.close();
    }
}
