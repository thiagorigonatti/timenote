package br.com.thecoders.timenote.format;

import br.com.thecoders.timenote.core.FormatSelectable;

import java.util.Locale;

public class SmallFormat implements FormatSelectable {

    @Override
    public String pattern(Locale locale) {

        switch (locale.toLanguageTag()){

            case "pt-BR":
                return "<ddd> <dd>/<MM>/<yy> <HH>:<mm>";
            default:
                return "<ddd>, <M>/<d>/<yy> <h>:<mm> <a>";
        }
    }
}
