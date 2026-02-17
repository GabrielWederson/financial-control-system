package br.com.gabrielwederson.financial.util;

import br.com.gabrielwederson.financial.model.LaunchData;
import br.com.gabrielwederson.financial.model.Type;

import java.io.*;

public class ReportDAO {


    private static final String path = System.getenv("FILE_PATH");

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

    public void generatesum(){

        double totalRevenues = 0;
        double totalExpenses = 0;
        String linereader;

        boolean temRevenue = false;
        boolean temExpense = false;

        try(BufferedReader revenueReader = new BufferedReader(new FileReader(path + "\\revenues.csv"))){
            revenueReader.readLine();
            while ((linereader = revenueReader.readLine()) != null) {
                temRevenue = true;

                int first = linereader.indexOf(",");
                int second = linereader.indexOf(",", first + 1);
                int third = linereader.indexOf(",", second + 1);

                String valorString = linereader.substring(second + 1, third);
                totalRevenues += Double.parseDouble(valorString);
            }
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        try(BufferedReader expenseReader = new BufferedReader(new FileReader(path + "\\expenses.csv"))) {
            expenseReader.readLine();

            while ((linereader = expenseReader.readLine()) != null) {
                temExpense = true;

                int first = linereader.indexOf(",");
                int second = linereader.indexOf(",", first + 1);
                int third = linereader.indexOf(",", second + 1);

                String valorString = linereader.substring(second + 1, third);
                totalExpenses += Double.parseDouble(valorString);
            }
        } catch(IOException e){
            System.out.println("ERROR: " + e.getMessage());
        }

        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(path + "\\sum.csv", true))) {

            if (temRevenue && temExpense) {
                double total = totalRevenues - totalExpenses;
                String totalS = String.valueOf(total);

                bw.write(totalS);
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

    }
}