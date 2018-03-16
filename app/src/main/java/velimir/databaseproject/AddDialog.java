package velimir.databaseproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import db.AcademyContract;
import db.DatabaseHelper;
import model.Student;


public class AddDialog extends DialogFragment {



    private EditText mName, mLastname, mYear, mPoints;
    private OnDialogSendMessegeListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View dialogLayout = layoutInflater.inflate(R.layout.add_user_dialog, null, false);

        mName = (EditText) dialogLayout.findViewById(R.id.editText_name);
        mLastname = (EditText) dialogLayout.findViewById(R.id.editText_lastname);
        mYear = (EditText) dialogLayout.findViewById(R.id.editText_year);
        mPoints = (EditText) dialogLayout.findViewById(R.id.editText_points);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogLayout);

        builder.setPositiveButton("add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String name = mName.getText().toString();
                String lastName = mLastname.getText().toString();
                String year = mYear.getText().toString();
                String points = mPoints.getText().toString();

                if (name.equals("") || lastName.equals("") || year.equals("") || points.equals("")) {
                    if (listener != null) {
                        listener.onDialogSendMessege("To INSERT a Student in Database you have to fill all fields!");
                    }

                } else {

                    int intYear = Integer.valueOf(year);
                    int intPoints = Integer.valueOf(points);

                    Student student = new Student(name, lastName, intYear, intPoints, getActivity());
                    boolean hasInserted = student.insert();

                    if(hasInserted){

                        if (listener != null) {
                            listener.onDialogSendMessege("Successfuly added!");
                        }

                    }



                }

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AddDialog.this.dismiss();
            }
        });


        return builder.create();
    }


    @Override
    public void onAttach(Context context) {

        this.listener = (OnDialogSendMessegeListener) context;
        super.onAttach(context);

    }
}
