package com.goketech.smartcommunity.wxapi;

import android.content.Intent;
import android.widget.Toast;
import com.goketech.smartcommunity.R;
import com.goketech.smartcommunity.apps.MyApp;
import com.goketech.smartcommunity.base.BaseActivity;
import com.goketech.smartcommunity.utils.MyUtils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import okhttp3.FormBody;
import okhttp3.RequestBody;

import java.util.HashMap;
import java.util.Map;

public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler, WxContract.View {
    private IWXAPI api;
    private String code;

    @Override
    protected IBasePresenter getPresenter() {
        return new WxLoginPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_wx_login;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
        finish();
    }

    @Override
    protected void initView() {
        super.initView();
        api = WXAPIFactory.createWXAPI(this, MyApp.WX_APPID, false);
        api.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
    }

    @Override
    public void onResp(BaseResp baseResp) {
        String result = "";
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                code = ((SendAuth.Resp) baseResp).code;
                //调用微信登录方法
                wxLogin(code);
//                SharedPreferences WxSp = getApplicationContext().getSharedPreferences(PrefParams.spName, Context.MODE_PRIVATE);
//                SharedPreferences.Editor WxSpEditor = WxSp.edit();
//                WxSpEditor.putString(PrefParams.CODE, code);
//                WxSpEditor.apply();
//                Intent intent = new Intent();
//                intent.setAction("authlogin");
//                WXEntryActivity.this.sendBroadcast(intent);
//                finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = "发送取消";
                Toast.makeText(this, result, Toast.LENGTH_LONG).show();
                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = "发送被拒绝";
                Toast.makeText(this, result, Toast.LENGTH_LONG).show();
                finish();
                break;
            default:
                result = "发送返回";
                Toast.makeText(this, result, Toast.LENGTH_LONG).show();
                finish();
                break;
        }
    }

    /**
     * 微信登录
     * @param code
     */
    private void wxLogin(String code){
        Map<String, String> map = new HashMap<>();
        map.put("wx_code", code);
        String sign = MyUtils.Companion.getSign(map);

        RequestBody requestBody = new FormBody.Builder()
                .add("wx_code", code)
                .add("sign",sign)
                .build();
        //((WxLoginPresenter)mPresenter).wxLogin(requestBody);

    }

    @Override
    public void wxLoginReturn(WeiCode_bean data) {
        String access_token = data.getData().getAccess_token();
        String openid = data.getData().getOpenid();
        Intent intent = new Intent(this, BindPhone.class);
        intent.putExtra("access_token",access_token);
        intent.putExtra("openid",openid);
        startActivity(intent);
        finish();
//        Map<String, String> map = new HashMap<>();
//        map.put("mode", "3");
//        map.put("code", "230027");
//        map.put("openid", openid);
//        map.put("phone", "13132158823");
//        map.put("access_token", access_token);
//        String sign = ASCIIUtils.getSign(map);
//
//        RequestBody requestBody = new FormBody.Builder()
//               .add("code", "230027")
//                .add("mode","3")
//                .add("openid",openid)
//                .add("access_token",access_token)
//                .add("phone","13132158823")
//                .add("sign",sign)
//                .build();
//        ((WxLoginPresenter)mPresenter).login(requestBody);

    }

    @Override
    public void loginReturn(Landing_bean data) {
        Toast.makeText(this, ""+data.getData().getId(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String errorMsg) {

    }
}
