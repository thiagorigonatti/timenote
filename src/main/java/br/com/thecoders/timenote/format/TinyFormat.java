package br.com.thecoders.timenote.format;

import br.com.thecoders.timenote.core.FormatSelectable;

import java.util.Locale;

public class TinyFormat implements FormatSelectable {

    @Override
    public String pattern(Locale locale) {

        switch (locale.toLanguageTag()) {

            case "pt-BR":
                return "<dd>/<MM>/<yy> <HH>:<mm>";
            default:
                return "<M>/<d>/<yy> <h>:<mm> <a>";
        }
    }
}
