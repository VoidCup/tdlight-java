package it.tdlight.reactiveexample;

import java.util.UUID;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

/**
 * @author DXH
 * @version 1.0
 * @email dxh.1027@gmail.com
 * @date 2024/5/1 星期三 17:49
 * @descriptor:
 */
public class ExampleReactive {

	public static void main(String[] args) throws InterruptedException {
		SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
		Flow.Subscriber<String> subscriber = new Subscriber<String>() {

			private Subscription subscription;

			@Override
			public void onSubscribe(Subscription subscription) {
				System.out.println("onSubscribe: ");
				subscription.request(1);
				this.subscription = subscription;
			}

			@Override
			public void onNext(String item) {
				System.out.println("onNext: "+item);
				this.subscription.request(1);
			}

			@Override
			public void onError(Throwable throwable) {
				System.out.println("onError: "+throwable.getMessage());
			}

			@Override
			public void onComplete() {
				System.out.println("onComplete: ");
			}
		};
		publisher.subscribe(subscriber);
		for (int i = 0; i < 100; i++) {
			publisher.submit(UUID.randomUUID().toString());
		}
		TimeUnit.DAYS.sleep(1);
	}
}
