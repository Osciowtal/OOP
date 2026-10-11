package ru.nsu.iyaroslavtsev.task113;

import java.util.Scanner;

public class Main {
    public static int variabled = 0;
    public static int want_to_assign = 0;

    public static void main(String[] args) {
        // parsing from string
        // receiving expression from stdin
        System.out.println("Rules:");
        System.out.println("1. All binary operations must be wrapped with '(' + ')'");
        System.out.println("2. Var names are allowed to consist from:");
        System.out.println("Letters | Digits | Underscores\n");
        System.out.println("Insert your expression:");

        Scanner scanner = new Scanner(System.in);
        String str_expr = scanner.nextLine();
        Expression parsed = Parser.parse(str_expr);

        String assignments = new String();
        if (variabled == 1) {
            System.out.println("Do you want to specify values for variables?");
            System.out.println("If so, insert \"1\"");
            want_to_assign = Integer.parseInt(scanner.nextLine().trim());
            if (want_to_assign == 1) {
                System.out.println("Specify in this format: name=val;name=val;...");
                assignments = scanner.nextLine();
            }
        }

        System.out.println("What would you like to compute:");
        System.out.println("[1] Value\n[2] Derivative");
        int operation = Integer.parseInt(scanner.nextLine().trim());
        double res;
        if (variabled == 0) {
            switch (operation) {
                case 1:
                    res = parsed.eval(assignments);
                    System.out.println(res);
                    break;
                case 2:
                    System.out.println("No matter by what: always 0.0");
                    break;
                default:
                    System.out.println("Unrecognized option :(");
                    break;
            }
        } else {
            switch (operation) {
                case 1:
                    res = parsed.eval(assignments);
                    System.out.println(res);
                    break;
                case 2:
                    System.out.println("Specify by what to differentiate:");
                    System.out.println("d(expr) / d( ? )");
                    String var = scanner.nextLine();
                    Expression dexpr = parsed.derivative(var);
                    dexpr.print();
                    break;
            }
        }
        return;
    }
}