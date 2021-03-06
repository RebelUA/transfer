package com.rebel.transfer;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.rebel.transfer.repository.TransferRepo;
import com.rebel.transfer.service.TransferService;
import com.rebel.transfer.web.TransferWebServer;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class TransferApp {

    private final TransferWebServer webServer;
    private final HazelcastInstance hazelcastInstance;
    private final Config config = ConfigFactory.defaultApplication();

    TransferApp() {
        this.hazelcastInstance = Hazelcast.newHazelcastInstance();
        var transferRepo = new TransferRepo(hazelcastInstance);
        var transferService = new TransferService(transferRepo);
        this.webServer = new TransferWebServer(config.getInt("rest-port"), transferService);
    }

    void launch(CompletableFuture<Void> startFuture) {
        webServer.run(Executors.newFixedThreadPool(config.getInt("executor-threads")), startFuture);
    }

    void shutDown() {
        webServer.shutdown();
        hazelcastInstance.shutdown();
    }
}
