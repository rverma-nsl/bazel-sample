package module0packageJava0;

import module0packageJava0.GreeterGrpc;
import module0packageJava0.HelloReply;
import module0packageJava0.HelloRequest;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Greeter implementation which replies identifying itself with its hostname. */
public final class HostnameGreeter extends GreeterGrpc.GreeterImplBase {
  private static final Logger logger = Logger.getLogger(HostnameGreeter.class.getName());

  private final String serverName;

  public HostnameGreeter(String serverName) {
    if (serverName == null) {
      serverName = determineHostname();
    }
    this.serverName = serverName;
  }

  @Override
  public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
    HelloReply reply = HelloReply.newBuilder()
        .setMessage("Hello " + req.getName() + ", from " + serverName)
        .build();
    responseObserver.onNext(reply);
    responseObserver.onCompleted();
  }

  private static String determineHostname() {
    try {
      return InetAddress.getLocalHost().getHostName();
    } catch (IOException ex) {
      logger.log(Level.INFO, "Failed to determine hostname. Will generate one", ex);
    }
    // Strange. Well, let's make an identifier for ourselves.
    return "generated-" + new Random().nextInt();
  }
}
