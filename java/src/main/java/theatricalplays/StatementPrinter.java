package theatricalplays;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

    public String print(Invoice invoice, Map<String, Play> plays) {
        int totalPriceInUsd = 0;
        int volumeCredits = 0;
        String result = String.format("Statement for %s\n", invoice.customer);

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);

        for (Performance performance : invoice.performances) {
            Play play = plays.get(performance.playID);
            int priceInUsd = 0;

            switch (play.type) {
                case "tragedy":
                    priceInUsd = 400;
                    if (performance.audience > 30) {
                        priceInUsd += 10 * (performance.audience - 30);
                    }
                    break;
                case "comedy":
                    priceInUsd = 300;
                    if (performance.audience > 20) {
                        priceInUsd += 100 + 5 * (performance.audience - 20);
                    }
                    priceInUsd += 3 * performance.audience;
                    break;
                default:
                    throw new Error("unknown type: ${play.type}");
            }

            // add volume credits
            volumeCredits += Math.max(performance.audience - 30, 0);
            // add extra credit for every ten comedy attendees
            if ("comedy".equals(play.type)) volumeCredits += performance.audience / 5;

            // print line for this order
            result += String.format("  %s: %s (%s seats)\n", play.name, currencyFormatter.format(priceInUsd), performance.audience);
            totalPriceInUsd += priceInUsd;
        }
        result += String.format("Amount owed is %s\n", currencyFormatter.format(totalPriceInUsd));
        result += String.format("You earned %s credits\n", volumeCredits);
        return result;
    }

}
