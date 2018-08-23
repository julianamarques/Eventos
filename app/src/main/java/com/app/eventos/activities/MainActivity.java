package com.app.eventos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.view.View;
import android.widget.ArrayAdapter;
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

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.rv_lista_eventos) protected RecyclerView recyclerEventos;
    @BindView(R.id.nav_view) protected NavigationView navigationView;
    @BindView(R.id.drawer_layout) protected DrawerLayout drawer;
    @BindView(R.id.toolbar) protected Toolbar toolbar;

    private FirebaseAuth auth;
    private EventosAdapter eventosAdapter;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        auth = ConfiguracaoFirebaseAuth.getFirebaseAuth();
        View header = navigationView.inflateHeaderView(R.layout.nav_header_main);
        TextView tvEmailUsuario = (TextView) header.findViewById(R.id.tv_email_usuario);

        if (LoginActivity.verificarLogin(auth.getCurrentUser())) {
            tvEmailUsuario.setText(auth.getCurrentUser().getEmail());
        }

        else {
            tvEmailUsuario.setText("");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        eventosAdapter = new EventosAdapter(this);
        recyclerEventos.setAdapter(eventosAdapter);
        recyclerEventos.setLayoutManager(new LinearLayoutManager(this));
        recyclerEventos.setHasFixedSize(true);

        ocultarMenuLoginOuSair(navigationView);
    }

    @Override
    public void onBackPressed() {
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
            if (LoginActivity.verificarLogin(auth.getCurrentUser())) {
                Toast.makeText(this,"Você já está logado!",Toast.LENGTH_SHORT).show();

            }else {
                startActivity(new Intent(this, LoginActivity.class));
            }
        }

        else if (id == R.id.menu_minhas_inscricoes) {
            if (LoginActivity.verificarLogin(auth.getCurrentUser())) {
                startActivity(new Intent(this, MinhasInscricoesActivity.class));
            }

            else {
                Toast.makeText(this, "Faça o login para acessar minhas inscrições", Toast.LENGTH_SHORT).show();
            }
        }

        else if (id == R.id.menu_meus_eventos) {
                if (LoginActivity.verificarLogin(auth.getCurrentUser())) {
                    startActivity(new Intent(this, MeusEventosActivity.class));

                }

                else{
                    Toast.makeText(this,"Faça o login para acessar meus eventos",Toast.LENGTH_SHORT).show();
                }
            }

        else if (id == R.id.menu_config) {

        }

        else if (id == R.id.menu_sair) {
            if (LoginActivity.verificarLogin(auth.getCurrentUser())){
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);

                builder.setTitle("EventosAPP");
                builder.setMessage("Deseja mesmo sair da sua conta?");
                builder.setPositiveButton("SIM", (dialog, which) -> {
                    auth.signOut();
                    Toast.makeText(this,"Usuário deslogado!",Toast.LENGTH_SHORT).show();
                    recreate();
                });
                builder.setNegativeButton("NÃO", (dialog, which) -> {
                });

                builder.create().show();
            }

            else {
                Toast.makeText(this, "Você não está logado", Toast.LENGTH_SHORT).show();
            }
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    public void criarEvento(MenuItem item) {
        if (LoginActivity.verificarLogin(auth.getCurrentUser())) {
            startActivity(new Intent(this, CadastroEventosActivity.class));
        }

        else {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }


    private void ocultarMenuLoginOuSair(NavigationView navigationView) {
        Menu menu = navigationView.getMenu();

        if (LoginActivity.verificarLogin(auth.getCurrentUser())) {
            menu.findItem(R.id.menu_login).setVisible(false);
            menu.findItem(R.id.menu_sair).setVisible(true);
        }

        else {
            menu.findItem(R.id.menu_login).setVisible(true);
            menu.findItem(R.id.menu_sair).setVisible(false);
        }
    }
}
