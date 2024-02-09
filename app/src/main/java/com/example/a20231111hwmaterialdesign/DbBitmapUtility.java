package com.example.a20231111hwmaterialdesign;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

import java.io.ByteArrayOutputStream;

/**
* https://stackoverflow.com/questions/9357668/how-to-store-image-in-sqlite-database
**/

public class DbBitmapUtility {

    // convert from bitmap to byte array
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    public static Bitmap retrieveResource(Context context, int res_id, Point sz) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), res_id, opts);
        opts.inJustDecodeBounds = false;
        // does not introduce an absolute upper bound, but protects against unreasonably hi-res images
        opts.inSampleSize = Math.max((opts.outWidth + sz.x - 1) / sz.x, (opts.outHeight + sz.y - 1) / sz.y);
        return BitmapFactory.decodeResource(context.getResources(), res_id, opts);
    }
}