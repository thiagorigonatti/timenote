package br.com.thecoders.timenote;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.TimeZone;

public interface Notable {

    void setDateTime(ZonedDateTime dateTime);

    Locale getLocale();

    void setLocale(Locale locale);

    TimeZone getTimeZone();

    void setTimeZone(ZoneId zoneId);

    void setPattern(String pattern);

    String getPattern();

}
