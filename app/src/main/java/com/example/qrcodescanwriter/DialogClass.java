package com.example.qrcodescanwriter;
import static android.content.Context.CLIPBOARD_SERVICE;

import android.content.ClipboardManager;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import java.nio.charset.Charset;

public class DialogClass extends DialogFragment {

    public static final int IDD_TEXT = 1;
    public static final int IDD_URI = 2;

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState){
        String text = getArguments().getString("msg");
        int ID = getArguments().getInt("id");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.result);
        builder.setCancelable(false);
        builder.setMessage(text);

        switch (ID){
            case IDD_TEXT:
                builder.setPositiveButton(R.string.ok, (dialog, i) -> dialog.dismiss());
                builder.setNegativeButton(R.string.textCopy, (dialog, i) -> {
                    copyText(text);
                    dialog.dismiss();
                    Toast.makeText(getActivity(), R.string.textCopied, Toast.LENGTH_LONG).show();

                });
                return builder.create();
            case IDD_URI:
                builder.setPositiveButton(R.string.ok, (dialog, i) -> dialog.dismiss());
                builder.setNegativeButton(R.string.uriCopy, (dialog, i) -> {
                    copyText(text);
                    dialog.dismiss();
                    Toast.makeText(getActivity(), R.string.uriCopied, Toast.LENGTH_LONG).show();

                });
                builder.setNeutralButton(R.string.uriUse, (dialog, i) -> {
                    dialog.dismiss();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(text));
                    startActivity(intent);
                });
                return builder.create();
            default:
                return null;
        }
    }

    private void copyText(String copiedText) {
        ClipboardManager myClipboard = (ClipboardManager) getContext().getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("text",copiedText);
        myClipboard.setPrimaryClip(clipData);
    }
}
