package fr.sanwire.launcher.api.auth.util;

import com.google.gson.Gson;
import fr.sanwire.launcher.api.auth.exception.AuthException;
import fr.sanwire.launcher.core.utils.logger.LogColor;
import fr.sanwire.launcher.core.utils.logger.LogType;
import fr.sanwire.launcher.core.utils.logger.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpRequester {

    private final Gson gson = new Gson();

    private final Logger logger;

    public HttpRequester(Logger logger){
        this.logger = logger;
    }

    public Object gsonRequest(Object request, Class<?> responseModel, String point) throws AuthException {
        String response;

        try {
            if (logger != null) logger.log("Authenticating to Mojang Service", new LogType("auth", LogColor.PURPLE));
            response = postRequest("https://authserver.mojang.com/" + point, request);
        } catch (IOException e){
            if (logger != null) logger.log("Cannot send request to URL - " + e.getClass().getName() + " - " + e.getMessage(), LogType.ERROR);
            throw new AuthException("Cannot send request to URL - " + e.getClass().getName() + " - " + e.getMessage());
        }

        if (responseModel != null) return gson.fromJson(response, responseModel);
        else return null;
    }

    private String postRequest(String serverUrl, Object request) throws AuthException, IOException {
        final String json = gson.toJson(request);
        final byte[] jsonBytes = json.getBytes(StandardCharsets.UTF_8);
        final URL url = new URL(serverUrl);
        final HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setDoOutput(true);
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        connection.setRequestProperty("Content-Length", String.valueOf(jsonBytes.length));
        final DataOutputStream output = new DataOutputStream(connection.getOutputStream());
        output.write(jsonBytes, 0, jsonBytes.length);
        output.flush();
        output.close();

        connection.connect();

        final int responseCode = connection.getResponseCode();

        InputStream is;
        if (responseCode == 200) is = connection.getInputStream();
        else is = connection.getErrorStream();

        String response;

        final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        response = reader.readLine();
        try {
            reader.close();
        } catch (IOException e){
            if (logger != null) logger.log(e.getMessage(), LogType.ERROR);
            e.printStackTrace();
        }
        connection.disconnect();

        while (response != null && response.startsWith("\uFEFF")) response = response.substring(1);

        if (responseCode != 200) throw new AuthException("Failed to authenticate");

        return response;
    }

}
