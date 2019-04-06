package yasantha.tharindu.plan_it_all;

import android.app.Application;

import com.firebase.client.Firebase;



public class FirebaseSignals extends Application {

    private Firebase mRootRef;


    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);


    }



}
