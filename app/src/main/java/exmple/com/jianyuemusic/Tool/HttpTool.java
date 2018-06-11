package exmple.com.jianyuemusic.Tool;

/**
 * Created by Admin on 2018/6/4.
 */

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

/**
 * @Author: LKL
 * @Date: 2018/6/4 15:47
 * @CodeInfo:网络工具类
 */
public class HttpTool {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(8000, TimeUnit.SECONDS)//设置读取超时时间
            .writeTimeout(8000, TimeUnit.SECONDS)//设置写的超时时间
            .connectTimeout(8000, TimeUnit.SECONDS)//设置连接超时时间
            .build();
    /**
     * 获取网络链接重定向地址
     * @param path
     * @return String
     * @throws Exception
     */
    public static String getRealUrl(String path) {
        String url = null;
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
            conn.setInstanceFollowRedirects(false);
            conn.setConnectTimeout(5000);
            url = conn.getHeaderField("Location");
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }
    /**
     * @Author: LKL
     * @Date: 2018/6/4 15:54
     * @CodeInfo:网络请求，上传JSON数据，接受JSON数据
     */
    public static String getUriInfo(final String address,final String jsonstring){
        String result = null;
        try {
            RequestBody body = RequestBody.create(JSON, jsonstring);
            Request request = new Request.Builder()
                    .url(address)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                if (response.isSuccessful()) {
                    try {
                        result = response.body().string();
                        Log.i(TAG, "get: "+result);
                        System.out.printf(""+result);
                        if (!TextUtils.isEmpty(result)) {
                            JSONObject obj = new JSONObject(result);
                            System.out.println(result);
                        }
                        return result;
                    } catch (Exception e) {
                        Log.e("----->", "Exception = " + e);
                    }
                }else {
                    return "ERROR:Network unconnected";
                }
            } else {
                throw new IOException("Unexpected code " + response);
            }
        }  catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
