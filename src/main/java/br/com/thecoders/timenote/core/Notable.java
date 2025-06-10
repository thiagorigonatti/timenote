package br.com.thecoders.timenote.core;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;

public abstract class Notable {

    protected abstract ZonedDateTime getDateTime();

    protected abstract void setDateTime(ZonedDateTime dateTime);

    protected abstract Locale getLocale();

    protected abstract void setLocale(Locale locale);

    protected abstract ZoneId getZoneId();

    protected abstract void setZoneId(ZoneId zoneId);

    protected abstract String getPattern();

    protected abstract void setPattern(String pattern);

    protected abstract FormatSelectable getFormatSelectable();

    protected abstract void setFormatSelectable(FormatSelectable formatSelectable);

    protected abstract boolean isWithFormatSelectableInvocated();

    protected abstract void setWithFormatSelectableInvocated(boolean withFormatSelectableInvocated);
}
