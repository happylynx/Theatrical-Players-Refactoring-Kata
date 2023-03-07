package theatricalplays;

import theatricalplays.playtype.PlayType;

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
            final PlayType playType = PlayType.fromId(play.type);
            volumeCredits += playType.getVolumeCredits(performance.audience);
            final int priceInUsd = playType.getPriceInUsd(performance.audience);

            // print line for this order
            result += String.format("  %s: %s (%s seats)\n", play.name, currencyFormatter.format(priceInUsd), performance.audience);
            totalPriceInUsd += priceInUsd;
        }
        result += String.format("Amount owed is %s\n", currencyFormatter.format(totalPriceInUsd));
        result += String.format("You earned %s credits\n", volumeCredits);
        return result;
    }

}
