package com.example.vollycomplexexample;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;

import android.support.v7.app.ActionBarActivity;
import android.graphics.Bitmap;
import android.media.ImageReader;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;


public class MainActivity extends ActionBarActivity {
	ImageView imgView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgView = (ImageView) findViewById(R.id.imageView);
        String url = "http://i.imgur.com/7spzG.png";
        RequestQueue queue = MySingletonVolly.getInstance(this.getApplicationContext()).getRequestQueue();
        ImageRequest request = new ImageRequest(url, new Response.Listener<Bitmap>() {
			@Override
			public void onResponse(Bitmap arg0) {
				// TODO Auto-generated method stub
				Log.d("DEB", "Voly response is successfull");
				imgView.setImageBitmap(arg0);
				
			}
		}, 0, 0, null, new Response.ErrorListener(){
			public void onErrorResponse(VolleyError error) {
				Log.d("DEB", "Voly response is NOT successfull");
				Log.d("DEB", "Volly error - "+error);
	            imgView.setImageResource(R.drawable.ic_launcher);
	        }
		});
       //All three work!!
       // Option 1
       //  queue.add(request);
       //option 2 - This is what is used as told by developer site
        MySingletonVolly.getInstance(this).addToRequestQueue(request);
       // Option 3
       //  ImageLoader imgLoader = MySingletonVolly.getInstance(this).getImageLoader();
      //   imgLoader.get(url, ImageLoader.getImageListener(imgView, R.drawable.ic_launcher, R.drawable.ic_launcher));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
