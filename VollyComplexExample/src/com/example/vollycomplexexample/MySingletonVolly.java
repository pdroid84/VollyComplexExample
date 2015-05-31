package com.example.vollycomplexexample;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

public class MySingletonVolly {
	private static MySingletonVolly mInstance;
	private ImageLoader mImageLoader;
	private static Context mCtx;
	private RequestQueue mRequestQueue;

private MySingletonVolly (Context context){
	Log.d("DEB", "MySingletonVolly Constructor");
	mCtx = context;
	mRequestQueue = getRequestQueue();
	mImageLoader = new ImageLoader(mRequestQueue,new ImageLoader.ImageCache() {
		
		private final LruCache<String,Bitmap> cache = new LruCache<String, Bitmap>(20);
		
		@Override
		public void putBitmap(String url, Bitmap bitmap) {
			// TODO Auto-generated method stub
			cache.put(url,bitmap);
		}
		
		@Override
		public Bitmap getBitmap(String url) {
			// TODO Auto-generated method stub
			return cache.get(url);
		}
	});
}
public static synchronized MySingletonVolly getInstance (Context context){
	Log.d("DEB", "MySingletonVolly - getInstance");
	if (mInstance==null){
		mInstance = new MySingletonVolly(context);
	}
	return mInstance;
}
public RequestQueue getRequestQueue (){
	Log.d("DEB", "MySingletonVolly - getRequestQueue");
	if(mRequestQueue==null){
		// getApplicationContext() is key, it keeps you from leaking the
        // Activity or BroadcastReceiver if someone passes one in.
		mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
	}
	return mRequestQueue;
}
public <T> void addToRequestQueue(Request<T> req){
	Log.d("DEB", "MySingletonVolly - addToRequestQueue");
	getRequestQueue().add(req);
}
public ImageLoader getImageLoader(){
	Log.d("DEB", "MySingletonVolly - getImageLoader");
	return mImageLoader;
}
}
