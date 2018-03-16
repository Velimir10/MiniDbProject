package velimir.databaseproject;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import model.Student;

/**
 * Created by Velimir on 3/9/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<Student> lista;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView id, name, lastname, year, points;

        public ViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.textView_id);
            name = (TextView) itemView.findViewById(R.id.textView_name);
            lastname = (TextView) itemView.findViewById(R.id.textView_lastname);
            year = (TextView) itemView.findViewById(R.id.textView_year);
            points = (TextView) itemView.findViewById(R.id.textView_points);
        }
    }


    public MyAdapter(ArrayList<Student> lista) {
        this.lista = lista;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_list, parent, false);
        ViewHolder v = new ViewHolder(layout);
        return v;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.id.setText(String.valueOf((int)lista.get(position).getId()));
        holder.name.setText(lista.get(position).getName());
        holder.lastname.setText(lista.get(position).getLastname());
        holder.year.setText(String.valueOf(lista.get(position).getYearOfReg()));
        holder.points.setText(String.valueOf(lista.get(position).getPoints()));

    }

    @Override
    public int getItemCount() {
        if(lista == null)
            return 0;
        return lista.size();
    }


}
