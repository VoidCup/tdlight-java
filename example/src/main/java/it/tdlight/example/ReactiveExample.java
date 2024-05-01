package it.tdlight.example;

import it.tdlight.ClientFactory;
import it.tdlight.ReactiveTelegramClient;
import it.tdlight.netio.NetProxy;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author DXH
 * @version 1.0
 * @email dxh.1027@gmail.com
 * @date 2024/5/1 星期三 14:33
 * @descriptor:
 */
public class ReactiveExample {

	public static void main(String[] args) throws Exception{

		ClientFactory clientFactory = ClientFactory.create();

		ReactiveTelegramClient reactive = clientFactory.createReactive();

		reactive.send(NetProxy.localSocket5Proxy().covertAddProxyFunction(), Duration.ofSeconds(180));


		reactive.createAndRegisterClient();
	}
}
