package theatricalplays;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

    public String print(Invoice invoice, Map<String, Play> plays) {
        int totalAmount = 0;
        int volumeCredits = 0;
        String result = String.format("Statement for %s\n", invoice.customer);

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);

        for (Performance performance : invoice.performances) {
            Play play = plays.get(performance.playID);
            int thisAmount = 0;

            switch (play.type) {
                case "tragedy":
                    thisAmount = 40000;
                    if (performance.audience > 30) {
                        thisAmount += 1000 * (performance.audience - 30);
                    }
                    break;
                case "comedy":
                    thisAmount = 30000;
                    if (performance.audience > 20) {
                        thisAmount += 10000 + 500 * (performance.audience - 20);
                    }
                    thisAmount += 300 * performance.audience;
                    break;
                default:
                    throw new Error("unknown type: ${play.type}");
            }

            // add volume credits
            volumeCredits += Math.max(performance.audience - 30, 0);
            // add extra credit for every ten comedy attendees
            if ("comedy".equals(play.type)) volumeCredits += Math.floor(performance.audience / 5);

            // print line for this order
            result += String.format("  %s: %s (%s seats)\n", play.name, numberFormat.format(thisAmount / 100), performance.audience);
            totalAmount += thisAmount;
        }
        result += String.format("Amount owed is %s\n", numberFormat.format(totalAmount / 100));
        result += String.format("You earned %s credits\n", volumeCredits);
        return result;
    }

}
