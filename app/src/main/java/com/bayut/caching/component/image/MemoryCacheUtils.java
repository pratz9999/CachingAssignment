package com.bayut.caching.component.image;

import android.graphics.Bitmap;
import android.util.LruCache;

public class MemoryCacheUtils {
    static {

        long maxMemory = Runtime.getRuntime().maxMemory();
        lruCache = new LruCache<String, Bitmap>((int) (maxMemory / 8)) {
            //3. Get the size of each picture
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    private static final LruCache<String, Bitmap> lruCache;

    public static void saveCache(Bitmap bitmap, String url) {
        lruCache.put(url, bitmap);
    }

    public static Bitmap readCache(String url) {
        return lruCache.get(url);
    }
}