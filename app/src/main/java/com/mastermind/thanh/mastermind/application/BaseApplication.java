package com.mastermind.thanh.mastermind.application;

import com.mastermind.thanh.mastermind.asynctask.ListGroupAsynctask;
import com.mastermind.thanh.mastermind.constant.APIConstant;
import com.mastermind.thanh.mastermind.utils.MD5;

/**
 * Created by Thanh on 12/22/2015.
 */
public class BaseApplication {
    public static void getListGroups() {
        String url = APIConstant.HOME_URL + APIConstant.LIST_GROUP;
        String api_secret_key = MD5.encryptMD5(APIConstant.SECRET_KEY);
        String[] keys = {APIConstant.API_SECRET_KEY};
        String[] values = {api_secret_key};
        new ListGroupAsynctask(keys, values, url).execute();
    }
}
