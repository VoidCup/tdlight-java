package it.tdlight.reactiveexample;

import it.tdlight.ClientFactory;
import it.tdlight.ReactiveTelegramClient;
import it.tdlight.netio.NetProxy;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author DXH
 * @version 1.0
 * @email dxh.1027@gmail.com
 * @date 2024/5/1 星期三 14:33
 * @descriptor:
 */
public class ReactiveExample {

	public static final Logger log = LoggerFactory.getLogger(ReactiveExample.class);

	public static void main(String[] args) throws Exception{

		ClientFactory clientFactory = ClientFactory.create();

		ReactiveTelegramClient reactive = clientFactory.createReactive();

		reactive.createAndRegisterClient();

		reactive.setListener(signal -> System.out.println("signalListener: "+signal.getUpdate().getClass().getSimpleName()));

		reactive.send(NetProxy.localSocket5Proxy().covertAddProxyFunction(), Duration.ofSeconds(180));

		TimeUnit.DAYS.sleep(1);
	}
}
