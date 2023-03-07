package theatricalplays.playtype;

public enum Comedy implements PlayType {
    INSTANCE;
    @Override
    public int getPriceInUsd(int audience) {
        final int basePrice = 300 + 3 * audience;
        if (audience <= 20) {
            return basePrice;
        }
        return basePrice + 100 + 5 * (audience - 20);
    }

    @Override
    public int getVolumeCredits(int audience) {
        return Math.max(audience - 30, 0) + audience / 5;
    }
}
