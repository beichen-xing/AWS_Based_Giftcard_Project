<template>
  <div>
    <el-row class="action-btn-list">
      <el-button-group>
        <el-button @click="showInputComponent()">Add Card</el-button>
        <el-button @click="deleteCard()">Delete Card</el-button>
        <el-button @click="editCardMenu()">Edit Card</el-button>
        <el-button>Duplicate Card</el-button>
        <el-button>Display Card</el-button>
        <el-button>Generate Link</el-button>
      </el-button-group>
    </el-row>
    <!-- <button @click="showInputComponent()" class="btn">Add Card</button> -->
    <!-- <input type="submit" value="Add Card" class="btn" /> -->
    <!-- <button @click="deleteCard()" class="btn">Delete Card</button>
    <button @click="editCardMenu()" class="btn">Edit Card</button>
    <input type="submit" value="Duplicate Card" class="btn" />
    <input type="submit" value="Display Card" class="btn" />
    <input type="submit" value="Generate Link" class="btn" />-->
  </div>
</template>


<script >
export default {
  name: "GalleryMenu",
  methods: {
    showInputComponent: function() {
      this.$emit("showInputChild", { msg1: false, msg2: true });
    },
    editCardMenu: function() {
      this.$emit("editCardMenu", { msg1: false, msg2: true, edit: true });
    },
    deleteCard() {
      this.$emit("deleteButtonChild", { del: true });
    }
  }
};
</script>

<style scoped>
.action-btn-list {
  margin-top: 2vh;
}
</style>