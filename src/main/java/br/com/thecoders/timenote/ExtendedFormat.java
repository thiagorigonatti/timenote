package br.com.thecoders.timenote;

import java.util.Locale;

public class ExtendedFormat implements FormatSelectable{


    @Override
    public String pattern(Locale locale) {


        switch (locale.toLanguageTag()){

            case "en-US":
                return "<dddd>, <MMMM> <d>, <yyyy>, <h>:<mm>:<ss>.<ms> <a> <z> <zzzz> <xxxx>";

            case "pt-BR":
                return "<dddd>, <dd> 'de' <MMMM> 'de' <yyyy>, <HH>:<mm>:<ss>.<ms> <z> <zzzz> <xxxx>";
            default:
                throw new IllegalArgumentException("not supported locale, do create a CustomFormat or use pattern instead");
        }


    }
}
