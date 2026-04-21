package dev.michalkochaniak.portfolio.selenium.support;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public final class LocalDemoServer {
    private HttpServer server;
    private String baseUrl;

    public void start() {
        if (server != null) {
            return;
        }

        try {
            server = HttpServer.create(new InetSocketAddress(0), 0);
            server.createContext("/", this::serveIndex);
            server.start();
            baseUrl = "http://127.0.0.1:" + server.getAddress().getPort();
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to start local demo server", exception);
        }
    }

    public void stop() {
        if (server != null) {
            server.stop(0);
            server = null;
        }
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    private void serveIndex(HttpExchange exchange) throws IOException {
        if (!"/".equals(exchange.getRequestURI().getPath()) && !"/index.html".equals(exchange.getRequestURI().getPath())) {
            byte[] notFound = "Not found".getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(404, notFound.length);
            try (OutputStream outputStream = exchange.getResponseBody()) {
                outputStream.write(notFound);
            }
            return;
        }

        try (InputStream inputStream = getClass().getResourceAsStream("/demo-app/index.html")) {
            if (inputStream == null) {
                throw new IllegalStateException("Demo app resource not found");
            }

            byte[] content = inputStream.readAllBytes();
            exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(200, content.length);
            try (OutputStream outputStream = exchange.getResponseBody()) {
                outputStream.write(content);
            }
        }
    }
}
