package br.com.thecoders.timenote.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * <p>Test class for {@link TimeNote} and {@link TimeNote.Builder}.</p>
 * <p>This class contains unit tests to verify the correct functioning of date and time
 * construction and formatting with the {@code TimeNote} class.</p>
 *
 * @author YourNameOrTeamName
 * @version 1.0
 */
class TimeNoteTest {

    private TimeNote.Builder builder;

    /**
     * Sets up the test environment before each test method is executed.
     * Sets the default locale to {@code Locale.GERMANY} and initializes a {@code TimeNote.Builder}
     * with a fixed date and time (2025-01-02 03:04).
     */
    @BeforeEach
    public void setUp() {
        Locale.setDefault(Locale.GERMANY);
        builder = new TimeNote.Builder().of(LocalDateTime.of(2025, 1, 2, 3, 4));
    }

    /**
     * **Given**: A {@code TimeNote.Builder} initialized with a {@code LocalDateTime}.
     * **When**: The {@code build()} method is called.
     * **Then**: The resulting {@code ZonedDateTime} should match the original {@code LocalDateTime}
     * converted to the "America/Sao_Paulo" time zone.
     */
    @Test
    public void givenLocalDateTime_whenBuild_thenReturnsCorrectZonedDatetime() {
        assertEquals(LocalDateTime.of(2025, 1, 2, 3, 4)
                .atZone(ZoneId.of("America/Sao_Paulo")), builder.build().getDateTime());
    }

    /**
     * **Given**: A {@code TimeNote.Builder} initialized with a {@code LocalDateTime}.
     * **When**: The {@code build()} method is called.
     * **Then**: The returned {@code ZonedDateTime} should be identical to an {@code ZonedDateTime}
     * explicitly created with the same information and time zone ("America/Sao_Paulo").
     */
    @Test
    public void givenLocalDateTime_whenBuild_thenReturnsExplicitZonedDatetime() {
        assertEquals(ZonedDateTime.of(2025, 1, 2, 3, 4, 0, 0,
                ZoneId.of("America/Sao_Paulo")), builder.build().getDateTime());
    }

    /**
     * **Given**: A {@code TimeNote.Builder} with the locale configured to {@code Locale.US}
     * and the format for the short day ("&lt;d&gt;").
     * **When**: The {@code toString()} method is called.
     * **Then**: The resulting string should have a length of 1 (representing the day "1").
     */
    @Test
    public void givenUSLocaleAndShortDayFormat_whenToString_thenLengthIsOne() {
        assertEquals(1, builder.from(Locale.US).with("<d>").toString().length());
    }

    /**
     * **Given**: A {@code TimeNote.Builder} with a conditional format that returns "&lt;dddd&gt;" for {@code Locale.FRANCE}.
     * **When**: The locale is set to {@code Locale.FRANCE} and {@code toString()} is called.
     * **Then**: The resulting string should be the full day of the week name in French ("jeudi").
     */
    @Test
    public void givenConditionalFormatAndFranceLocale_whenToString_thenReturnsFullDayNameInFrench() {
        builder.with(locale -> {
            if (locale == Locale.FRANCE) return "<dddd>";
            else return "<ddd>";
        }).from(Locale.FRANCE);

        assertEquals("jeudi", builder.toString());
    }

    /**
     * **Given**: A {@code TimeNote.Builder} with a conditional format that returns "&lt;dddd&gt;" for {@code Locale.FRANCE}.
     * **When**: The default locale is {@code Locale.GERMANY} (not France) and {@code toString()} is called.
     * **Then**: The resulting string should not be the full day of the week name in French ("jeudi"),
     * confirming that the locale condition was applied.
     */
    @Test
    public void givenConditionalFormatAndNonFranceLocale_whenToString_thenDoesNotReturnFullDayNameInFrench() {
        builder.with(locale -> {
            if (locale == Locale.FRANCE) return "<dddd>";
            else return "<ddd>";
        });

        assertNotEquals("jeudi", builder.toString());
    }

    /**
     * **Given**: A {@code TimeNote.Builder} with a conditional format that returns "&lt;ddd&gt;" for locales other than {@code Locale.FRANCE}.
     * **When**: The default locale is {@code Locale.GERMANY} and {@code toString()} is called.
     * **Then**: The resulting string should be the abbreviated day of the week name in German ("Do.").
     */
    @Test
    public void givenConditionalFormatAndGermanyLocale_whenToString_thenReturnsAbbreviatedDayNameInGerman() {
        builder
                .with(locale -> {
                    if (locale == Locale.FRANCE) return "<dddd>";
                    else return "<ddd>";
                });

        assertEquals("Do.", builder.toString());
    }
}