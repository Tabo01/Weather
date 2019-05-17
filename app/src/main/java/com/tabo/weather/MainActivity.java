package com.tabo.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String apiKey="67c34c0f166cfe1cc2b61affa815a966";

        double latitude =  37.8267;
        double longtitude = -122.4233;

        String forcastURl = "https://api.darksky.net/forecast/"
                + apiKey +"/" + latitude + "," + longtitude;

        // Creates a client object of the OkHttpClient type, the main client object
        OkHttpClient client = new OkHttpClient();

        /***
        We need to build a request for the client to send to the dark sky server:
         which passes in the forecast URl and has it build
         */
        Request request = new Request.Builder()
                .url(forcastURl)
                .build();


        /***
         We put the request inside a call object. \
         Call class is a execute method we can use on our call object, to get a response
         The call.equeue method executes a call, but does so by putting it into a queue,
         this queue is provided by OkHttp and executes the call asynchronously and does
         so in the order they are added to the queue.

         At that point, the call is executing on another thread, in the background.
         Thread is the path of execution for code.

         Call backs are the communication bridge between a background working thread and the main thread,
         when the call finishes in the background, the onResponse method is called, then the code written
         inside is executed.
         */

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException{
                try {

                    /*
                 if an error is caught, it will be printed in the Log
                 and if the log is successful, we log the entire response body
                 For Asynchronous calls. Must give the app permission
                 to access the internet in manifest
                 */

                    Log.v(TAG,response.body().string());
                    if(response.isSuccessful()) {
                        Log.v(TAG, response.body().string());
                    } else{

                        // if the build was unsuccessful, the alertUserAboutError is called
                            alterUserAboutError();
                    }
                } catch (IOException e) {

                    Log.e(TAG, "IO Execption caught...",e);
                }
            }
        });



    }

    private void alterUserAboutError() {

        // Created a dialog object of the AlertDialogFragment class
        AlertDialogFragment dialog = new AlertDialogFragment();

        // Shows this in our activity
        dialog.show(getFragmentManager(),"error_dialog");
    }
}
