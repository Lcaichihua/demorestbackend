package pe.com.caichihua.backrest.demorestbackend.service.base;

import pe.com.caichihua.backrest.demorestbackend.service.exceptions.ServiceException;

import java.util.List;

public interface GenericService<T> { // T, K, V...

	T findById(Long id) throws ServiceException;
	
	List<T> findByLikeObject(T t) throws ServiceException;
	
	T save(T t) throws ServiceException;
	
	T update(T t) throws ServiceException;
	
	void delete(Long id) throws ServiceException;
		
}
