package com.example.acer.readernew.Utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by acer on 2017/4/19.
 */

public class DiskCacheManager {
    private long maxSize = 10 * 1024 * 1024;
    private static DiskLruCache diskLruCache = null;
    private DiskLruCache.Editor mEditor;
    private DiskLruCache.Snapshot mSnapshot;

    public DiskCacheManager(Context context, String uniqueName) {
        try {
            if (diskLruCache != null) {
                diskLruCache.close();
                diskLruCache = null;
            }
            diskLruCache = DiskLruCache.open(getDiskCacheDir(context, uniqueName), getAppVersion(context), 1, maxSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param key   键
     * @param value 值
     *              保存数据
     */
    public void put(String key, String value) {
        DiskLruCache.Editor editor = null;
        BufferedWriter bufferedWriter = null;
        try {
            editor = editor(key);
            if (editor == null) {
                return;
            }
            OutputStream outputStream = editor.newOutputStream(0);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            bufferedWriter.write(value);
            bufferedWriter.flush();
            bufferedWriter.close();
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                if (editor != null) {
                    editor.abort();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally {
                try {
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * @param key 键值
     * @return 字符串
     */
    public String get(String key) {
        InputStream inputStream = getCacheInputStream(key);
        if (inputStream == null) {
            return null;
        }
        try {
            return inputStream2String(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 同步记录文件
     */
    public static void flush() {
        if (diskLruCache != null) {
            try {
                diskLruCache.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private DiskLruCache.Editor editor(String key) throws IOException {
        key = hashKeyForDisk(key);
        if (diskLruCache != null) {
            mEditor = diskLruCache.edit(key);
        }
        return mEditor;
    }

    private DiskLruCache.Snapshot snapshot(String key) {
        if (diskLruCache != null) {
            try {
                mSnapshot = diskLruCache.get(key);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mSnapshot;
    }

    /**
     * @param key 键
     * @return 输入流
     * 根据键获取输入流，方便将输入流转换为其他类型
     */
    private InputStream getCacheInputStream(String key) {
        key = hashKeyForDisk(key);
        InputStream in;
        DiskLruCache.Snapshot snapshot = snapshot(key);
        if (snapshot == null) {
            return null;
        }
        in = snapshot.getInputStream(0);
        return in;
    }

    /**
     * inputStream 转 String
     *
     * @param is 输入流
     * @return 结果字符串
     */
    private String inputStream2String(InputStream is) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        StringBuilder buffer = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }

    private File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if ((Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable())
                && context.getExternalCacheDir() != null) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    private int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }


    private String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    public static long getSize() {
        if (diskLruCache != null)
            return diskLruCache.size();
        else return 0;
    }
    public static void clearAll(){
        if (diskLruCache!=null){
            try {
                diskLruCache.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
