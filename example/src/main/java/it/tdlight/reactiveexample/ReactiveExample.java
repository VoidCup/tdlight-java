package it.tdlight.reactiveexample;

import it.tdlight.ClientFactory;
import it.tdlight.ReactiveTelegramClient;
import it.tdlight.Signal;
import it.tdlight.SignalListener;
import it.tdlight.jni.TdApi;
import it.tdlight.jni.TdApi.Object;
import it.tdlight.netio.NetProxy;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
