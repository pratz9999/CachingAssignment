package com.bayut.caching.component.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

public class BitmapUtils {
    private static final String TAG = "BitmapUtils";

    static {
        netCacheUtils = new NetCacheUtils();
        localCacheUtils = new LocalCacheUtils();
        memoryCacheUtils = new MemoryCacheUtils();
    }

    private static final NetCacheUtils netCacheUtils;
    private static final LocalCacheUtils localCacheUtils;
    private static final MemoryCacheUtils memoryCacheUtils;

    //display picture
    public static void display(Context context, ImageView iv, String url) {
        Bitmap bitmap;
        //Memory cache
        bitmap = memoryCacheUtils.readCache(url);
        if (bitmap != null) {
            iv.setImageBitmap(bitmap);
            Log.i(TAG, "Getting pictures from memory");
            return;
        }
        //Disk cache
        bitmap = localCacheUtils.readCache(context, url);
        if (bitmap != null) {
            iv.setImageBitmap(bitmap);
            Log.i(TAG, "Getting pictures from disk");
            return;
        }
        //Network cache
        netCacheUtils.getBitmapFromNet(context, iv, url);
    }

}