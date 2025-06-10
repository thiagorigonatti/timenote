package br.com.thecoders.timenote.format;

import br.com.thecoders.timenote.core.FormatSelectable;

import java.util.Locale;

public class BigFormat implements FormatSelectable {

    @Override
    public String pattern(Locale locale) {

        switch (locale.toLanguageTag()) {

            case "pt-BR":
                return "<dddd>, <dd> 'de' <MMMM> 'de' <yyyy> <HH>:<mm> <z> <xxxx>";
            default:
                return "<dddd>, <MMMM> <d>, <yyyy> <h>:<mm>:<ss> <a> <z> <xxxx>";
        }
    }
}
