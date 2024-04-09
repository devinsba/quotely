package com.briandevins.quotely;

public class Main {
    public static void main(String[] args) {
        if (args.length > 1) {
            System.err.println("quotely expects a 0 or 1 arguments with valid arguments being English or Russian");
        }

        Quotely quotely = new Quotely(new ForismaticService());

        String language = "English";
        if (args.length == 1) {
            language = args[0];
        }

        String output = null;
        try {
            output = quotely.getQuoteAndAttribution(language);
        } catch (RuntimeException e) {
            if (e.getCause() == null) {
                System.err.println(e.getMessage());
            } else {
                System.err.println(e.getCause().getMessage());
            }
            return;
        }
        System.out.println(output);
    }
}