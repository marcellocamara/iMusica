package dev.marcello.imusica.util;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

/**
 * Marcello
 * 2019
 */

public class LanguageUtil {

    private static SharedPreferences sharedPreferences;
    private static final String FILE_NAME = "Language";

    public static ContextWrapper changeLang(Context context) {
        String localeCode = getLocaleCode(context);
        String countryCode = getCountryCode(context);
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        Locale locale = new Locale(localeCode, countryCode);
        Locale.setDefault(locale);
        configuration.setLocale(locale);
        context = context.createConfigurationContext(configuration);
        return new ContextWrapper(context);
    }

    private static String getLocaleCode(Context context) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, context.MODE_PRIVATE);
        return sharedPreferences.getString("locale", "en");
    }

    private static String getCountryCode(Context context) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, context.MODE_PRIVATE);
        return sharedPreferences.getString("country", "US");
    }

    public static void setLanguage(String locale, String countryCode, Context context) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("locale", locale);
        editor.putString("country", countryCode);
        editor.apply();
    }

}