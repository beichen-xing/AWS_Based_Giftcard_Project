<template>
  <div class="card-item">
    <p>
      <input type="checkbox" @click="$emit('select-card-item', card.id)" />
      {{card.title}}
      <!-- <button @click="$emit('del-card', card.id)">Edit</button> -->
      <!-- <button @click="$emit('del-card-item', card.id)" class="del">x</button> -->
    </p>
  </div>
</template>


<script>
//import { METHODS } from "http";
export default {
  name: "CardItem",
  props: ["card"],
  methods: {
    select_item() {
      this.card.id = !this.card.id;
    },
    markComplete() {
      this.card.completed = !this.card.completed;
    }
  }
};
</script>>


<style scoped>
.card-item {
  background: #f4f4f4;
  padding: 10px;
  border-bottom: 1px #ccc dotted;
}

.is-selected {
  font-weight: bold;
}

.is-complete {
  text-decoration: line-through;
}
.del {
  background: #ff0000;
  color: #fff;
  border: none;
  padding: 5px 9px;
  border-radius: 50%;
  cursor: pointer;
  float: right;
}
</style>