package it.tdlight.reactiveexample;


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
	private long requested = 10;

	public ExamplesSubscription() {
	}

	@Override
	public void request(long n) {
		requested = n;
	}

	@Override
	public void cancel() {
		cancelled.set(true);
	}
}
