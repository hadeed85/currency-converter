import type { CombinedGraphQLErrors } from '@apollo/client/errors'

export function getGenericErrorMessage(error: CombinedGraphQLErrors): string {
  error.errors.forEach((graphQLError) => {
    console.log(graphQLError.message)
    console.log(graphQLError.path)
    console.log(graphQLError.locations)
  })

  if (error.errors.length > 0) {
    return 'Something went wrong. Please try again.'
  }

  return 'An unexpected error occurred.'
}
