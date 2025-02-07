package br.com.thecoders.timenote;

import java.time.ZoneId;

public class ZoneIdExtra {

    private final ZoneId zoneId;

    public ZoneId getZoneId() {
        return zoneId;
    }

    public static final ZoneIdExtra SP = new ZoneIdExtra(ZoneId.of("America/Sao_Paulo"));


    public ZoneIdExtra(ZoneId zoneId) {
        this.zoneId = zoneId;
    }
}
