package velimir.databaseproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import db.RepositoryManager;
import model.Student;

public class ShowActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private MyAdapter myAdapter;

    private ArrayList<Student> studentLista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        studentLista = RepositoryManager.getInstance().read(this);

        myAdapter = new MyAdapter(studentLista);
        recyclerView = findViewById(R.id.myRecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);

    }


}







