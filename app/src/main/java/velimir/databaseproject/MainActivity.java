package velimir.databaseproject;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import dialogs.AddDialog;
import dialogs.DialogDelete;

public class MainActivity extends AppCompatActivity implements velimir.databaseproject.OnDialogSendMessegeListener {


    private Button add, delete, show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        add = (Button) findViewById(R.id.button_add);
        delete = (Button) findViewById(R.id.button_delete);
        show = (Button) findViewById(R.id.button_show);


    }


    public void onButtonAddClick(View view) {

        Fragment fragment = new AddDialog();
        openDialog(fragment,"Add Dialog");
    }

    public void onButtonDeleteClick(View view) {

        Fragment fragment = new DialogDelete();
        openDialog(fragment, "Dialog Delete");


    }

    public void onButtonShowClick(View view) {

        Intent intent = new Intent(this, ShowActivity.class);
        startActivity(intent);

    }

    @Override
    public void onDialogSendMessege(CharSequence messege) {
        Toast.makeText(this, messege, Toast.LENGTH_LONG).show();
    }


    public void openDialog(Fragment fragment, String tag) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        transaction.add(fragment, tag);
        transaction.commit();
    }
}
