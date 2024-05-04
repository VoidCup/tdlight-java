package it.tdlight.reactiveexample;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author DXH
 * @version 1.0
 * @email dxh.1027@gmail.com
 * @date 2024/5/1 星期三 15:11
 * @descriptor:
 */
public class ExamplePublisher extends Thread implements Publisher<String>{

	private static final Logger LOG = LoggerFactory.getLogger(ExamplePublisher.class);


	private final List<Subscriber<String>> subscribers = new ArrayList<>(8);

	public ExamplePublisher(String name) {
		super(name);
	}

	public ExamplePublisher() {
	}

	@Override
	public synchronized void start() {
		LOG.info("start {} thread",ExamplePublisher.class.getSimpleName());
		super.start();
	}

	@Override
	public void run() {
		LOG.info("{} runnable",ExamplePublisher.class.getSimpleName());
		while (!Thread.currentThread().isInterrupted()){
			for (Subscriber<String> subscriber : subscribers) {
				subscriber.onNext(UUID.randomUUID().toString());
			}
		}
	}

	@Override
	public void subscribe(Subscriber<? super String> subscriber) {
	}
}
