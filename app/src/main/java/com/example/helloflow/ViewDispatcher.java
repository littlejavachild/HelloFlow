package com.example.helloflow;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import flow.Dispatcher;
import flow.Traversal;
import flow.TraversalCallback;

/**
 * Created by archenemy on 23/2/16.
 */
public class ViewDispatcher implements Dispatcher{
    private Activity activity;
    //----------------------------------------------------------------------------------------------
    public ViewDispatcher(Activity act){
        activity = act;
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public void dispatch(Traversal traversal, TraversalCallback callback) {
        Object dest = traversal.destination.top();
        Object source = traversal.origin == null ? null : traversal.origin.top();

        Log.d("ViewDispatcher","Dispatcher Called");
        Log.d("ViewDispatcher","Source: " + (source==null ? "null" : ((Screens)source).name()) );
        Log.d("ViewDispatcher", "Destination: " + ((Screens) dest).name());

        ViewGroup root = (ViewGroup) activity.findViewById(R.id.root);
        if (traversal.origin != null) {
            int childCount = root.getChildCount();
            if (childCount > 1) {
                traversal.getState(traversal.origin.top()).save(root.getChildAt(1));
                removeAllViewsAfter(root,0);
            }
        }

        @LayoutRes int layout;
        if(dest.equals(Screens.WELCOME)){
            layout = R.layout.view_welcome;
        }else{
            layout = R.layout.view_chat;
        }

        View incomingView = LayoutInflater
                            .from(traversal.createContext(dest, activity))
                            .inflate(layout, root, false);
        traversal.getState( traversal.destination.top() ).restore(incomingView);
        root.addView( incomingView );
        callback.onTraversalCompleted();
    }
    //----------------------------------------------------------------------------------------------
    private void removeAllViewsAfter(ViewGroup root, int index){
        for( int i = root.getChildCount() - 1; i > index; i-- ){
            root.removeViewAt( i );
        }
    }
    //----------------------------------------------------------------------------------------------
}
