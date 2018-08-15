package com.nes.wisdomplayer.encrypt;

import android.content.Context;

import com.nes.wisdomplayer.io.FileUtils;
import com.nes.wisdomplayer.io.IOUtils;
import com.nes.wisdomplayer.utils.LogUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class EncryptUtil {
    private static final String TAG = EncryptUtil.class.getSimpleName();

    private static final String FILE_NAME_KEY = "iptv_icevod_key";
    private static final String FILE_NAME_ENCRYPT = "iptv_icevod_data";// ICE VOD HOST
    /*-----------------------test------------------------*/
    private static final String FILE_NAME_PORT = "iptv_icevod_port";
    private static final String FILE_NAME_USERNAME = "iptv_icevod_username";
    private static final String FILE_NAME_PASSWORD = "iptv_icevod_password";
    /*-----------------------test------------------------*/
    private Context mContext;

    public EncryptUtil(Context context) {
        mContext = context.getApplicationContext();
    }

    public void saveKey(byte[] key) {
        File file = new File(mContext.getFilesDir().getPath() + "/" + FILE_NAME_KEY);
        try {
            FileUtils.writeByteArrayToFile(file, key);
            LogUtil.i( "save file " + file.getAbsolutePath() + " success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] readKey() {
        File file = new File(mContext.getFilesDir().getPath() + "/" + FILE_NAME_KEY);
        byte[] key = null;
        try {
            key = FileUtils.readFileToByteArray(file);
            LogUtil.i( "read file " + file.getAbsolutePath() + " success");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return key;
    }

    /**
     * save host  or url
     * @param datas
     */
    public void saveEncrptDatas(byte[] datas) {
        File file = new File(mContext.getFilesDir().getPath() + "/" + FILE_NAME_ENCRYPT);
        try {
            FileUtils.writeByteArrayToFile(file, datas);
            LogUtil.i( "save file " + file.getAbsolutePath() + " success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param datas  port
     */
    public void saveEncrptPort(byte[] datas) {
        File file = new File(mContext.getFilesDir().getPath() + "/" + FILE_NAME_PORT);
        try {
            FileUtils.writeByteArrayToFile(file, datas);
            LogUtil.i( "save file " + file.getAbsolutePath() + " success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * save username
     * @param datas
     */
    public void saveEncrptUsername(byte[] datas) {
        File file = new File(mContext.getFilesDir().getPath() + "/" + FILE_NAME_USERNAME);
        try {
            FileUtils.writeByteArrayToFile(file, datas);
            LogUtil.i( "save file " + file.getAbsolutePath() + " success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * save password
     * @param datas
     */
    public void saveEncrptPassword(byte[] datas) {
        File file = new File(mContext.getFilesDir().getPath() + "/" + FILE_NAME_PASSWORD);
        try {
            FileUtils.writeByteArrayToFile(file, datas);
            LogUtil.i( "save file " + file.getAbsolutePath() + " success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * read host or url
     * @return
     */
    public byte[] readEncryptDatas() {
        File file = new File(mContext.getFilesDir().getPath() + "/" + FILE_NAME_ENCRYPT);
        byte[] datas = null;
        try {
            datas = FileUtils.readFileToByteArray(file);
            LogUtil.i( "read file " + file.getAbsolutePath() + " success");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datas;
    }

    /**
     * read port
     * @return
     */
    public byte[] readEncryptPort() {
        File file = new File(mContext.getFilesDir().getPath() + "/" + FILE_NAME_PORT);
        byte[] datas = null;
        try {
            datas = FileUtils.readFileToByteArray(file);
            LogUtil.i( "read file " + file.getAbsolutePath() + " success");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datas;
    }

    /**
     * read username
     * @return
     */
    public byte[] readEncryptUsername() {
        File file = new File(mContext.getFilesDir().getPath() + "/" + FILE_NAME_USERNAME);
        byte[] datas = null;
        try {
            datas = FileUtils.readFileToByteArray(file);
            LogUtil.i( "read file " + file.getAbsolutePath() + " success");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datas;
    }

    /**
     * read password
     * @return
     */
    public byte[] readEncryptPassword() {
        File file = new File(mContext.getFilesDir().getPath() + "/" + FILE_NAME_PASSWORD);
        byte[] datas = null;
        try {
            datas = FileUtils.readFileToByteArray(file);
            LogUtil.i( "read file " + file.getAbsolutePath() + " success");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datas;
    }

    public boolean checkKeyAndData() {
        if (hasKey() && hasData()) {
            return true;
        }
        copyEncrytpData();
        return false;
    }

    private boolean hasKey() {
        File fileKey = new File(mContext.getFilesDir().getPath() + "/" + FILE_NAME_KEY);
        return fileKey.exists() && !fileKey.isDirectory();
    }

    private boolean hasData() {
        File fileData = new File(mContext.getFilesDir().getPath() + "/" + FILE_NAME_ENCRYPT);
        return fileData.exists() && !fileData.isDirectory();
    }

    private static final String ASSET_GOGO_KEY = "encrypt/iptv_gogo_key";
    private static final String ASSET_ICEVOD_KEY = "encrypt/iptv_icevod_key";
    private static final String ASSET_GOGO_DATA = "encrypt/iptv_gogo_data";
    private static final String ASSET_ICE_VOD_DATA = "encrypt/iptv_icevod_data";//ICE VOD port
    /*-----------------------test------------------------*/
    private static final String ASSET_ICE_VOD_PROT = "encrypt/iptv_icevod_port";//ICE VOD port
    private static final String ASSET_ICE_VOD_USERNAME = "encrypt/iptv_icevod_username";//ICE VOD port
    private static final String ASSET_ICE_VOD_PASSWORD = "encrypt/iptv_icevod_password";//ICE VOD port
    /*-----------------------test------------------------*/

    public void copyEncrytpData() {
        byte[] buf = new byte[30720]; // 30k
        FileOutputStream osKey = null;
        FileOutputStream osData = null;
        FileOutputStream osPort = null;
        FileOutputStream osUsername = null;
        FileOutputStream osPassword = null;
        InputStream isKey = null;
        InputStream isData = null;
        InputStream isPort = null;
        InputStream isUsername = null;
        InputStream isPassword = null;
        try {

                isData = mContext.getAssets().open(ASSET_ICE_VOD_DATA);// 得到host的数据流
                isPort = mContext.getAssets().open(ASSET_ICE_VOD_PROT);// 得到port的数据流
                isUsername = mContext.getAssets().open(ASSET_ICE_VOD_USERNAME);// 得到username的数据流
                isPassword = mContext.getAssets().open(ASSET_ICE_VOD_PASSWORD);// 得到password的数据流
                isKey = mContext.getAssets().open(ASSET_ICEVOD_KEY);// 得到Key的数据流


            File fileKey = new File(mContext.getFilesDir().getPath() + "/" + FILE_NAME_KEY);
            File fileData = new File(mContext.getFilesDir().getPath() + "/" + FILE_NAME_ENCRYPT);
            File filePort = new File(mContext.getFilesDir().getPath() + "/" + FILE_NAME_PORT);
            File fileUsername = new File(mContext.getFilesDir().getPath() + "/" + FILE_NAME_USERNAME);
            File filePassword = new File(mContext.getFilesDir().getPath() + "/" + FILE_NAME_PASSWORD);
            osKey = new FileOutputStream(fileKey);// 得到数据库文件的写入流
            osData = new FileOutputStream(fileData);// 得到数据库文件的写入流
            osPort = new FileOutputStream(filePort);// 得到数据库文件的写入流
            osUsername = new FileOutputStream(fileUsername);// 得到数据库文件的写入流
            osPassword = new FileOutputStream(filePassword);// 得到数据库文件的写入流
            int count = -1;

            if(null != isKey && null != osKey){
                while ((count = isKey.read(buf)) != -1) {
                    osKey.write(buf, 0, count);
                }
            }

            if(null != isData && null != osData){
                while ((count = isData.read(buf)) != -1) {
                    osData.write(buf, 0, count);
                }
            }

            if(null != isPort && null != osPort){
                while ((count = isPort.read(buf)) != -1) {
                    osPort.write(buf, 0, count);
                }
            }

            if(null != isUsername && null != osUsername){
                while ((count = isUsername.read(buf)) != -1) {
                    osUsername.write(buf, 0, count);
                }
            }

            if(null != isPassword && null != osPassword){
                while ((count = isPassword.read(buf)) != -1) {
                    osPassword.write(buf, 0, count);
                }
            }
            LogUtil.i( "copy key data success");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(isData);
            IOUtils.closeQuietly(osData);
            IOUtils.closeQuietly(isKey);
            IOUtils.closeQuietly(osKey);

            IOUtils.closeQuietly(isPort);
            IOUtils.closeQuietly(osPort);
            IOUtils.closeQuietly(isUsername);
            IOUtils.closeQuietly(osUsername);
            IOUtils.closeQuietly(isPassword);
            IOUtils.closeQuietly(osPassword);

            buf = null;
        }
    }


    public static String toHex(String txt) {
        return toHex(txt.getBytes());
    }

    public static String fromHex(String hex) {
        return new String(toByte(hex));
    }

    public static byte[] toByte(String hexString) {
        int len = hexString.length() / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++)
            result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
        return result;
    }

    public static String toHex(byte[] buf) {
        if (buf == null)
            return "";
        StringBuffer result = new StringBuffer(2 * buf.length);
        for (int i = 0; i < buf.length; i++) {
            appendHex(result, buf[i]);
        }
        return result.toString();
    }

    private final static String HEX = "0123456789ABCDEF";

    private static void appendHex(StringBuffer sb, byte b) {
        sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
    }
}
