<template>
  <div>
    <h1>Card Editor</h1>
    <div id="menu">
      <Menu />
    </div>
    <div>this is the board</div>
    <div>
      <EditorBoard />
    </div>
  </div>
</template>

<script>
import Menu from "../components/EditorMenu";
import EditorBoard from "../components/EditorBoard";
export default {
  name: "Editor",
  components: {
    Menu,
    EditorBoard
  }
};
</script>

