module tdlight.java {
	requires tdlight.api;
	requires org.reactivestreams;
	requires org.slf4j;
	requires atlassian.util.concurrent;
	requires static com.google.zxing;
	requires static reactor.blockhound;
	requires com.google.common;
	exports it.tdlight.tdnative;
	exports it.tdlight;
	exports it.tdlight.util;
	exports it.tdlight.client;
}
