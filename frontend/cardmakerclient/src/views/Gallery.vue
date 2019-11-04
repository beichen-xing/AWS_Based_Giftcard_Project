<template>
  <div>
    <h1>This is the exsiting card</h1>
    <div id="menu">
      <GalleryMenu
        v-bind:GetCardInfoMenu="GetCardInfoGallery"
        @showInputChild="showInputParent"
        @deleteButtonChild="deleteButtonParent"
        @editCardMenu="editCardGallery"
      />
    </div>

    <div>
      <div id="card_div" v-if="showCards">
        <Cards
          v-bind:cards="cards"
          v-bind:isSelectedCards="isSelectedGallery"
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
  //inject: ["reload"],
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
      isSelectedGallery: [],
      selectedCard: "",
      GetCardInfoGallery: {
        cardName: "",
        eventType: "",
        recipien: "",
        orientation: ""
      },
      selectedCards: [],
      isDelete: false
    };
  },
  created() {
    this.getCardsRequeast();
  },
  methods: {
    addCardRequest(NewCard) {
      //onsole.log("add card");
      console.log(NewCard);
      axios
        .post(
          "https://smrii41wj7.execute-api.us-east-2.amazonaws.com/beta/addCard",
          {
            headers: {
              "Postman-Token": "ec539028-18ec-4376-8357-423b2d8d5c01",
              "cache-control": "no-cache",
              "Access-Control-Allow-Origin": "*",
              "Content-Type": "application/json"
            }
          },
          {
            data: {
              id: NewCard.id,
              //title: NewCard.title,
              name: NewCard.cardName,
              recipient: NewCard.recipient,
              type: NewCard.eventType,
              oritentation: NewCard.orientation
            }
          }
        )
        .then(res => this.getCardsRequeast())
        .catch(err => console.log(err));
    },
    delCardRequest(id) {
      //console.log("deleted!");
      console.log(id);
      axios
        .post(
          `https://smrii41wj7.execute-api.us-east-2.amazonaws.com/beta/delCard`,
          {
            headers: {
              "Postman-Token": "ec539028-18ec-4376-8357-423b2d8d5c01",
              "cache-control": "no-cache",
              "Access-Control-Allow-Origin": "*",
              "Content-Type": "application/json"
            }
          },
          {
            data: {
              id: id
            }
          }
        )
        .then(res => this.getCardsRequeast())
        .catch(err => console.log(err));
      this.getCardsRequeast();
    },

    getCardRequest(id) {
      //console.log("triggered!");
      console.log(id);
      axios
        .post(
          "https://smrii41wj7.execute-api.us-east-2.amazonaws.com/beta/getCard${id}"
        )
        .then(
          res =>
            (this.GetCardInfoGallery = [...this.GetCardInfoGallery, res.data])
        )
        .catch(err => console.log(err));
    },

    getCardsRequeast() {
      axios
        .get(
          "https://smrii41wj7.execute-api.us-east-2.amazonaws.com/beta/listCard"
        )
        .then(res => (this.cards = res.data.list))
        .catch(err => console.log(err));
      //console.log(this.cards);
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
      //this.$router.go(0);
      //this.getCardsRequeast();
    },
    cancelParent: function(msg) {
      console.log(msg);
      this.showCards = msg.msg1;
      this.showInputs = msg.msg2;
      //console.log(this.showCards);
      //this.$router.go(0);
    },
    selectCardGallery(msg) {
      //console.log(msg);
      if (msg.selected == true) {
        console.log("selcted triggered!");
        if (this.selectedCards.indexOf(msg.id) == -1) {
          this.selectedCards.push(msg.id);
          this.selectedCard = msg.id;
        }
      }
      if (msg.selected == false) {
        console.log("deselcted triggered!");
        if (this.selectedCards.indexOf(msg.id) != -1) {
          this.selectedCards.pop(msg.id);
          this.selected = "";
        }
      }
      //console.log(this.selectedCards);
      //$emit("SelectCardIDGallery", id);
    },
    delCardsParent() {},
    deleteButtonParent(msg) {
      this.isDelete = msg.del;
      //console.log(this.selectedCards);
      if (this.isDelete == true) {
        for (var i = 0; i < this.selectedCards.length; i++) {
          this.delCardRequest(this.selectedCards[i]);
          console.log("Deleted");
        }
        this.isDelete = false;
      }
    },
    editCardGallery(msg) {
      if (msg.edit == true) {
        this.showCards = msg.msg1;
        this.showInputs = msg.msg2;
        this.getCardRequest(this.id);
        //console.log(this.id);
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