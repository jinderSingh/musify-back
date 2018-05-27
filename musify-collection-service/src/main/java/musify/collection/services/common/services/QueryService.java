package musify.collection.services.common.services;

import java.util.List;

public interface QueryService<K, T> {
	T findOne(K id);

	List<T> findAll();
}
