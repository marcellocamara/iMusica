package dev.marcello.imusica.util;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;

import java.util.Locale;

/**
 * Marcello
 * 2019
 */

public class LanguageUtil {

    private static SharedPreferences sharedPreferences;
    private static final String FILE_NAME = "Language";

    public static ContextWrapper changeLang(Context context) {
        String langCode = getLanguage(context);
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            configuration.setLocale(locale);
        } else {
            configuration.locale = locale;
        }
        context = context.createConfigurationContext(configuration);
        return new ContextWrapper(context);
    }

    private static String getLanguage(Context context) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, context.MODE_PRIVATE);
        return sharedPreferences.getString("locale", "en");
    }

    public static void setLanguage(String locale, Context context) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("locale", locale);
        editor.apply();
    }

}