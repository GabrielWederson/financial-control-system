package br.com.gabrielwederson.financial.util;

import br.com.gabrielwederson.financial.model.LaunchData;
import br.com.gabrielwederson.financial.model.Type;
import java.io.*;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.*;
import java.nio.file.*;

public class ReportDAO {
static String path = "C:\\Users\\gabri\\OneDrive\\Documentos\\financial-control-system\\data";

    public static void savedata(LaunchData launchdata) {

        if (launchdata.getType() == Type.EXPENSE) {
            String line = launchdata.getId() + "," + launchdata.getName() + "," + launchdata.getValue() + "," + launchdata.getType();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(path + "\\expenses.csv", true))) {
                bw.write(line);
                bw.newLine();

                System.out.println("Data sent to the file expenses.csv, check the 'data' folder");
                System.out.println(" ");
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } else if (launchdata.getType() == Type.REVENUE) {
            String line = launchdata.getId() + "," + launchdata.getName() + "," + launchdata.getValue() + "," + launchdata.getType();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(path + "\\revenues.csv", true))) {
                bw.write(line);
                bw.newLine();

                System.out.println("Data sent to the file revenues.csv, check the 'data' folder");
                System.out.println(" ");
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Have a error, please restart the system");
            System.exit(0);
        }
    }



    public static void getsum(String path) {
        double totalRevenues = 0.0;
        double totalExpenses = 0.0;

        totalRevenues = readCsvTotal(path + "\\revenues.csv");
        totalExpenses = readCsvTotal(path + "\\expenses.csv");

        double result = totalRevenues - totalExpenses;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path + "\\sum.csv"))) {
            bw.write(String.format("%.2f", result));
        } catch (IOException e) {
            System.out.println("Erro in sum.csv: " + e.getMessage());
        }

        System.out.println("Revenues: " + totalRevenues + " | Expenses: " + totalExpenses + " | Result: " + result);
    }

    private static double readCsvTotal(String filepath) {
        double total = 0.0;

        File file = new File(filepath);
        if (!file.exists()) return total;

        try (CSVReader reader = new CSVReader(new FileReader(filepath))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                if (line.length >= 3) {
                    try {
                        // remove espaços e caracteres invisíveis
                        double valor = Double.parseDouble(line[2].trim().replaceAll("[^0-9.\\-]", ""));
                        total += valor;
                    } catch (NumberFormatException e) {
                        System.out.println("Error" + filepath + ": '" + line[2] + "'");
                    }
                }
            }
        } catch (IOException | CsvValidationException e) {
            System.out.println("Error" + filepath + ": " + e.getMessage());
        }

        return total;
    }
}
