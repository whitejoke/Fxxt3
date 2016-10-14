package aizulove.com.fxxt.view;

import java.util.HashMap;

import android.content.Context;
import android.widget.Toast;

/**
 * @author zhudi
 * @version ����ʱ�䣺2013-3-31 ����11:17:09
 */
public class ToastSingle{
	private static HashMap<Context, Toast> toasts = new HashMap<Context, Toast>();
	public synchronized static void showToast(Context context, String content){
		Toast toast = toasts.get(context);
		if(null == toast){
			toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
			toasts.put(context, toast);
		}
		toast.setText(content);
		toast.show();
	}
}