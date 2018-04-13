package dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import db.RepositoryManager;
import velimir.databaseproject.OnDialogSendMessegeListener;


public class DialogDelete extends DialogFragment {

    private OnDialogSendMessegeListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Delete Table");
        dialog.setMessage("Are you sure ?");


        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {



                if (RepositoryManager.getInstance().tableExist(getActivity())) {

                    RepositoryManager.getInstance().deleteTable(getActivity());

                    sendMessegeToParent("Deleted!");

                } else {

                   sendMessegeToParent("There is no table to delete!");

                }


            }
        });


        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                DialogDelete.this.dismiss();

            }
        });

        return dialog.create();
    }

    @Override
    public void onAttach(Context context) {
        listener = (OnDialogSendMessegeListener) context;
        super.onAttach(context);
    }

    private void sendMessegeToParent(String message) {
        if (listener != null) {
            listener.onDialogSendMessege(message);
        }
    }
}
