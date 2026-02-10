import { gql } from '@apollo/client/core'

export const QUERY_RATE = gql`
  query QueryRate($source: Iso4217Currency!, $target: Iso4217Currency!, $amount: Int!) {
    rate(sourceCurrency: $source, targetCurrency: $target, monetaryValue: $amount) {
      monetaryValue
      sourceCurrency
      targetCurrency
    }
  }
`
