<template>
  <div>
    <div v-for="(card,index) in cards" :key="index">
      <CardItem v-bind:card="card" v-on:select-card-item="selectCardCards" />
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
  props: ["cards"],
  methods: {
    selectCardCards(msg) {
      //console.log(msg);
      this.$emit("select-card-cards", { id: msg.card, selected: msg.selected });
    }
  }
};
</script>

<style scoped>
</style>