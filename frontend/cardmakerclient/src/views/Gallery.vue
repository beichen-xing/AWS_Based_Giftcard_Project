<template>
  <div>
    <h1>This is the exsiting card</h1>
    <div id="menu">
      <GalleryMenu @showInputParent="showInputChild" />
    </div>

    <div>
      <div id="card_div" v-if="showCards">
        <Cards v-bind:cards="cards" />
      </div>
      <div id="input_div" v-if="showInputs">
        <CardInfo />
      </div>
    </div>
  </div>
</template>


<script>
import GalleryMenu from "../components/GalleryMenu";
import Cards from "../components/Cards";
import CardInfo from "../components/CardInfo";

export default {
  name: "Gallery",
  components: {
    GalleryMenu,
    Cards,
    CardInfo
  },
  data() {
    return {
      showCards: true,
      showInputs: false,
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
  },
  methods: {
    showInputParent: function(msg) {
      console.log("come in!")
      this.showCards = msg[0];
      this.showInputs = msg[1];
      //console.log(this.showCards);
    }
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