package com.itmm.rss_reader;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Дмитрий on 11/5/2016.
 */

public class ButtonHandler {

    private Button ButtonPolitics;
    private Button ButtonBusiness;
    private Button ButtonSport;
    private Button ButtonMusic;
    private Button ButtonMSDN;

    private ButtonHandler.ButtonPressed Delegate;

    public ButtonHandler(Activity activity, ButtonHandler.ButtonPressed delegate) {
        ButtonPolitics = (Button) activity.findViewById(R.id.button1);
        ButtonBusiness = (Button) activity.findViewById(R.id.button2);
        ButtonSport = (Button) activity.findViewById(R.id.button3);
        ButtonMusic = (Button) activity.findViewById(R.id.button4);
        ButtonMSDN = (Button) activity.findViewById(R.id.button5);

        Delegate = delegate;

        ButtonPolitics.setOnClickListener(onClickPolitics);
        ButtonBusiness.setOnClickListener(onClickBusiness);
        ButtonSport.setOnClickListener(onClickSport);
        ButtonMusic.setOnClickListener(onClickMusic);
        ButtonMSDN.setOnClickListener(onClickMSDN);
    }

    private Button.OnClickListener onClickPolitics = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Delegate.buttonPolitics();
        }
    };

    private Button.OnClickListener onClickBusiness = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Delegate.buttonBusiness();
        }
    };

    private Button.OnClickListener onClickSport = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Delegate.buttonSport();
        }
    };

    private Button.OnClickListener onClickMusic = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Delegate.buttonMusic();
        }
    };

    private Button.OnClickListener onClickMSDN = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Delegate.buttonMSDN();
        }
    };

    /**
     * Interface with predefined methods as a actions for listener
     */
    public interface ButtonPressed {
        void buttonPolitics();

        void buttonBusiness();

        void buttonSport();

        void buttonMusic();

        void buttonMSDN();
    }
}
