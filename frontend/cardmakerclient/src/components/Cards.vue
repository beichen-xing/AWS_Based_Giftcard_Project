<template>
  <div>
    <div v-for="(card,index) in cards" :key="index">
      <CardItem
        v-bind:card="card"
        v-on:select-card-item="$emit('select-card-cards', card.id)"
        v-on:del-card-item="$emit('delCardChild', card.id)"
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