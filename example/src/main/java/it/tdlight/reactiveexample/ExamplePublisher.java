package it.tdlight.reactiveexample;

import it.tdlight.jni.TdApi.CanSendMessageToUserResultUserIsDeleted;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author DXH
 * @version 1.0
 * @email dxh.1027@gmail.com
 * @date 2024/5/1 星期三 15:11
 * @descriptor:
 */
public class ExamplePublisher extends Thread implements Publisher<String>{

	private static final Logger LOG = LoggerFactory.getLogger(ExamplePublisher.class);

	private static final int CACHE_SIZE = 10000;

	private String[] BUFFER_CACHE = new String[CACHE_SIZE];

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
			if(BUFFER_CACHE.length == CACHE_SIZE){
				try {
					TimeUnit.SECONDS.sleep(10);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
			BUFFER_CACHE
		}
	}

	@Override
	public void subscribe(Subscriber<? super String> subscriber) {
		subscribers.add(new ExampleSubscriber());
	}
}
