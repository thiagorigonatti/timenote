package br.com.thecoders.timenote;

import java.util.Locale;

public class BigFormat implements FormatSelectable {
    @Override
    public String pattern(Locale locale) {


        switch (locale.toLanguageTag()){

            case "en-US":
                return "<dddd>, <MMMM> <d>, <yyyy> <h>:<mm>:<ss> <a> <z> <xxxx>";

            case "pt-BR":
                return "<dddd>, <dd> 'de' <MMMM> 'de' <yyyy> <HH>:<mm> <z> <xxxx>";
            default:
                throw new IllegalArgumentException("not supported locale, do create a CustomFormat or use pattern instead");
        }


    }
}
