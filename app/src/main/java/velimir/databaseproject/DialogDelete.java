package velimir.databaseproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.Toast;

import db.AcademyContract;
import db.DatabaseHelper;
import model.Student;


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

                if (Student.tableExist(getActivity())) {

                    Student.deleteTable(getActivity());

                    if(listener != null){
                        listener.onDialogSendMessege("Deleted!");
                    }

                } else {
                    if(listener != null){
                        listener.onDialogSendMessege("There is no table to delete!");
                    }
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
}
