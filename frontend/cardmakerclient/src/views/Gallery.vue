<template>
  <div>
    <h1>This is the exsiting card</h1>
    <div id="menu">
      <GalleryMenu />
    </div>
    <div>
      <Cards v-bind:cards="cards" />
    </div>
  </div>
</template>


<script>
import GalleryMenu from "../components/GalleryMenu";
import Cards from "../components/Cards";

export default {
  name: "Gallery",
  components: {
    GalleryMenu,
    Cards
  },
  data() {
    return {
      cards: [
        {
          id: 1,
          title: "Card 1",
          compelete: false
        },
        {
          id: 2,
          title: "Card 2",
          compelete: false
        },
        {
          id: 1,
          title: "Card 2",
          compelete: false
        }
      ]
    };
  }
};
</script>


<style scoped>
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}
body {
  font-family: Arial, Helvetica, sans-serif;
  line-height: 1.4;
}
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