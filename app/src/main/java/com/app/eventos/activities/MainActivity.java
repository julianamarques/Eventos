package com.app.eventos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import com.app.eventos.R;
import com.app.eventos.adapter.EventosAdapter;
import com.app.eventos.controllers.EventoController;
import com.app.eventos.dao.ConfiguracaoFirebase;
import com.app.eventos.dao.ConfiguracaoFirebaseAuth;
import com.app.eventos.model.Evento;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.rv_lista_eventos) protected RecyclerView recyclerEventos;

    private FirebaseAuth auth;
    private EventosAdapter eventosAdapter;
    private int positionEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        auth = ConfiguracaoFirebaseAuth.getFirebaseAuth();
        positionEvento = getIntent().getIntExtra("idEvento", -1);

    }

    @Override
    protected void onResume() {
        super.onResume();

        recyclerEventos.setAdapter(eventosAdapter);
        recyclerEventos.setLayoutManager(new LinearLayoutManager(this));
        recyclerEventos.setHasFixedSize(true);
        eventosAdapter = new EventosAdapter(this, listarEventos());
    }

    public List<Evento> listarEventos() {
        final List<Evento> eventos = new ArrayList<>();

        ConfiguracaoFirebase.getDatabaseReference().child("eventos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    Evento evento = objSnapshot.getValue(Evento.class);
                    eventos.add(evento);
                }

                eventosAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return eventos;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_login) {
            startActivity(new Intent(this, LoginActivity.class));

            auth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = auth.getCurrentUser();

                    if (user != null) {
                        item.setVisible(false);
                    }

                    else {
                        item.setVisible(true);
                    }
                }
            });
        }

        else if (id == R.id.menu_minhas_inscricoes) {

        }

        else if (id == R.id.menu_meus_eventos) {
            startActivity(new Intent(this, MeusEventosActivity.class));
        }

        else if (id == R.id.menu_config) {

        }

        else if (id == R.id.menu_sair) {
            auth.signOut();

            auth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = auth.getCurrentUser();

                    if (user == null) {
                        item.setVisible(false);
                    }

                    else {
                        item.setVisible(true);
                    }
                }
            });
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
