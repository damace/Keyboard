package co.damace.mykeyboard;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;

public class MyKeyboard extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

    private KeyboardView kv;
    private Keyboard keyboard;

    private boolean caps = false, especiales = true, especiales2 = true;

    @Override
    public View onCreateInputView() {
        View view = getLayoutInflater().inflate(R.layout.keyboard, null);
        kv = view.findViewById(R.id.keyboards);
        keyboard = new Keyboard(this, R.xml.qwerty);
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);
        return view;
    }

    private void playCLick(int keyCode) {
        AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);
        switch (keyCode) {
            case 32:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR);
                break;
            case 10:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN);
                break;
            case Keyboard.KEYCODE_DELETE:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_DELETE);
                break;
            default:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
        }
    }

    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        /***
         * Funcionalidad de cada tecla especial
         * ***/
        InputConnection ic = getCurrentInputConnection();
        playCLick(primaryCode);
        switch (primaryCode) {

            case Keyboard.KEYCODE_DELETE:
                ic.deleteSurroundingText(1, 0);
                break;
            case Keyboard.KEYCODE_SHIFT:
                caps = !caps;
                keyboard.setShifted(caps);
                kv.invalidateAllKeys();
                break;
            case Keyboard.KEYCODE_DONE:
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
            case Keyboard.KEYCODE_ALT:
                if (especiales) {
                    keyboard = new Keyboard(this, R.xml.specials);
                    kv.setKeyboard(keyboard);
                    kv.setOnKeyboardActionListener(this);
                    especiales = !especiales;
                } else {
                    keyboard = new Keyboard(this, R.xml.qwerty);
                    kv.setKeyboard(keyboard);
                    kv.setOnKeyboardActionListener(this);
                    especiales = !especiales;
                }
                break;
            case -2:
                if (especiales2) {
                    keyboard = new Keyboard(this, R.xml.specials1);
                    kv.setKeyboard(keyboard);
                    kv.setOnKeyboardActionListener(this);
                    especiales2 = !especiales2;
                } else {
                    keyboard = new Keyboard(this, R.xml.specials);
                    kv.setKeyboard(keyboard);
                    kv.setOnKeyboardActionListener(this);
                    especiales2 = !especiales2;
                }
                break;
            case -3:
                keyboard = new Keyboard(this, R.xml.qwerty);
                kv.setKeyboard(keyboard);
                kv.setOnKeyboardActionListener(this);
                especiales2 = true;
                especiales = true;
                break;
            default:
                char code = (char) primaryCode;
                if (Character.isLetter(code) && caps) {
                    code = Character.toUpperCase(code);
                }
                ic.commitText(String.valueOf(code), 1);

        }

    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }



}
