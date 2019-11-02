<template>
  <div>
    <h1>This is the exsiting card</h1>
    <div id="menu">
      <GalleryMenu @showInputChild="showInputParent" @deleteButtonChild="deleteButtonParent" />
    </div>

    <div>
      <div id="card_div" v-if="showCards">
        <Cards
          v-bind:cards="cards"
          v-on:delCardChild="delCardsParent"
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
      cards: [],
      selectedCard: ""
    };
  },
  created() {
    this.getCardsRequeast();
  },
  methods: {
    addCardRequest(NewCard) {
      //onsole.log("add card");
      // console.log(NewCard);
      axios
        .post("https://jsonplaceholder.typicode.com/todos", {
          id: NewCard.id,
          title: NewCard.title,
          cardName: NewCard.cardName,
          recipient: NewCard.recipient,
          eventType: NewCard.eventType,
          orientation: NewCard.orientation
        })
        .then(res => (this.cards = [...this.cards, res.data]))
        .catch(err => console.log(err));
    },
    delCardRequest(id) {
      //console.log("deleted!");
      axios
        .post(`https://jsonplaceholder.typicode.com/todos${id}`)
        .then(res => (this.cards = [...this.cards, res.data]))
        .catch(err => console.log(err));
    },
    getCardRequest(id) {
      console.log("triggered!");
      axios
        .post(`https://jsonplaceholder.typicode.com/todos${id}`)
        .then(res => (this.cards = [...this.cards, res.data]))
        .catch(err => console.log(err));
    },
    getCardsRequeast() {
      //console.log("get alll cards");
      axios
        .get("https://jsonplaceholder.typicode.com/todos?_limit=5")
        .then(res => (this.cards = res.data))
        .catch(err => console.log(err));
    },
    showInputParent: function(msg) {
      console.log(msg);
      this.showCards = msg.msg1;
      this.showInputs = msg.msg2;
    },
    submitParent: function(msg) {
      this.showCards = msg.msg1;
      this.showInputs = msg.msg2;
      const NewCard = {
        id: msg.cardID,
        cardName: msg.cardName,
        recipient: msg.recipientName,
        eventType: msg.eventType,
        orientation: msg.orientation
      };
      //console.log(NewCard);
      this.addCardRequest(NewCard);
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
      this.selectedCard = id;
      //$emit("SelectCardIDGallery", id);
    },
    delCardsParent() {},
    deleteButtonParent(msg) {
      if (msg.del == true) {
        this.delCardRequest(this.id);
      }
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