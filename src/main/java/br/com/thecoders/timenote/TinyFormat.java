package br.com.thecoders.timenote;

import java.util.Locale;

public class TinyFormat implements FormatSelectable{

    @Override
    public String pattern(Locale locale) {


        switch (locale.toLanguageTag()){

            case "en-US":
                return "<M>/<d>/<yy> <h>:<mm> <a>";

            case "pt-BR":
                return "<dd>/<MM>/<yy> <HH>:<mm>";
            default:
                throw new IllegalArgumentException("not supported locale, do create a CustomFormat or use pattern instead");
        }


    }
}
