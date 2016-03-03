package com.example.helloflow;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.Objects;

import flow.Flow;

public class MainActivity extends AppCompatActivity {
    private ViewGroup root;
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ButterKnife.bind( this )
        // Initialize Facebook SDK
        root = (ViewGroup) findViewById( R.id.root );
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public void onResume(){
        super.onResume();
    }
    //----------------------------------------------------------------------------------------------
    @Override
    protected void attachBaseContext(Context baseContext) {
        PrefUtils.init( baseContext );
        Object key;

        key = PrefUtils.getBoolean(PrefUtils.Key.IS_USER_LOGGED_IN,false) == false ? Screens.WELCOME : Screens.CHAT;

        baseContext = Flow.configure(baseContext, this)
                .dispatcher(new ViewDispatcher(this))
                .defaultKey(key)
                .install();
        super.attachBaseContext(baseContext);
    }
    //----------------------------------------------------------------------------------------------
    public void onGoogleSignIn(View v){
        // login
        PrefUtils.putBoolean(PrefUtils.Key.IS_USER_LOGGED_IN,true);
        // go to new screen
        Flow.get( root ).set( Screens.CHAT );
    }
    //----------------------------------------------------------------------------------------------
}
