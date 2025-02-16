package com.example.customkeyboard;

import android.inputmethodservice.InputMethodService;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.content.ClipboardManager;
import android.content.ClipData;
import android.content.Context;
import android.util.Log;

public class MyKeyboardService extends InputMethodService {

    private ClipboardManager clipboardManager;

    @Override
    public void onCreate() {
        super.onCreate();
        clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        clipboardManager.addPrimaryClipChangedListener(() -> {
            ClipData clipData = clipboardManager.getPrimaryClip();
            if (clipData != null && clipData.getItemCount() > 0) {
                CharSequence copiedText = clipData.getItemAt(0).getText();
                if (copiedText != null) {
                    Log.d("Clipboard", "Copied: " + copiedText.toString());
                }
            }
        });
    }

    @Override
    public View onCreateInputView() {
        return getLayoutInflater().inflate(R.layout.keyboard_view, null);
    }
}
