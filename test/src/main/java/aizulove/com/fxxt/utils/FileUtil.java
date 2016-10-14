package aizulove.com.fxxt.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.BitmapDrawable;


public class FileUtil {
	/**
	 * 创建指定目录
	 * @author wangjie
	 * @param pathName 要创建的目录名
	 */
	public static void createDir(String pathName){
		File dir = new File(pathName);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	public static Bitmap getHttpBitmap(String url) {
		URL myFileURL;
		Bitmap bitmap = null;
		try {
			BitmapFactory.Options bfOptions=new BitmapFactory.Options();
			bfOptions.inDither=false;
			bfOptions.inPurgeable=true;
			bfOptions.inInputShareable=true;
			bfOptions.inSampleSize = 2;
			bfOptions.inTempStorage=new byte[35 * 1024];
			myFileURL = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) myFileURL.openConnection();
			//conn.setConnectTimeout(6000);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			InputStream is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is,null,bfOptions);
			is.close();
		} catch (Exception e) {
			return bitmap;
		}
		return bitmap;
	}

	public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
		if(null!=bitmap){
			Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
			Canvas canvas = new Canvas(output);
			final int color = 0xff424242;
			final Paint paint = new Paint();
			final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
			final RectF rectF = new RectF(rect);
			final float roundPx = pixels;
			paint.setAntiAlias(true);
			canvas.drawARGB(0, 0, 0, 0);
			paint.setColor(color);
			canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
			paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
			canvas.drawBitmap(bitmap, rect, rect, paint);
			return output;
		}else{
			return null;
		}
	}

	public static boolean fileIsExists(String fileName) {
		try {
			File f = new File(fileName);
			if (f.exists()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static BitmapDrawable readBitmap(Resources resources, String filePath) {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.ARGB_4444;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		return (new BitmapDrawable(resources, BitmapFactory.decodeFile(filePath, opt)));
	}

	public static String md5(String paramString) {
		String returnStr;
		try {
			MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
			localMessageDigest.update(paramString.getBytes());
			returnStr = byteToHexString(localMessageDigest.digest());
			return returnStr;
		} catch (Exception e) {
			return paramString;
		}
	}

	public static String byteToHexString(byte[] b) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			hexString.append(hex.toUpperCase());
		}
		return hexString.toString();
	}

	/**
	 * 创建一个新文件
	 * @param pathFileName
	 * @return
	 */
	public static File createFile(String pathFileName){
		File file = null;
		String path = pathFileName.substring(0, pathFileName.lastIndexOf("/"));

		try {
			createDir(path);
			file = new File(pathFileName);
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

	/**
	 * 把bitmap保存到sd卡上
	 * @param bm
	 * @return 0:成功; 1:失败
	 */
	public static int saveBitmap2SD(String pathFileName, Bitmap bm){
		File file = null;
		try {
			file = createFile(pathFileName);
			FileOutputStream out = new FileOutputStream(file.getPath());
			bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}


	/**
	 * 递归删除文件和文件夹
	 * @param file    要删除的根目录
	 */
	public static void RecursionDeleteFile(File file) {
		if (file.isFile()) {
			file.delete();
			return;
		}
		if (file.isDirectory()) {
			File[] childFile = file.listFiles();
			if (childFile == null || childFile.length == 0) {
				file.delete();
				return;
			}
			for (File f : childFile) {
				RecursionDeleteFile(f);
			}
			file.delete();
		}
	}

	/**
	 * String --> InputStream
	 * @param str
	 * @return
	 */
	public static InputStream String2InputStream(String str) {
		ByteArrayInputStream stream = new ByteArrayInputStream(str.getBytes());
		return stream;
	}

	public static String escapeFilename(String filename){
		return filename.replaceAll("[/\\:?''<>|/\n]", "_");
	}
}
