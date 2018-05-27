package musify.collection.services.common.services;

public interface CrudService<K, T> extends QueryService<K, T> {

	T add(T data);

	T update(T data);

	void delete(K id);
}
