package com.tabo.weather;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

public class AlertDialogFragment extends DialogFragment {

    /***
     * A dialog is a simple window that pops up and prompts the user
     * to make a decision. It is good to alert the user when
     * an error has occurred. A toast can be used, but its only
     * on the screen for a short amount of time. This dialog fragment will
     * handle an error that occurs when something goes wrong with the network
     * request

     */

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Context context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        /*
        This sets the title and message of the dialog.
        A button is added, which runs code when the button is tapped
        */

        builder.setTitle(R.string.error_title) // Title
        .setMessage(R.string.error_message) //Message
        .setPositiveButton(R.string.ok_stringText,null); //Button

        //returns the created dialog fragment
        return builder.create();



    }
}
