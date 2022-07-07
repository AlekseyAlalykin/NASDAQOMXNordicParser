package Parser;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RequestSender {
    public String sendRequest() throws InterruptedException, IOException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://www.nasdaqomxnordic.com/webproxy/DataFeedProxy.aspx"))
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .header("DNT", "1")
                .header("Origin", "http://www.nasdaqomxnordic.com")
                .header("Referer", "http://www.nasdaqomxnordic.com/optionsandfutures/microsite?Instrument=SE0000337842")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36")
                .header("X-Requested-With", "XMLHttpRequest")
                .header("Accept", "text/html, */*; q=0.01")
                .header("Accept-Encoding", "identity")
                .header("Accept-Language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7")
                .method("POST", HttpRequest.BodyPublishers.ofString(getRequestBody()))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    private String getRequestBody() throws IOException{
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("RequestBody.txt");
        String requestBody = new String(is.readAllBytes());
        is.close();
        return requestBody;
    }

}
