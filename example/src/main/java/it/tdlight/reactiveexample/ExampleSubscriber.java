package it.tdlight.reactiveexample;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * @author DXH
 * @version 1.0
 * @email dxh.1027@gmail.com
 * @date 2024/5/1 星期三 16:13
 * @descriptor:
 */
public class ExampleSubscriber implements Subscriber<String> {

	private Subscription subscription;

	public ExampleSubscriber() {
	}

	@Override
	public void onSubscribe(Subscription s) {
		this.subscription = s;
	}

	@Override
	public void onNext(String s) {
		subscription.request(1);
	}

	@Override
	public void onError(Throwable t) {

	}

	@Override
	public void onComplete() {

	}
}
