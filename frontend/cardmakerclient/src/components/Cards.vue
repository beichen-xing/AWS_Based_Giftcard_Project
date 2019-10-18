<template>
  <div>
    <div v-for="card in cards" :key="card.id">
      <CardItem v-bind:card="card" v-on:del-card="$emit('del-card', card.id)" />
    </div>
  </div>
</template>

<script>
import CardItem from "./CardItem.vue";

export default {
  name: "Cards",
  components: {
    CardItem
  },
  props: ["cards"]
};
</script>

<style scoped>
</style>