package theatricalplays.playtype;

public sealed interface PlayType permits Comedy, Tragedy {

    int getPriceInUsd(int audience);
    int getVolumeCredits(int audience);

    static PlayType fromId(String id) {
        return switch (id) {
            case "tragedy" -> Tragedy.INSTANCE;
            case "comedy" -> Comedy.INSTANCE;
            default -> throw new Error("unknown type: " + id);
        };
    }

}
