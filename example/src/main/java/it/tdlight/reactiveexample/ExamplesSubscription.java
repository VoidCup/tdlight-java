package it.tdlight.reactiveexample;


import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author DXH
 * @version 1.0
 * @email dxh.1027@gmail.com
 * @date 2024/5/1 星期三 16:10
 * @descriptor:
 */
public class ExamplesSubscription implements Subscription {

	private final AtomicBoolean cancelled = new AtomicBoolean(false);
	private final Subscriber<String> subscriber;
	private final Publisher<String> publisher;

	public ExamplesSubscription(Subscriber<String> subscriber, Publisher<String> publisher) {
		this.subscriber = subscriber;
		this.publisher = publisher;
	}

	@Override
	public void request(long n) {

	}

	@Override
	public void cancel() {
		cancelled.set(true);
	}
}
