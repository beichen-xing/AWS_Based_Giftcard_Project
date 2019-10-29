<template>
  <div>
    <div v-for="(card,index) in cards" :key="index">
      <CardItem
        v-bind:card="card"
        v-on:select-card="$emit('select-card', card.id)"
        v-on:del-card="$emit('delCardChild', card.id)"
      />
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