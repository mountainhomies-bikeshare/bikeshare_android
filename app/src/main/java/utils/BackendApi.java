package utils;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


/**
 * Created by zhiyong on 23/9/2017.
 */

public class BackendApi {
        private static final String TAG = BackendApi.class.getSimpleName();

    static InputStream is = null;
    static JSONArray jobj = null;
    static String json = "";

        // constructor
        public BackendApi() {

        }

        //this method returns json object.
        public JSONArray makeHttpRequest(String url){
            Log.e(TAG, url);
            //http client helps to send and receive data
            DefaultHttpClient httpclient = new DefaultHttpClient();
            //our request method is post
            HttpGet httpGet = new HttpGet(url);
            try {
                //get the response
                HttpResponse httpresponse = httpclient.execute(httpGet);
                HttpEntity httpentity = httpresponse.getEntity();
                // get the content and store it into inputstream object.
                is = httpentity.getContent();
                } catch (ClientProtocolException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

                try {
//convert byte-stream to character-stream.
                        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
                        StringBuilder sb = new StringBuilder();
                        String line = null;
                        try {
                                while((line = reader.readLine())!=null){
                                        sb.append(line+"\n");

                                }
//close the input stream
                            is.close();
                                json = sb.toString();
                                try {
                                        jobj = new JSONArray(json);
                                } catch (JSONException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                }
                        } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }

                } catch (UnsupportedEncodingException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                return jobj;
        }
}