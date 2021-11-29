package com.bayut.caching.component.image;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetCacheUtils {
    private static final String TAG = "NetCacheUtils";
    private Context context;

    public void getBitmapFromNet(Context context, ImageView iv, String url) {
        this.context = context;
        iv.setTag(url);
        new BitmapTask().execute(iv, url);
    }

    @SuppressLint("StaticFieldLeak")
    class BitmapTask extends AsyncTask<Object, Void, Bitmap> {
        private ImageView iv;
        private String url;

        @Override
        protected Bitmap doInBackground(Object... params) {
            iv = (ImageView) params[0];
            url = (String) params[1];

            Bitmap bitmap = downloadBitmap(url);
            Log.i(TAG, "Loading pictures from the network");

            LocalCacheUtils.saveCache(context, bitmap, url);
            MemoryCacheUtils.saveCache(bitmap, url);
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            String url = (String) iv.getTag();
            if (bitmap != null && this.url.equals(url)) {
                iv.setImageBitmap(bitmap);
            }
        }
    }

    private Bitmap downloadBitmap(String url) {
        Bitmap bitmap;
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(6000);
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                InputStream inputStream = conn.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return null;
    }

}