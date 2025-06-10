package br.com.thecoders.timenote.core;

import br.com.thecoders.timenote.custom.LocaleExtra;
import br.com.thecoders.timenote.custom.ZoneIdExtra;
import br.com.thecoders.timenote.format.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class TimeNote extends Notable {

    private ZonedDateTime dateTime;
    private ZoneId zoneId;
    private String pattern;
    private Locale locale;
    private FormatSelectable formatSelectable;
    private boolean withFormatSelectableInvocated;

    @Override
    public ZonedDateTime getDateTime() {
        return this.dateTime;
    }

    @Override
    protected void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public Locale getLocale() {
        return this.locale;
    }

    @Override
    protected void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public ZoneId getZoneId() {
        return this.zoneId;
    }

    @Override
    protected void setZoneId(ZoneId zoneId) {
        this.zoneId = zoneId;
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }

    @Override
    protected void setPattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public FormatSelectable getFormatSelectable() {
        return this.formatSelectable;
    }

    @Override
    protected void setFormatSelectable(FormatSelectable formatSelectable) {
        this.formatSelectable = formatSelectable;
    }

    @Override
    protected boolean isWithFormatSelectableInvocated() {
        return withFormatSelectableInvocated;
    }

    @Override
    protected void setWithFormatSelectableInvocated(boolean withFormatSelectableInvocated) {
        this.withFormatSelectableInvocated = withFormatSelectableInvocated;
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

    private TimeNote(Builder builder) {
        this.dateTime = builder.dateTime;
        this.zoneId = builder.zoneId;
        this.pattern = builder.pattern;
        this.locale = builder.locale;
        this.formatSelectable = builder.formatSelectable;
        this.withFormatSelectableInvocated = builder.withFormatSelectableInvocated;
    }

    public static class Builder extends Notable {

        private ZonedDateTime dateTime = ZonedDateTime.now();
        private ZoneId zoneId = ZoneId.systemDefault();
        private Locale locale = Locale.getDefault();
        private FormatSelectable formatSelectable = new NormalFormat();
        private String pattern = getFormatSelectable().pattern(this.locale);
        private boolean withFormatSelectableInvocated = false;

        @Override
        protected void setLocale(Locale locale) {
            this.locale = locale;
        }

        @Override
        protected String getPattern() {
            return this.pattern;
        }

        @Override
        protected void setZoneId(ZoneId zoneId) {
            this.zoneId = zoneId;
        }

        @Override
        protected ZoneId getZoneId() {
            return this.zoneId;
        }

        @Override
        protected void setDateTime(ZonedDateTime dateTime) {
            this.dateTime = dateTime;
        }

        @Override
        protected ZonedDateTime getDateTime() {
            return this.dateTime;
        }

        @Override
        protected void setPattern(String pattern) {
            this.pattern = pattern;
        }

        @Override
        protected Locale getLocale() {
            return this.locale;
        }

        @Override
        protected FormatSelectable getFormatSelectable() {
            return formatSelectable;
        }

        @Override
        protected void setFormatSelectable(FormatSelectable formatSelectable) {
            this.formatSelectable = formatSelectable;
        }

        @Override
        protected boolean isWithFormatSelectableInvocated() {
            return withFormatSelectableInvocated;
        }

        @Override
        protected void setWithFormatSelectableInvocated(boolean withFormatSelectableInvocated) {
            this.withFormatSelectableInvocated = withFormatSelectableInvocated;
        }

        public Builder of(ZonedDateTime zonedDateTime) {
            setDateTime(zonedDateTime);
            return this;
        }

        public Builder of(LocalDateTime localDateTime) {
            setDateTime(localDateTime.atZone(getZoneId()));
            return this;
        }

        public Builder at(ZoneId zoneId) {
            setZoneId(zoneId);
            return this;
        }

        public Builder at(ZoneIdExtra zoneIdExtra) {
            setZoneId(zoneIdExtra.getZoneId());
            return this;
        }

        public Builder with(String pattern) {
            setPattern(pattern);
            return this;
        }

        public Builder with(FormatSelectable formatSelectable) {
            setFormatSelectable(formatSelectable);
            setWithFormatSelectableInvocated(true);
            return this;
        }

        public Builder with(TimeNoteFormat timeNoteFormat) {

            switch (timeNoteFormat) {

                case TINY: {
                    setFormatSelectable(new TinyFormat());
                    break;
                }

                case SMALL: {
                    setFormatSelectable(new SmallFormat());
                    break;
                }

                case NORMAL: {
                    setFormatSelectable(new NormalFormat());
                    break;
                }

                case BIG: {
                    setFormatSelectable(new BigFormat());
                    break;
                }

                case EXTENDED: {
                    setFormatSelectable(new ExtendedFormat());
                }
            }

            setWithFormatSelectableInvocated(true);
            return this;
        }

        public Builder from(Locale locale) {
            setLocale(locale);
            return this;
        }

        public Builder from(LocaleExtra localeExtra) {
            setLocale(localeExtra.getLocale());
            return this;
        }

        public TimeNote build() {
            if (isWithFormatSelectableInvocated()) {
                this.pattern = this.getFormatSelectable().pattern(this.locale);
            }
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
                .format(this.dateTime.withZoneSameInstant(this.zoneId));
    }
}
