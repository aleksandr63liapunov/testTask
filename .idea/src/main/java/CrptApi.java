import com.google.gson.Gson;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CrptApi {
    private final AtomicInteger requestCounter;
    private final TimeUnit timeUnit;
    private final int requestLimit;

    public CrptApi(AtomicInteger requestCounter, TimeUnit timeUnit, int requestLimit) {
        this.requestCounter = new AtomicInteger(0);
        this.timeUnit = timeUnit;
        this.requestLimit = requestLimit;
    }

    public synchronized void createDocument(Object document, String sign) {
        if (requestCounter.get() >= requestLimit) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        requestCounter.incrementAndGet();
        HttpURLConnection connection = null;
        try {
            URL url = new URL("https://ismp.crpt.ru/api/v3/lk/documents/create");
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("Post");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);
                String requestBody = convertToJson(document);
                OutputStream os = connection.getOutputStream();
                byte[] outPut = requestBody.getBytes(StandardCharsets.UTF_8);
                os.write(outPut, 0, outPut.length);
                int responseCode = connection.getResponseCode();
                if (responseCode != HttpURLConnection.HTTP_OK) {
                    System.err.println("HTTP error code: " + responseCode);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            requestCounter.decrementAndGet();
            notifyAll();

        }
    }

    private String convertToJson(Object document) {
        Gson gson = new Gson();
        return gson.toJson(document);
    }

}
