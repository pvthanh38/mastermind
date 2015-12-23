package com.mastermind.thanh.mastermind.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.mastermind.thanh.mastermind.constant.APIConstant;
import com.mastermind.thanh.mastermind.models.ListGroup;
import com.mastermind.thanh.mastermind.services.RequestApi;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thanh on 12/22/2015.
 */
public class ListGroupAsynctask extends AsyncTask<Void, Void, String>{
    private String[] keys, values;
    private String url;

    public ListGroupAsynctask(String[] keys, String[] values, String url) {
        this.keys = keys;
        this.values = values;
        this.url = url;
    }
    @Override
    protected void onPostExecute(String result) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<ListGroup> cats = new ArrayList<ListGroup>();
            JsonNode node = mapper.readTree(result);
            node = node.get(APIConstant.DATA);
            TypeReference<List<ListGroup>> typeReference = new TypeReference<List<ListGroup>>() {
            };
            cats = mapper.readValue(node.traverse(), typeReference);
            ListGroup cat = ListGroup.getInstance();
            cat.setListGroup(cats);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(Void... params) {
        //String result = ;
        return new RequestApi().getInfoUsingPOST(url, keys, values);
    }
}
