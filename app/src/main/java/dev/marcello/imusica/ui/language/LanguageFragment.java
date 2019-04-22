package dev.marcello.imusica.ui.language;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static dev.marcello.imusica.util.LanguageUtil.setLanguage;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dev.marcello.imusica.R;
import dev.marcello.imusica.ui.login.LoginActivity;
import dev.marcello.imusica.ui.main.IMainContract;

/**
 * Marcello
 * 2019
 */

public class LanguageFragment extends Fragment {

    private IMainContract.ScreenTitle screenTitle;
    private Unbinder unbinder;

    public LanguageFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenTitle.SetTitle(getString(R.string.language));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_language, container, false);

        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.btnEnglish)
    public void OnButtonEnglishClick() {
        changeLanguage("en", "US");
    }

    @OnClick(R.id.btnPortuguese)
    public void OnButtonPortugueseClick() {
        changeLanguage("pt", "BR");
    }

    private void changeLanguage(String localeCode, String countryCode) {
        setLanguage(localeCode, countryCode, getContext());
        getActivity().finish();
        startActivity(new Intent(getContext(), LoginActivity.class));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        screenTitle = (IMainContract.ScreenTitle) getActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}