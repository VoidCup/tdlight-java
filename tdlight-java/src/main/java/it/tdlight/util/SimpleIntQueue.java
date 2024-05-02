package it.tdlight.util;

import java.util.function.IntConsumer;

public final class SimpleIntQueue {
	public int size = 0;
	public int[] a = new int[16];

	//将指定的整数 i 添加到队列中。如果队列已满，则会自动扩容数组 a 的大小，并将元素添加到队列的末尾
	public void add(int i) {
		if (size >= a.length) {
			int[] prev = a;
			a = new int[a.length << 1];
			System.arraycopy(prev, 0, a, 0, prev.length);
		}
		a[size++] = i;
	}

	//遍历消费队列元素消费完毕后重置队列
	public void drain(IntConsumer consumer) {
		for (int i = 0; i < size; i++) {
			consumer.accept(a[i]);
		}
		reset();
	}

	//重置队列
	public void reset() {
		size = 0;
	}

	//是否包含元素
	public boolean isContentful() {
		return size > 0;
	}
}
