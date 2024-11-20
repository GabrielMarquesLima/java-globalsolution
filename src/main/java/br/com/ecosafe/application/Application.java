package br.com.ecosafe.application;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import java.net.URI;

public class Application {

    public static final String BASE_URI = "http://localhost:8080/";

    public static void main(String[] args) {
        final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), new ApiConfig());
        System.out.println("Servidor iniciado em " + BASE_URI);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Parando o servidor...");
            server.shutdownNow();
        }));

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
