package playground;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by jin810 on 18.01.2018.
 */
public class Conn {

    private static int HUNDRED_THOUSAND = 1000000;

    private InetSocketAddress address = new InetSocketAddress("www.google.com", 443);

    public void start() {
        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleAtFixedRate(() -> {
            try(Socket sock = new Socket()) {
                long millis = System.currentTimeMillis();
                sock.connect(address, 1000);
                sock.close();
                System.out.println("Connected " + (System.currentTimeMillis() - millis));
            } catch (IOException e) {
                System.out.println(Instant.now().toString() + e);
            }
        }, 0, 1, TimeUnit.SECONDS);
    }
}
