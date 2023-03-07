package theatricalplays.playtype;

public enum Tragedy implements PlayType {
    INSTANCE;
    @Override
    public int getPriceInUsd(int audience) {
        final int basePriceUsd = 400;
        if (audience <= 30) {
            return basePriceUsd;
        }
        return basePriceUsd + 10 * (audience - 30);
    }

    @Override
    public int getVolumeCredits(int audience) {
        return Math.max(audience - 30, 0);
    }
}
