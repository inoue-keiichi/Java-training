package ch17.ex05;

import java.io.File;
import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class ResourceManager {

	public final ReferenceQueue<Object> queue;
	final Map<Reference<?>, Resource> refs;
	// final Thread reaper;
	boolean shutdown = false;

	public ResourceManager() {
		queue = new ReferenceQueue<Object>();
		refs = new HashMap<Reference<?>, Resource>();
		// reaper = new ReaperThread();
		// reaper.start();

		// ... リソースの初期化 ...
		
	}

	public synchronized void shutdown() {
		if (!shutdown) {
			shutdown = true;
			// reaper.interrupt();
		}
	}

	public synchronized Resource getResource(Object key) {
		if (shutdown)
			throw new IllegalStateException();
		Resource res = new ResourceImpl(key);
		Reference<?> ref = new PhantomReference<Object>(key, queue);
		refs.put(ref, res);
		return res;
	}

	private static class ResourceImpl implements Resource {
		Set<Object> keySet = new HashSet<>();
		boolean needsRelease = false;

		public ResourceImpl(Object key) {
			this.keySet.add(key);

			// .. 外部リソースの設定

			needsRelease = true;
		}

		@Override
		public void use(Object key, Object... args) {
			if (!keySet.contains(key))
				throw new IllegalArgumentException("wrong key");

			// ... リソースの使用 ...
		}

		@Override
		public void release() {
			if (needsRelease) {
				needsRelease = false;

				// .. リソースの解放 ...
			}
		}
	}

//	public class ReaperThread extends Thread {
//		public void run() {
//			// 割り込まれるまで実行
//			while (true) {
//				try {
//					Reference<?> ref = queue.remove();
//					Resource res = null;
//					synchronized (ResourceManager.this) {
//						res = refs.get(ref);
//						refs.remove(ref);
//					}
//					res.release();
//					ref.clear();
//				} catch (InterruptedException e) {
//					while (true) {
//						Reference<?> ref = queue.poll();
//						Resource res = null;
//						if (ref == null) {
//							break; // 全て終了
//						}
//						synchronized (ResourceManager.this) {
//							res = refs.get(ref);
//							refs.remove(ref);
//						}
//						res.release();
//						ref.clear();
//					}
//				}
//			}
//		}
//	}
}
