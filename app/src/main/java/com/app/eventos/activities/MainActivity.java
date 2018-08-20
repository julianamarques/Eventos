package com.app.eventos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.app.eventos.R;
import com.app.eventos.adapter.EventosAdapter;
import com.app.eventos.dao.EventoDAO;
import com.app.eventos.dao.ConfiguracaoFirebase;
import com.app.eventos.dao.ConfiguracaoFirebaseAuth;
import com.app.eventos.model.Evento;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.rv_lista_eventos) protected RecyclerView recyclerEventos;

    private FirebaseAuth auth;
    private EventosAdapter eventosAdapter;
    private int positionEvento;
    private EventoDAO eventoDAO;


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
        eventoDAO = new EventoDAO();
        positionEvento = getIntent().getIntExtra("idEvento", -1);
    }

    @Override
    protected void onResume() {
        super.onResume();

        eventosAdapter = new EventosAdapter(this);
        recyclerEventos.setAdapter(eventosAdapter);
        recyclerEventos.setLayoutManager(new LinearLayoutManager(this));
        recyclerEventos.setHasFixedSize(true);
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
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        int id = item.getItemId();

        if (id == R.id.menu_login) {
            if (user == null) {
                startActivity(new Intent(this, LoginActivity.class));

            }else {
                Toast.makeText(this,"Você já está logado!",Toast.LENGTH_SHORT).show();
            }
        }

        else if (id == R.id.menu_minhas_inscricoes) {
            if (user == null) {
                Toast.makeText(this, "Faça o login para acessar minhas inscrições", Toast.LENGTH_SHORT).show();
            }

            else {
                startActivity(new Intent(this, MinhasInscricoesActivity.class));
            }
        }

        else if (id == R.id.menu_meus_eventos) {

                if (user == null) {
                    Toast.makeText(this,"Faça o login para acessar meus eventos",Toast.LENGTH_SHORT).show();

                }else{
                    startActivity(new Intent(this, MeusEventosActivity.class));
                }
            }

        else if (id == R.id.menu_config) {

        }

        else if (id == R.id.menu_sair) {
            if (user == null){
                Toast.makeText(this,"Você não está logado",Toast.LENGTH_SHORT).show();
            }else{

                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);

                builder.setTitle("EventosAPP");
                builder.setMessage("Deseja mesmo sair da sua conta?");
                builder.setPositiveButton("SIM", (dialog, which) -> {
                    auth.signOut();
                    Toast.makeText(this,"Usuário deslogado!",Toast.LENGTH_SHORT).show();
                });
                builder.setNegativeButton("NÃO", (dialog, which) -> {
                });

                builder.create().show();

            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    public void criarEvento(MenuItem item) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            startActivity(new Intent(this, LoginActivity.class));
        }

        else {
            startActivity(new Intent(this, CadastroEventosActivity.class));
        }
    }
}
