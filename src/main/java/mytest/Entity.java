package mytest;

import java.lang.reflect.ParameterizedType;

public abstract class Entity<T> {
	private Class<T> clazz;
	public Entity(){
		ParameterizedType pt = (ParameterizedType)this.getClass().getGenericSuperclass();
		clazz = (Class)pt.getActualTypeArguments()[0];
		System.out.println("clazz = " + clazz);
	}
}
