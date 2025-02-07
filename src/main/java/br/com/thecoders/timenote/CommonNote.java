package br.com.thecoders.timenote;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.TimeZone;

public final class CommonNote {

    private CommonNote() {
        throw new AssertionError("Instantiation of an utility class");
    }

    public static Notable with(Notable notable, TimeNoteFormat timeNoteFormat) {

        switch (timeNoteFormat) {

            case TINY: {
                notable.setPattern(new TinyFormat().pattern(notable.getLocale()));
                break;
            }

            case SMALL: {
                notable.setPattern(new SmallFormat().pattern(notable.getLocale()));
                break;
            }

            case NORMAL: {
                notable.setPattern(new NormalFormat().pattern(notable.getLocale()));
                break;
            }

            case BIG: {
                notable.setPattern(new BigFormat().pattern(notable.getLocale()));
                break;
            }

            case EXTENDED:
                notable.setPattern(new ExtendedFormat().pattern(notable.getLocale()));
        }
        return notable;
    }

    public static Notable with(Notable notable, String pattern) {
        notable.setPattern(pattern);
        return notable;
    }

    public static Notable with(Notable notable, FormatSelectable formatSelectable) {
        notable.setPattern(formatSelectable.pattern(notable.getLocale()));
        return notable;
    }


    public static Notable of(Notable notable, ZonedDateTime zonedDateTime) {
        notable.setDateTime(zonedDateTime);
        return notable;
    }

    public static Notable of(Notable notable, LocalDateTime localDateTime) {
        notable.setDateTime(localDateTime.atZone(notable.getTimeZone().toZoneId()));
        return notable;
    }


    public static Notable at(Notable notable, TimeZone timeZone) {
        notable.setTimeZone(timeZone.toZoneId());
        return notable;
    }

    public static Notable at(Notable notable, ZoneIdExtra zoneIdExtra) {
        notable.setTimeZone(zoneIdExtra.getZoneId());
        return notable;
    }

    public static Notable from(Notable notable, Locale locale) {

        notable.setLocale(locale);

        if (notable.getPattern() == null)
            notable.setPattern(new NormalFormat().pattern(notable.getLocale()));
        return notable;
    }
}
