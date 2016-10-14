package aizulove.com.fxxt.utils;


import android.provider.SyncStateContract;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitUtil {
//    private static RetrofitInterface rInterface;
//
//    private static RetrofitInterface getInterface() {
//        if(rInterface == null) {
//            OkHttpClient client = new OkHttpClient.Builder()
//                    .retryOnConnectionFailure(false)
//                    .connectTimeout(15, TimeUnit.SECONDS)
//                    .readTimeout(15, TimeUnit.SECONDS)
//                    .writeTimeout(45, TimeUnit.SECONDS)
//                    .build();
//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl(Constants.BASE_URL)
//                    .client(client)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//            rInterface = retrofit.create(RetrofitInterface.class);
//        }
//        return rInterface;
//    }
//
//
//    /**
//     * get user info
//     * @param callback do in Activity after get phone verify code
//     * @param dat example: {"tran":1,"usid":482640572063010816}
//     */
//    public static void view(Callback<UserRepo> callback, String dat) {
//        Call<UserRepo> call = getInterface().view(EncryptUtil.getParameterDes(dat.length()), Constants.KEY, dat.length(), dat);
//        call.enqueue(callback);
//    }
//
//    /**
//     * save user info
//     * @param callback do in Activity after get phone verify code
//     * @param dat example: {"addr":"解放路116号902室","area":110115,"cano":"489998623574555213","cape":100001,"gend":1,"nknm":"雷公仔","ubir":"1986-03-12","ujob":"金融","usid":512354393988157440,"usnm":"李元霸","utel":"15019612125","utro":"淳朴善良、为人侠义正真..."}
//     */
//    public static void save(Callback<BaseRepo> callback, String dat, File head, File cpho, File cimg) {
//        RequestBody rb0 = null, rb1 = null, rb2 = null;
//        Call<BaseRepo> call;
//        if(head != null && head.exists()) {
//            rb0 = RequestBody.create(MediaType.parse("image/jpeg"), head);
//        }
//        if(cpho != null && cpho.exists()) {
//            rb1 = RequestBody.create(MediaType.parse("image/jpeg"), cpho);
//        }
//        if(cimg != null && cimg.exists()) {
//            rb2 = RequestBody.create(MediaType.parse("image/jpeg"), cimg);
//        }
////        RequestBody rb0 = RequestBody.create(MediaType.parse("multipart/form-data"), head);
////        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), head);
////        RequestBody requestBody1 = RequestBody.create(MediaType.parse("application/octet-stream"), head);
////        MultipartBody.Part imageFileBody = MultipartBody.Part.createFormData("head", head.getName(), rb0);
//
//        if(rb0 == null && rb1 == null && rb2 == null) {
//            call = getInterface().save0(EncryptUtil.getParameterDes(dat.length()), Constants.KEY, dat.length(), dat);
//            call.enqueue(callback);
//        } else if(rb0 != null && rb1 == null && rb2 == null) {
//            call = getInterface().save1(EncryptUtil.getParameterDes(dat.length()), Constants.KEY, dat.length(), dat, rb0);
//            call.enqueue(callback);
//        } else if(rb0 == null && rb1 != null && rb2 == null) {
//            call = getInterface().save2(EncryptUtil.getParameterDes(dat.length()), Constants.KEY, dat.length(), dat, rb1);
//            call.enqueue(callback);
//        } else if(rb0 == null && rb1 == null && rb2 != null) {
//            call = getInterface().save3(EncryptUtil.getParameterDes(dat.length()), Constants.KEY, dat.length(), dat, rb2);
//            call.enqueue(callback);
//        } else if(rb0 != null && rb1 != null && rb2 == null) {
//            call = getInterface().save4(EncryptUtil.getParameterDes(dat.length()), Constants.KEY, dat.length(), dat, rb0, rb1);
//            call.enqueue(callback);
//        } else if(rb0 != null && rb1 == null && rb2 != null) {
//            call = getInterface().save5(EncryptUtil.getParameterDes(dat.length()), Constants.KEY, dat.length(), dat, rb0, rb2);
//            call.enqueue(callback);
//        } else if(rb0 == null && rb1 != null && rb2 != null) {
//            call = getInterface().save6(EncryptUtil.getParameterDes(dat.length()), Constants.KEY, dat.length(), dat, rb1, rb2);
//            call.enqueue(callback);
//        } else if(rb0 != null && rb1 != null && rb2 != null) {
//            call = getInterface().save7(EncryptUtil.getParameterDes(dat.length()), Constants.KEY, dat.length(), dat, rb0, rb1, rb2);
//            call.enqueue(callback);
//        }
//    }
}
