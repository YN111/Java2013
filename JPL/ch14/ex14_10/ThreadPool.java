/*
 * Copyright (C) 2012, 2013 RICOH Co., Ltd. All rights reserved.
 */

import java.util.LinkedList;

/**
 * Simple Thread Pool class.
 *
 * This class can be used to dispatch an Runnable object to
 * be exectued by a thread.
 *
 * [Instruction]
 *  Implement one constructor and three methods.
 *  Don't forget to write a Test program to test this class. 
 *  Pay attention to @throws tags in the javadoc.
 *  If needed, you can put "synchronized" keyword to methods.
 *  All classes for implementation must be private inside this class.
 *  Don't use java.util.concurrent package.
 */
public class ThreadPool {

	enum MyState {
		INITIAL, RUN, STOP;
	}

	private MyState mState = MyState.INITIAL;
	private LinkedList<Runnable> mQueue;
	private int mQueueSize;
	private Thread[] mThreads;

	/**
	 * Worker Thread class
	 */
	private class WorkerThread extends Thread {

		/**
		 * Execute Runnable objects which is included in the queue.
		 * If the queue is empty, then this method invocation will be blocked
		 * until the queue is not empty.
		 * When notified that state has become STOP, this method will finish 
		 * after Runnable object's terminations.
		 */
		@Override
		public void run() {
			while (true) {
				Runnable r;
				synchronized (ThreadPool.this) {
					while (mQueue.isEmpty()) {
						if (mState == MyState.RUN) {
							try {
								ThreadPool.this.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						if (mState == MyState.STOP && mQueue.isEmpty()) {
							return;
						}
					}
					r = mQueue.remove();
					ThreadPool.this.notifyAll();
				}
				r.run();
				if (mState == MyState.STOP) {
					return;
				}
			}
		}
	}

	/**
	 * Constructs ThreadPool.
	 *
	 * @param queueSize the max size of queue
	 * @param numberOfThreads the number of threads in this pool.
	 *
	 * @throws IllegalArgumentException if either queueSize or numberOfThreads
	 *         is less than 1
	 */
	public ThreadPool(int queueSize, int numberOfThreads) {
		if (queueSize < 1 || numberOfThreads < 1) {
			throw new IllegalArgumentException();
		}
		mThreads = new Thread[numberOfThreads];
		mQueue = new LinkedList<Runnable>();
		mQueueSize = queueSize;
	}

	/**
	 * Starts threads.
	 *
	 * @throws IllegalStateException if threads has been already started.
	 */
	public void start() {
		if (mState != MyState.INITIAL) {
			throw new IllegalStateException();
		}
		mState = MyState.RUN;

		for (int i = 0; i < mThreads.length; i++) {
			mThreads[i] = new WorkerThread();
			mThreads[i].start();
		}
	}

	/**
	 * Stop all threads and wait for their terminations.
	 *
	 * @throws IllegalStateException if threads has not been started.
	 */
	public void stop() {
		if (mState != MyState.RUN) {
			throw new IllegalStateException();
		}
		mState = MyState.STOP;

		synchronized (this) {
			notifyAll();
		}

		for (Thread t : mThreads) {
			// wait for thread terminations
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Executes the specified Runnable object, using a thread in the pool.
	 * run() method will be invoked in the thread. If the queue is full, then
	 * this method invocation will be blocked until the queue is not full.
	 * 
	 * @param runnable Runnable object whose run() method will be invoked.
	 *
	 * @throws NullPointerException if runnable is null.
	 * @throws IllegalStateException if this pool has not been started yet.
	 */
	public synchronized void dispatch(Runnable runnable) {
		if (runnable == null) {
			throw new NullPointerException();
		}

		if (mState != MyState.RUN) {
			throw new IllegalStateException();
		}

		while (mQueue.size() > mQueueSize) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		mQueue.add(runnable);
		notifyAll();
	}
}
