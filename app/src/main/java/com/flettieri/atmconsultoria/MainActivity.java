package com.flettieri.atmconsultoria;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.flettieri.atmconsultoria.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                enviarEmail();

            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_principal, R.id.nav_servicos, R.id.nav_clientes, R.id.nav_contato, R.id.nav_sobre)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    public void enviarEmail() {
        //        String celular = "tel: 11968389586";
        //        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(celular));
        //        String imagem = "https://a.cdn-hotels.com/gdcs/production36/d869/fa6abe34-9d1e-4a27-832f-067bbf579a22.jpg";
        //        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(imagem));
        //        String maps = "https://www.google.com.br/maps/place/Parque+Ibirapuera+-+Vila+Mariana,+S%C3%A3o+Paulo+-+State+of+S%C3%A3o+Paulo,+04094-050/@-23.5889651,-46.6622414,17z/data=!3m1!4b1!4m13!1m7!3m6!1s0x94ce59e6f06b0f89:0x43617829f701baf6!2sIbirapuera,+S%C3%A3o+Paulo+-+State+of+S%C3%A3o+Paulo!3b1!8m2!3d-23.5836023!4d-46.6644988!3m4!1s0x94ce59f0957e5695:0xac00121dd1f780!8m2!3d-23.589123!4d-46.6598127";
        //        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(maps));


        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"atendimento@atmconsultoria.com.br"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Contato pelo App");
        intent.putExtra(Intent.EXTRA_TEXT, "Mensagem automática");

//        intent.setType("text/plain");
//        intent.setType("image/*");
//        intent.setType("application/pdf");
        intent.setType("message/rfc822");

        startActivity(Intent.createChooser(intent, "Escolha um App de e-mail"));



    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}