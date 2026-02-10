package com.currency.converter.graphql;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

import com.currency.converter.core.CurrencyResolver;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import io.micronaut.context.annotation.Bean;
import io.micronaut.core.io.ResourceResolver;
import jakarta.inject.Singleton;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@io.micronaut.context.annotation.Factory
public class Factory {

  private static final Logger LOG = LoggerFactory.getLogger(Factory.class);

  @Bean
  @Singleton
  public GraphQL graphQL(ResourceResolver resourceResolver, CurrencyResolver graphQLDataFetchers) {
    SchemaParser schemaParser = new SchemaParser();

    TypeDefinitionRegistry typeRegistry = new TypeDefinitionRegistry();
    Optional<InputStream> graphqlSchema =
        resourceResolver.getResourceAsStream("classpath:schema.graphqls");

    if (graphqlSchema.isPresent()) {
      typeRegistry.merge(
          schemaParser.parse(new BufferedReader(new InputStreamReader(graphqlSchema.get()))));

      RuntimeWiring runtimeWiring =
          RuntimeWiring.newRuntimeWiring()
              .type(newTypeWiring("Query").dataFetcher("rate", graphQLDataFetchers.getRate()))
              .type(newTypeWiring("Rate").dataFetcher("rate", graphQLDataFetchers.getRate()))
              .build();

      SchemaGenerator schemaGenerator = new SchemaGenerator();
      GraphQLSchema graphQLSchema =
          schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);

      return GraphQL.newGraphQL(graphQLSchema).build();

    } else {
      LOG.debug("No GraphQL services found, returning empty schema");
      return new GraphQL.Builder(GraphQLSchema.newSchema().build()).build();
    }
  }
}
