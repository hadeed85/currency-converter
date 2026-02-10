import { ApolloClient, InMemoryCache, HttpLink } from '@apollo/client'

const backendApi = import.meta.env.BACKEND_API || 'http://localhost:8080/graphql'

export const apolloClient = new ApolloClient({
  link: new HttpLink({
    uri: backendApi,
  }),
  cache: new InMemoryCache(),
})
