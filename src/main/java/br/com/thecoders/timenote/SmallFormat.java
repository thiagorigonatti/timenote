package br.com.thecoders.timenote;

import java.util.Locale;

public class SmallFormat implements FormatSelectable{

    @Override
    public String pattern(Locale locale) {


        switch (locale.toLanguageTag()){

            case "en-US":
                return "<ddd>, <M>/<d>/<yy> <h>:<mm> <a>";

            case "pt-BR":
                return "<ddd> <dd>/<MM>/<yy> <HH>:<mm>";
            default:
                throw new IllegalArgumentException("not supported locale, do create a CustomFormat or use pattern instead");
        }


    }
}
