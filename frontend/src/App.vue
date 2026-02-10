<script setup lang="ts">
import CurrencySelector from '@/components/CurrencySelector.vue'
</script>

<template>
  <header>
    <div class="wrapper">
      <CurrencySelector />
    </div>
  </header>
</template>

<style scoped>
header {
  line-height: 1.5;
}

@media (min-width: 1024px) {
  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }
}
</style>
