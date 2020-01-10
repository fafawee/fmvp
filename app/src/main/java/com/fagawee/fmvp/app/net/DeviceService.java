package com.fagawee.fmvp.app.net;

import com.fagawee.fmvp.app.net.response.DeviceResponse;
import com.fagawee.fmvp.app.net.response.DeviceValidateNetBean;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Mr.Tian on 2020/1/10.
 */

public interface DeviceService {
    /**
     * 设备验证接口
     * @param did
     * @param flat_mac
     * @return
     */
    @FormUrlEncoded
    @POST("/api/user/deviceValidate")
    Observable<DeviceValidateNetBean> deviceValidate(@Field("did")String did, @Field("flat_mac")String flat_mac);


}
