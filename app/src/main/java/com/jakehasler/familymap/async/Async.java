package com.jakehasler.familymap.async;

/**
 * Created by jakehasler on 3/17/16.
 */

import android.os.AsyncTask;

import com.jakehasler.familymap.MainModel;
import com.jakehasler.familymap.model.Person;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public abstract class Async extends AsyncTask<URL, Integer, Long> {

    public static JSONObject doLogin(String totalUrl, JSONObject loginBody) throws MalformedURLException {
        byte[] postData = loginBody.toString().getBytes(StandardCharsets.UTF_8);
        int reqLength = postData.length;
        String uri = "/user/login/";
        URL url = new URL(totalUrl + uri);
        System.out.println("totalUrl = " + totalUrl + uri);
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Content-Length", Integer.toString(reqLength));
            DataOutputStream ds = new DataOutputStream(conn.getOutputStream());
            ds.write(postData);
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder resOutput = new StringBuilder();
            Scanner sc = new Scanner(br);
            while(sc.hasNextLine()) {
                resOutput.append(sc.nextLine());
            }
            JSONObject loginRes = new JSONObject(resOutput.toString());
            return loginRes;
        }
        catch(IOException e) {
            System.out.println("e = " + e);
        } catch (JSONException e) {
            e.printStackTrace();
        } finally{
            conn.disconnect();
        }
        return null;
    }

    public static JSONObject getEvents(String totalUrl) throws MalformedURLException {
        String uri = "/event/";
        URL url = new URL(totalUrl + uri);
        System.out.println("totalUrl = " + totalUrl + uri);
        System.out.println("Getting Events");
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Authorization", MainModel.getAuthToken());
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder resOutput = new StringBuilder();
            Scanner sc = new Scanner(br);
            while(sc.hasNextLine()) {
                resOutput.append(sc.nextLine());
            }
            JSONObject eventRes = new JSONObject(resOutput.toString());
            return eventRes;
        }
        catch(IOException e) {
            System.out.println("e = " + e);
        } catch (JSONException e) {
            e.printStackTrace();
        } finally{
            conn.disconnect();
        }
        return null;
    }

    public static JSONObject getAllPersons(String totalUrl) throws MalformedURLException {
        String uri = "/person/";
        URL url = new URL(totalUrl + uri);
        System.out.println("totalUrl = " + totalUrl + uri);
        System.out.println("Getting Persons");
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Authorization", MainModel.getAuthToken());
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder resOutput = new StringBuilder();
            Scanner sc = new Scanner(br);
            while(sc.hasNextLine()) {
                resOutput.append(sc.nextLine());
            }
            JSONObject personsRes = new JSONObject(resOutput.toString());
            return personsRes;
        }
        catch(IOException e) {
            System.out.println("e = " + e);
        } catch (JSONException e) {
            e.printStackTrace();
        } finally{
            conn.disconnect();
        }
        return null;
    }

    public static JSONObject getSinglePerson(String totalUrl, String personId) throws MalformedURLException {
        String uri = "/person/" + personId;
        URL url = new URL(totalUrl + uri);
        System.out.println("totalUrl = " + totalUrl + uri);
        System.out.println("Getting Person: " + personId);
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Authorization", MainModel.getAuthToken());
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder resOutput = new StringBuilder();
            Scanner sc = new Scanner(br);
            while(sc.hasNextLine()) {
                resOutput.append(sc.nextLine());
            }
            JSONObject personsRes = new JSONObject(resOutput.toString());
            return personsRes;
        }
        catch(IOException e) {
            System.out.println("e = " + e);
        } catch (JSONException e) {
            e.printStackTrace();
        } finally{
            conn.disconnect();
        }
        return null;
    }
}