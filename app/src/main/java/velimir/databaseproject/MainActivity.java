package velimir.databaseproject;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import db.DatabaseHelper;

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

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.addToBackStack(null);
        AddDialog addDialog = new AddDialog();
        addDialog.show(ft, "Add Dialog");
    }

    public void onButtonDeleteClick(View view) {

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        DialogDelete dialogDelete = new DialogDelete();
        dialogDelete.show(transaction, "DialogDelete");


    }

    public void onButtonShowClick(View view) {

        Intent intent = new Intent(this, ShowActivity.class);
        startActivity(intent);

    }

    @Override
    public void onDialogSendMessege(CharSequence messege) {
        Toast.makeText(this,messege , Toast.LENGTH_LONG).show();
    }
}
