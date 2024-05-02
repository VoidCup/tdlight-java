package it.tdlight.reactiveexample;

import it.tdlight.ClientFactory;
import it.tdlight.ReactiveTelegramClient;
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

		reactive.send(NetProxy.localSocket5Proxy().covertAddProxyFunction(), Duration.ofSeconds(180)).subscribe(new Subscriber<Object>() {

			private Subscription subscription;

			@Override
			public void onSubscribe(Subscription s) {
				log.info("onSubscribe");
				this.subscription = s;
				this.subscription.request(1);
			}

			@Override
			public void onNext(Object object) {
				log.info("onNext");
				this.subscription.request(1);
			}

			@Override
			public void onError(Throwable t) {
				log.warn("onError", t);
			}

			@Override
			public void onComplete() {
				log.info("onComplete");
			}
		});


		TimeUnit.DAYS.sleep(1);
	}
}
