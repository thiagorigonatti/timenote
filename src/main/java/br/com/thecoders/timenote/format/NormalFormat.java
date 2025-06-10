package br.com.thecoders.timenote.format;

import br.com.thecoders.timenote.core.FormatSelectable;

import java.util.Locale;

public class NormalFormat implements FormatSelectable {

    @Override
    public String pattern(Locale locale) {

        switch (locale.toLanguageTag()) {

            case "pt-BR":
                return "<dddd>, <dd>/<MM>/<yyyy> - <HH>:<mm> <z>";
            default:
                return "<dddd>, <M>/<d>/<yyyy> - <h>:<mm> <a> <z>";
        }
    }
}
