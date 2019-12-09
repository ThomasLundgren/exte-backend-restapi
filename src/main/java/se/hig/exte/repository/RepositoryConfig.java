package se.hig.exte.repository;

import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config);
		config.exposeIdsFor(entityManager.getMetamodel().getEntities().stream()
				.map(e -> e.getJavaType()).collect(Collectors.toList()).toArray(new Class[0]));
		config.setReturnBodyOnCreate(true);
		config.setReturnBodyOnUpdate(true);
	}
	
}
