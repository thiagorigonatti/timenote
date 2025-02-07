package br.com.thecoders.timenote;

import java.util.Locale;

public class NormalFormat implements FormatSelectable {


    @Override
    public String pattern (Locale locale){

        switch (locale.toLanguageTag()){

            case "en-US":
                return "<dddd>, <M>/<d>/<yyyy> - <h>:<mm> <a> <z>";

            case "pt-BR":
                return "<dddd>, <dd>/<MM>/<yyyy> - <HH>:<mm> <z>";
            default:
                throw new IllegalArgumentException("not supported locale, do create a CustomFormat or use pattern instead");
        }
    }


}
