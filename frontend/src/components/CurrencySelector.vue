<template>
  <div class="selector-container">
    <h2>Enter to calculate monetary value</h2>
    <div class="inputs">
      <div class="input-group">
        <label for="source">Source currency:</label>
        <select v-model="source">
          <option v-for="option in enumOptions" :key="option" :value="option">
            {{ option }}
          </option>
        </select>
      </div>
      <div class="input-group">
        <label for="target">Target currency:</label>
        <select v-model="target">
          <option v-for="option in enumOptions" :key="option" :value="option">
            {{ option }}
          </option>
        </select>
      </div>
      <div class="input-group">
        <label for="amount">Monetary value:</label>
        <input id="amount" v-model="amount" placeholder="Enter monetary value" />
      </div>
    </div>
    <button class="fetch-btn" @click="getRates">Calculate</button>
    <div class="results">
      <div v-if="loading" class="status">Loading...</div>
      <div v-else-if="error" class="status error">{{ error }}</div>
      <ul v-if="rates">
        <li v-for="(value, key) in rates" :key="key">
          <strong>{{ key }}:</strong> {{ value }}
        </li>
      </ul>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import { apolloClient } from '../apollo'
import { QUERY_RATE } from '../graphql/queryRate'
import { CombinedGraphQLErrors } from '@apollo/client'

interface Rate {
  targetCurrency: string
  sourceCurrency: string
  monetaryValue: string
}

interface GetRateResponse {
  rate: Rate
}

import { Iso4217Currency } from '@/generated/graphql'
import { getGenericErrorMessage } from '@/utils/graphqlErrorHandler.ts'

const enumOptions = Object.values(Iso4217Currency)

const source = ref<Iso4217Currency>(Iso4217Currency.Eur)
const target = ref<Iso4217Currency>(Iso4217Currency.Gbp)
const amount = ref<number>(0)

const rates = ref<Rate>()

const loading = ref(false)
const error = ref<string | null>(null)

async function getRates() {
  loading.value = true
  error.value = null

  try {
    const result = await apolloClient.query<GetRateResponse>({
      query: QUERY_RATE,
      variables: {
        source: source.value,
        target: target.value,
        amount: Number(amount?.value),
      },
    })
    if (result.data?.rate) {
      rates.value = result.data.rate
    }
  } catch (err) {
    error.value = getGenericErrorMessage(err as CombinedGraphQLErrors)
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
.selector-container {
  max-width: 450px;
  margin: 0 auto;
  padding: 30px;
  background: #f9f9f9;
  border-radius: 12px;
  box-shadow: 0 8px 25px rgba(0, 0, 0, 0.1);
  font-family: 'Helvetica Neue', Arial, sans-serif;
}

h2 {
  text-align: center;
  margin-bottom: 20px;
  color: #333;
}

label {
  margin-bottom: 5px;
  font-weight: 500;
  color: #555;
}

.inputs {
  display: flex;
  flex-direction: column;
  gap: 20px;
  margin-bottom: 30px;
  max-width: 500px;
  width: 100%;
  font-family: 'Segoe UI', Tahoma, sans-serif;
}

.input-group {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.input-group > label {
  font-weight: 600;
  color: #333;
  font-size: 14px;
}

.input-group > input,
.input-group > select,
.input-group > textarea {
  padding: 10px 14px;
  font-size: 16px;
  border: 1px solid #ccc;
  border-radius: 8px;
  width: 100%;
  box-sizing: border-box;
  background-color: #fff;
  transition: all 0.2s ease;
  appearance: none;
}

.input-group > input:focus,
.input-group > select:focus,
.input-group > textarea:focus {
  outline: none;
  border-color: #6c63ff;
  box-shadow: 0 0 8px rgba(108, 99, 255, 0.2);
}

.input-group > select {
  background-image: url("data:image/svg+xml,%3Csvg width='12' height='12' viewBox='0 0 24 24' fill='none' stroke='%236c63ff' stroke-width='2' stroke-linecap='round' stroke-linejoin='round'%3E%3Cpolyline points='6 9 12 15 18 9'%3E%3C/polyline%3E%3C/svg%3E");
  background-repeat: no-repeat;
  background-position: right 10px center;
  background-size: 12px;
  padding-right: 35px;
  cursor: pointer;
}

.input-group > textarea {
  min-height: 80px;
  resize: vertical;
}

.fetch-btn {
  width: 100%;
  padding: 12px 0;
  font-size: 16px;
  color: #fff;
  background-color: #42b983;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.2s;
}

.fetch-btn:hover {
  background-color: #369f6f;
}

.results {
  margin-top: 20px;
}

.results ul {
  list-style-type: disc;
  padding-left: 20px;
}

.status {
  text-align: center;
  font-weight: 500;
}

.status.error {
  color: #d9534f;
}
</style>
