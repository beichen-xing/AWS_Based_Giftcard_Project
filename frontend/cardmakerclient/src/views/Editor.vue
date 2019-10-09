<template>
  <div>
    <h1>Card Editor</h1>
    <div id="menu">
      <Menu />
    </div>
    <div>this is the board</div>
  </div>
</template>

<script>
import Menu from "../components/EditorMenu";
export default {
  name: "Editor",
  components: {
    Menu
  }
};
</script>

