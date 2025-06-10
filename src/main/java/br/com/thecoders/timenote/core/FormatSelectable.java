package br.com.thecoders.timenote.core;

import java.util.Locale;

@FunctionalInterface
public interface FormatSelectable {

    String pattern(Locale locale);
}
