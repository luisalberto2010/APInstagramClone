package com.example.apinstagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application
{

    @Override
    public void onCreate() {

        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("PQ5RA1dPIkwagVtNGRm36W1DeOme4HGTy2RVzZax")
                // if defined
                .clientKey("boHK1NVFXwlyxZA4GJcAH8u4kBUwLhxWxi82GWlp")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }




}
