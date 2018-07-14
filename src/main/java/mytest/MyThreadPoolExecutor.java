package mytest;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPoolExecutor {
	ThreadPoolExecutor pool = new ThreadPoolExecutor(4, 5, 1000, TimeUnit.SECONDS, new BlockingQueue<Runnable>() {
		
		@Override
		public <T> T[] toArray(T[] a) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Object[] toArray() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public int size() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public boolean retainAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean removeAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public Iterator<Runnable> iterator() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public boolean isEmpty() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean containsAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public boolean addAll(Collection<? extends Runnable> c) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public Runnable remove() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Runnable poll() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Runnable peek() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Runnable element() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Runnable take() throws InterruptedException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public boolean remove(Object o) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public int remainingCapacity() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public void put(Runnable e) throws InterruptedException {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public Runnable poll(long timeout, TimeUnit unit)
				throws InterruptedException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public boolean offer(Runnable e, long timeout, TimeUnit unit)
				throws InterruptedException {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean offer(Runnable e) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public int drainTo(Collection<? super Runnable> c, int maxElements) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public int drainTo(Collection<? super Runnable> c) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public boolean contains(Object o) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean add(Runnable e) {
			// TODO Auto-generated method stub
			return false;
		}
	});
	
}
