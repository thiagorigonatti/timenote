package br.com.thecoders.timenote;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;


public class TimeNote implements Notable {

    private ZonedDateTime dateTime;
    private TimeZone timeZone;
    private String pattern;
    private Locale locale;


    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }


    public Locale getLocale() {
        return locale;
    }

    @Override
    public void setLocale(Locale locale) {
        this.locale = locale;
    }


    public TimeZone getTimeZone() {
        return timeZone;
    }

    @Override
    public void setTimeZone(ZoneId zoneId) {
        this.timeZone = TimeZone.getTimeZone(zoneId);
    }


    public String getPattern() {
        return pattern;
    }

    @Override
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }


    private static final Map<String, String> REPLACEMENTS = new HashMap<>();

    static {
        REPLACEMENTS.put("<dddd>", "EEEE");
        REPLACEMENTS.put("<ddd>", "E");
        REPLACEMENTS.put("<dd>", "dd");
        REPLACEMENTS.put("<d>", "d");

        REPLACEMENTS.put("<MMMM>", "MMMM");
        REPLACEMENTS.put("<MMM>", "MMM");
        REPLACEMENTS.put("<MM>", "MM");
        REPLACEMENTS.put("<M>", "M");

        REPLACEMENTS.put("<YYYY>", "y");
        REPLACEMENTS.put("<yyyy>", "y");
        REPLACEMENTS.put("<YY>", "yy");
        REPLACEMENTS.put("<yy>", "yy");

        REPLACEMENTS.put("<HH>", "HH");
        REPLACEMENTS.put("<H>", "H");
        REPLACEMENTS.put("<hh>", "hh");
        REPLACEMENTS.put("<h>", "h");

        REPLACEMENTS.put("<mm>", "mm");
        REPLACEMENTS.put("<m>", "m");

        REPLACEMENTS.put("<SS>", "ss");
        REPLACEMENTS.put("<S>", "s");
        REPLACEMENTS.put("<ss>", "ss");
        REPLACEMENTS.put("<s>", "s");

        REPLACEMENTS.put("<a>", "a");

        REPLACEMENTS.put("<ms>", "SSS");
        REPLACEMENTS.put("<ns>", "n");

        REPLACEMENTS.put("<x>", "x");
        REPLACEMENTS.put("<xx>", "xx");
        REPLACEMENTS.put("<xxx>", "xxx");
        REPLACEMENTS.put("<xxxx>", "ZZZZ");

        REPLACEMENTS.put("<z>", "z");
        REPLACEMENTS.put("<zz>", "VV");
        REPLACEMENTS.put("<zzz>", "vvvv");
        REPLACEMENTS.put("<zzzz>", "zzzz");

    }


    private static String parsePattern(String pattern) {

        for (Map.Entry<String, String> stringStringEntry : REPLACEMENTS.entrySet()) {
            if (pattern.contains(stringStringEntry.getKey())) {
                pattern = pattern.replace(stringStringEntry.getKey(), stringStringEntry.getValue());
            }
        }
        return pattern;
    }


    public TimeNote of(ZonedDateTime zonedDateTime) {
        return (TimeNote) CommonNote.of(this, zonedDateTime);
    }

    public TimeNote of(LocalDateTime localDateTime) {
        return (TimeNote) CommonNote.of(this, localDateTime);
    }

    public TimeNote at(TimeZone timeZone) {
        return ((TimeNote) CommonNote.at(this, timeZone));
    }

    public TimeNote at(ZoneIdExtra zoneIdExtra) {
        return ((TimeNote) CommonNote.at(this, zoneIdExtra));
    }

    public TimeNote with(String pattern) {
        return (TimeNote) CommonNote.with(this, pattern);
    }

    public TimeNote with(FormatSelectable formatSelectable) {
        return (TimeNote) CommonNote.with(this, formatSelectable.pattern(this.locale));
    }

    public TimeNote with(TimeNoteFormat timeNoteFormat) {
        return (TimeNote) CommonNote.with(this, timeNoteFormat);
    }

    public TimeNote from(Locale locale) {
        return (TimeNote) CommonNote.from(this, locale);
    }

    public TimeNote from(LocaleExtra localeExtra) {
        return (TimeNote) CommonNote.from(this, localeExtra.getLocale());
    }


    public TimeNote(Builder builder) {
        this.dateTime = builder.dateTime;
        this.timeZone = builder.timeZone;
        this.pattern = builder.pattern;
        this.locale = builder.locale;
    }

    public static class Builder implements Notable {

        @Override
        public void setLocale(Locale locale) {
            this.locale = locale;
        }

        @Override
        public String getPattern() {
            return this.pattern;
        }

        @Override
        public void setTimeZone(ZoneId zoneId) {
            this.timeZone = TimeZone.getTimeZone(zoneId);
        }

        @Override
        public TimeZone getTimeZone() {
            return this.timeZone;
        }

        @Override
        public void setDateTime(ZonedDateTime dateTime) {
            this.dateTime = dateTime;
        }


        @Override
        public void setPattern(String pattern) {
            this.pattern = pattern;
        }

        @Override
        public Locale getLocale() {
            return this.locale;
        }

        private ZonedDateTime dateTime = ZonedDateTime.now();
        private TimeZone timeZone = TimeZone.getDefault();
        private Locale locale = Locale.getDefault();
        private String pattern = new NormalFormat().pattern(this.locale);


        public Builder of(ZonedDateTime zonedDateTime) {
            return (Builder) CommonNote.of(this, zonedDateTime);
        }

        public Builder of(LocalDateTime localDateTime) {
            return (Builder) CommonNote.of(this, localDateTime);
        }

        public Builder at(TimeZone timeZone) {
            return ((Builder) CommonNote.at(this, timeZone));
        }

        public Builder at(ZoneIdExtra zoneIdExtra) {
            return ((Builder) CommonNote.at(this, TimeZone.getTimeZone(zoneIdExtra.getZoneId())));
        }

        public Builder with(String pattern) {
            return ((Builder) CommonNote.with(this, pattern));
        }

        public Builder with(FormatSelectable formatSelectable) {
            return (Builder) CommonNote.with(this, formatSelectable);
        }

        public Builder with(TimeNoteFormat timeNoteFormat) {
            return (Builder) CommonNote.with(this, timeNoteFormat);
        }

        public Builder from(Locale locale) {
            return (Builder) CommonNote.from(this, locale);
        }

        public Builder from(LocaleExtra localeExtra) {
            return (Builder) CommonNote.from(this, localeExtra.getLocale());
        }

        public TimeNote build() {
            return new TimeNote(this);
        }

        @Override
        public String toString() {
            return this.build().toString();
        }
    }

    @Override
    public String toString() {

        return DateTimeFormatter
                .ofPattern(parsePattern(this.pattern), this.locale)
                .format(this.dateTime.withZoneSameInstant(this.timeZone.toZoneId()));
    }
}
