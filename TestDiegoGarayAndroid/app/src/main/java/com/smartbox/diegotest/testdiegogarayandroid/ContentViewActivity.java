package com.smartbox.diegotest.testdiegogarayandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class ContentViewActivity extends AppCompatActivity {

    private RecyclerView rView;

    public RecyclerView getrView() {
        return rView;
    }

    public void setrView(RecyclerView rView) {
        this.rView = rView;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_view_activity);

        rView = (RecyclerView) findViewById(R.id.contentView);
        rView.setHasFixedSize(true);

        LinearLayoutManager lManager = new LinearLayoutManager(this);
        rView.setLayoutManager(lManager);

        ArrayList<Partidos> list = (ArrayList<Partidos>) getIntent().getSerializableExtra("partidos");

        PartidosAdapter adapter = new PartidosAdapter(this, list);
        rView.setAdapter(adapter);
    }
}
