package dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import db.RepositoryManager;
import model.Student;
import velimir.databaseproject.OnDialogSendMessegeListener;
import velimir.databaseproject.R;


public class AddDialog extends DialogFragment {


    private EditText mName, mLastname, mYear, mPoints;
    private OnDialogSendMessegeListener listener;
    private String name;
    private String lastName;
    private String year;
    private String points;

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

                getViewValues();

                if (name.equals("") || lastName.equals("") || year.equals("") || points.equals("")) {

                    sendMessegeToParent("To INSERT a Student in Database you have to fill all fields!");
                } else {

                    int intYear = Integer.valueOf(year);
                    int intPoints = Integer.valueOf(points);


                    boolean hasInserted = RepositoryManager.getInstance().insert(new Student(name, lastName, intYear, intPoints), getActivity());

                    if (hasInserted) {
                        sendMessegeToParent("Successfuly added!");

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

    private void getViewValues() {

        name = mName.getText().toString();
        lastName = mLastname.getText().toString();
        year = mYear.getText().toString();
        points = mPoints.getText().toString();
    }

    private void sendMessegeToParent(String message) {
        if (listener != null) {
            listener.onDialogSendMessege(message);
        }
    }
}
