package dev.marcello.imusica.ui.dialog;

/**
 * Macello
 * 2019
 */

public interface IDialog {

    interface Post {

        void OnCreate(String title, long created);

        void OnUpdate(String title, String author, long created, int ups, int comments);

        void OnDelete(long created);

    }

}