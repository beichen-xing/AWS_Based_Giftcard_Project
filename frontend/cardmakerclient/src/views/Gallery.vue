<template>
  <div>
    <h1>This is the exsiting card</h1>
    <div id="menu">
      <GalleryMenu @showInputChild="showInputParent" @deleteCardChild="delCardsParent" />
    </div>

    <div>
      <div id="card_div" v-if="showCards">
        <Cards
          v-bind:cards="cards"
          v-on:del-card="delCardsParent"
          v-on:select-card-cards="selectCardGallery"
        />
      </div>
      <div id="input_div" v-if="showInputs">
        <CardInfo @submitChild="submitParent" @cancelChild="cancelParent" />
      </div>
    </div>
  </div>
</template>


<script>
import uuid from "uuid";
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
    //console.log("Get all cards");
    axios
      .get("https://jsonplaceholder.typicode.com/todos?_limit=5")
      .then(res => (this.cards = res.data))
      .catch(err => console.log(err));
    //this.$options.methods.getCardsRequeast();
  },
  methods: {
    addCardRequest(NewCard) {
      axios
        .post("https://jsonplaceholder.typicode.com/todos", {
          id: uuid.v4(),
          title: NewCard.title,
          cardName: NewCard.cardName,
          recipient: NewCard.recipient,
          eventType: NewCard.eventType,
          orientation: NewCard.orientation
        })
        .then(res => (this.todos = [...this.todos, res.data]))
        .catch(err => console.log(err));
    },
    delCardRequest(id) {
      console.log("triggered!");
      axios
        .post(`https://jsonplaceholder.typicode.com/todos/${id}`)
        //.then(res => (this.cards = this.cards.filter(card => card.id != id)))
        .catch(err => console.log(err));
    },
    getCardsRequeast() {
      console.log("get alll cards");
      axios
        .get("https://jsonplaceholder.typicode.com/todos?_limit=5")
        .then(res => (this.cards = res.data))
        .catch(err => console.log(err));
    },
    showInputParent: function(msg) {
      console.log(msg);
      this.showCards = msg.msg1;
      this.showInputs = msg.msg2;
      //console.log(this.showCards);
    },
    submitParent: function(msg) {
      // console.log("Getting info");
      // console.log(msg);
      this.showCards = msg.msg1;
      this.showInputs = msg.msg2;
      const NewCard = {
        title: msg.title,
        cardName: msg.cardName,
        recipient: msg.recipient,
        eventType: msg.eventType,
        orientation: msg.orientation
      };
      this.$options.methods.addCardRequest(NewCard);
      //console.log("cards created!");
    },
    cancelParent: function(msg) {
      console.log(msg);
      this.showCards = msg.msg1;
      this.showInputs = msg.msg2;
      //console.log(this.showCards);
    },
    selectCardGallery(id) {
      console.log(id);
      //$emit("SelectCardIDGallery", id);
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