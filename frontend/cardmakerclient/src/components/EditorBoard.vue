<template>
  <div class="board" width="960" height="600">
    <ImageBoard />
    <TextBoard />
  </div>
</template>


<script>
import ImageBoard from "../components/ImageBoard";
import TextBoard from "../components/TextBoard";
export default {
  name: "EditorBoard",
  components: {
    TextBoard,
    ImageBoard
  }
};
</script>

<style scoped>
board {
  border: 1px solid #333;
}
</style>