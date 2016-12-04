package copyjava;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.concurrent.ConcurrentMap;

public class WConcurrentHashMap<K,V> extends AbstractMap<K,V> implements ConcurrentMap<K,V>, Serializable {

	private static final int MAXINUM_CAPACITY = (1 << 30);

	private static final int DEFAULT_CAPACITY = 16;

	/* non power of two */
	static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

	private static final int DEFAULT_CONCURRENCY_LEVEL = 16;

	private static final float LOAD_FACTOR = 0.75F;

	static final int TREEIFY_THRESHOLD = 8;

	static final int UNTREEIFY_THRESHOLD = 6;

	static final int MIN_TREEIFY_CAPACITY = 64;

	private static final int MIN_TRANSFER_STRIDE = 16;

	private static int RESIZE_STAMP_BITS = 16;

	private static final int MAX_RESIZERS = (1 << (32 - RESIZE_STAMP_BITS)) - 1;

	private static final int RESIZE_STAMP_SHIFT = (32 - RESIZE_STAMP_BITS);

	static final int MOVED = -1;
	static final int TREEBIN = -2;
	static final int RESERVED = -3;
	static final int HASH_BITS = 0x7fffffff;

	static final int NCPU = Runtime.getRuntime().availableProcessors();

	private static final ObjectStreamField[] serialPersistentFields = {
		new ObjectStreamField("segments", Segment[].class),
		new ObjectStreamField("segmentMask", Integer.TYPE),
		new ObjectStreamField("SegmentShift", Integer.TYPE)
	};

	/* ---------------- Nodes ------------- */
	static class Node<K,V> implements Map.Entry<K,V> {
		final int hash;
		final K key;
		volatile V val;
		volatile Node<K,V> next;

		Node(int hash, K key, V val, Node<K,V> next){
			this.hash = hash;
			this.key = key;
			this.val = val;
			this.next = next;
		}

		public final K getKey(){
			return key;
		}

		public final V getValue(){
			return val;
		}

		public final int hashCode(){
			return key.hashCode ^ val.hashCode();
		}

		public final String toString(){
			return key + "=" +val;
		}

		public final V setValue(V value){
			throw UnsupportedOperationException();
		}

		public final boolean equals(Object o){
			Object k, v, u;
			Map.Entry<?,?> e;
			return ( (o instanceof Map.Entry) && (k = (e=(Map.Entry<?,?>)o).getKey()) != null && (v = e.getValue()) != null
			      && (k == key || k.equals(key)) && (v == (u = val) || v.equals(u)));
		}

		Node<K,V> find(int h, Object k){
			Node<K,V> e = this;
			if(k != null){
				do {
					K ek;
					if(e.hash == h && ((ek = e.key) == k || (ek != null && k.equals(ek)))){
						return e;
					}
				}while((e = e.next) != null);
			}
			return null;
		}
	


	}

	static final int spread(int h){
		reutrn (h ^ (h >>> 16)) & HASH_BITS;
	}

	/* 
	 * Returns a poer of two table size for the given desired capacity. 
	 * n对应的二进制位有w位数，先将 w-1 置1，然后 n+1 将 w+1 位置1，其余位置0
	 */
	private static final int tableSizeFor(int c){
		int n = c - 1;
		n |= n >>> 1;
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;
		n |= n >>> 16;
		return (n < 0) ? 1 : (n >= MAXINUM_CAPACITY) ? MAXINUM_CAPACITY : n + 1;
	}

	static Class<?> comparableClassFor(Object x){
		if (x instanceof Comparable){
			Class<?> c;
			Type[] ts, as;
			Type t;
			ParameterizedType p;
			if ((c = x.getClass()) == String.class){
				return c;
			}
			if((ts = c.getGenericInterfaces()) != null){
				for (int i = 0; i < ts.length; ++i){
					if( ( ( t = ts[i] ) instanceof ParameterizedType ) && ((p=(ParameterizedType)t).getRawType() == Comparable.class) &&
					 (as = p.getActualTypeArguments()) != null && as.length == 1 && as[0] == c ){
						return c;
					}
				}
			}
		}
		return null;
	}

	static class Segment<K,V> extends ReentrantLock implements Serializable{
		final float loadFactor;
		Segment(float lf){
			this.loadFactor = lf;
		}
	}











}
