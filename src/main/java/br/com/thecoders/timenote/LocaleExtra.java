package br.com.thecoders.timenote;

import java.util.Locale;

public class LocaleExtra {

    private final Locale locale;

    private LocaleExtra(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }

    public static final LocaleExtra PT_BR = new LocaleExtra(new Locale("pt", "BR"));

}
