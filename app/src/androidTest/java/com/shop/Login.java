package com.shop;

import android.util.Log;
import okhttp3.*;

import java.io.IOException;
import java.util.*;

public class Login {

    private String android_code = "a01973b3-38a2-4260-8caa-00c4b53e6d86";
    private String url = "http://sc.minxj.com/api/";
    private String secret_key = "m$c2Bk3kb^J$7MDE";
    private String phone = "18512885306";
    private String code = "779417";

    public Login(){

    }



    public void startLogin(){

        Map<String,String> map = new HashMap<>();
        map.put("phone",phone);
        map.put("code",code);
        map.put("mode","2");

        final String sign = getSign(map);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request head = chain.request().newBuilder()
                                .addHeader("device-id",android_code)
                                .addHeader("sign",sign)
                                .build();
                        return chain.proceed(head);
                    }
                })
                .build();
        RequestBody requestBody = new FormBody.Builder()
                .add("mode","2")
                .add("phone",phone)
                .add("code",code)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("error:",e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response){
                String result = null;
                try {
                    result = response.body().string();
                    Log.i("response:",result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public String getSign(Map<String,String> map){
        String result = "";
        try {
            if(map.size() > 0) {
                List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(map.entrySet());
                // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
                Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {

                    public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                        return (o1.getKey()).toString().compareTo(o2.getKey());
                    }
                });

                // 构造签名键值对的格式
                StringBuilder sb = new StringBuilder();
                for (Map.Entry<String, String> item : infoIds) {
                    if (item.getKey() != null || item.getKey() != "") {
                        String key = item.getKey();
                        String val = item.getValue();
                        if (!(val == "" || val == null)) {
                            sb.append(key + "=" + val + "&");
                        }
                    }
                }
                sb = sb.deleteCharAt(sb.length() - 1);
//			sb.append(PropertyManager.getProperty("SIGNKEY"));
                result = sb.toString();
            }
            //进行MD5加密
            result = MD5Utils.MD5Encode(result+secret_key,"utf8").toUpperCase();
        } catch (Exception e) {
            return null;
        }
        return result;
    }

}
