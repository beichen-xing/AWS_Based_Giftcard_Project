<template>
  <div class="card-item">
    <p>
      <input type="checkbox" v-on:change="select_card($event)" />
      CardID: {{card.id}} &nbsp;
      Card Name: {{card.name}} &nbsp;
      Recipient: {{card.recipient}} &nbsp;
      Event Type: {{card.type}} &nbsp;
      orientation: {{card.oritentation}} &nbsp;
    </p>
  </div>
</template>


<script>
//import { METHODS } from "http";
export default {
  name: "CardItem",
  props: ["card"],
  methods: {
    select_card(event) {
      if (event.target.checked) {
        //console.log("Selected!");
        this.$emit("select-card-item", { card: this.card.id, selected: true });
      } else {
        //console.log("DeSelected!");
        this.$emit("select-card-item", { card: this.card.id, selected: false });
      }
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