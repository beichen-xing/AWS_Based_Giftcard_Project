<template>
  <div>
    <h1>This is the exsiting card</h1>
    <div id="menu">
      <GalleryMenu @showInputChild="showInputParent" />
    </div>

    <div>
      <div id="card_div" v-if="showCards">
        <Cards v-bind:cards="cards" v-on:delCardsChild="delCardsParent" />
      </div>
      <div id="input_div" v-if="showInputs">
        <CardInfo @submitChild="submitParent" @cancelChild="cancelParent" />
      </div>
    </div>
  </div>
</template>


<script>
import axios from "axios";
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
      cards: []
    };
  },
  created() {
    console.log("Get all cards");
    axios
      .get("https://jsonplaceholder.typicode.com/todos?_limit=5")
      .then(res => (this.cards = res.data))
      .catch(err => console.log(err));
  },
  methods: {
    showInputParent: function(msg) {
      console.log(msg);
      this.showCards = msg.msg1;
      this.showInputs = msg.msg2;
      //console.log(this.showCards);
    },
    submitParent: function(msg) {
      console.log(msg);
      this.showCards = msg.msg1;
      this.showInputs = msg.msg2;
      //console.log(this.showCards);
      //const cardName = msg.cardName;
      //const recipient = msg.recipient;
      //const eventType = msg.eventType;
      const title = msg.title;
      const test = msg.test;
      axios
        .post("https://jsonplaceholder.typicode.com/todos", {
          title,
          test
          //cardName
          //recipient
          //eventType
        })
        .then(res => (this.todos = [...this.todos, res.data]));
      //.catch(err => console.log(err));
      console.log("cards created!");
    },
    cancelParent: function(msg) {
      console.log(msg);
      this.showCards = msg.msg1;
      this.showInputs = msg.msg2;
      //console.log(this.showCards);
    },
    delCardsParent(id) {
      axios
        .delete(`https://jsonplaceholder.typicode.com/todos/${id}`)
        .then(res => (this.cards = this.cards.filter(card => card.id != id)))
        .catch(err => console.log(err));
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