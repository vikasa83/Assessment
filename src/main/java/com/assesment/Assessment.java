package com.assesment;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.EnumSet;
import java.util.UUID;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.api.TypeLiteral;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.servlet.ServletProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.assesment.bo.TransactionBO;
import com.assesment.bo.TransactionBOImpl;
import com.assesment.dao.TransactionDao;
import com.assesment.dao.TransactionDaoImpl;
import com.assesment.resource.TransactionResource;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;

import io.dropwizard.Application;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.SwaggerSerializers;

public class Assessment extends Application<AssessmentConfiguration> {

	private static final Logger LOGGER = LoggerFactory.getLogger(Assessment.class);

	public static void main(String[] args) throws Exception {
		new Assessment().run(args);

	}

	@Override
	public void run(AssessmentConfiguration configuration, Environment environment) throws Exception {
		ServiceLocator serviceLocator = ServiceLocatorUtilities
				.createAndPopulateServiceLocator(UUID.randomUUID().toString());

		LOGGER.info("Configuring dependencies...");
		configureDependencies(serviceLocator, configuration);

		LOGGER.info("Configuring service locator for the jersey environment");
		environment.getApplicationContext().getAttributes().setAttribute(ServletProperties.SERVICE_LOCATOR,
				serviceLocator);

		LOGGER.info("Registering REST resources");
		registerResources(environment);

		LOGGER.info("Configuring Swagger Docs");
		configureSwagger(environment);

	}

	@Override
	public void initialize(Bootstrap<AssessmentConfiguration> b) {
	}

	private void registerResources(Environment environment) {
		environment.jersey().register(TransactionResource.class);
	}

	private void configureSwagger(Environment environment) throws UnknownHostException, SocketException {

		environment.jersey().register(io.swagger.jaxrs.listing.ApiListingResource.class);
		environment.jersey().register(io.swagger.jaxrs.listing.SwaggerSerializers.class);

		BeanConfig swaggerConfig = new BeanConfig();
		swaggerConfig.setTitle("Transaction API");
		swaggerConfig.setVersion("1.0");
		swaggerConfig.setBasePath("/");
		swaggerConfig.setResourcePackage("com.assesment.resource");
		swaggerConfig.setScan(true);
		environment.servlets().addFilter("CrossOriginFilter", CrossOriginFilter.class)
				.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
	}

	private void configureDependencies(ServiceLocator serviceLocator, final AssessmentConfiguration configuration)
			throws Exception {
		final ObjectMapper objectMapper = Jackson.newObjectMapper();
		objectMapper.registerModule(new AfterburnerModule());
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		//final CouchbaseCluster cluster = CouchbaseCluster.create(configuration.getCouchbaseNodes());
		
		//final Bucket bucket = cluster.openBucket(configuration.getCouchbaseBucket());

		ServiceLocatorUtilities.bind(serviceLocator, new AbstractBinder() {
			@Override
			protected void configure() {
				bind(configuration).named("configuration");
				bind(objectMapper).named("jsonmapper");

			//	bind(bucket).named("bucket");
				
				bind(TransactionDaoImpl.class).named("transactionDao").to(new TypeLiteral<TransactionDao>() {
				});

				bind(TransactionBOImpl.class).named("transactionBO").to(new TypeLiteral<TransactionBO>() {
				});

				bind(SwaggerSerializers.class);

			}
		});
	}

}
