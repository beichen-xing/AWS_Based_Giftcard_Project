<template>
  <div>
    <!-- <form action> -->
    <button @click="showInputComponent()" class="btn">Add Card</button>
    <!-- <input type="submit" value="Add Card" class="btn" /> -->
    <button @click="deleteCard()" class="btn">Delete Card</button>
    <button @click="showInputComponent()" class="btn">Edit Card</button>
    <input type="submit" value="Duplicate Card" class="btn" />
    <input type="submit" value="Display Card" class="btn" />
    <input type="submit" value="Generate Link" class="btn" />
    <!-- </form> -->
  </div>
</template>


<script >
export default {
  name: "GalleryMenu",
  methods: {
    showInputComponent: function() {
      this.$emit("showInputChild", { msg1: false, msg2: true });
    },
    deleteCard() {
      this.$emit("deleteCardChild", { del: true });
    }
  }
};
</script>

<style scoped>
.btn {
  display: inline-block;
  border: none;
  background: #555;
  color: #fff;
  padding: 7px 20px;
  cursor: pointer;
}
.btn:hover {
  background: #666;
}
</style>