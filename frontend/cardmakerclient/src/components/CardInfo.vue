<template>
  <div>
    <p>----------------------------------------------------------------------------------------------------------------------</p>
    <p />
    <!-- input : trim automate remove spaces -->
    <label>Card Name: &ensp;</label>
    <input v-model.trim="cardName" placeholder="Enter text..." />
    The Card name is : {{ cardName }}
    <p></p>
    <p />
    <label>Recipient: &ensp;</label>
    <input v-model.trim="recipientName" placeholder="Enter text..." />
    Recipient : {{ recipientName }}
    <p>----------------------------------------------------------------------------------------------------------------------</p>
    <div id="eventType">
      <select v-model="eventType">
        <option value>---Event Type---</option>
        <option value="Birthday">Birthday</option>
        <option value="Christmas">Christmas</option>
        <option value="Halloween">Halloween</option>
      </select>
      <br />
      <br />
      <div id="output">The event type selected: {{eventType}}</div>
    </div>
    <p>----------------------------------------------------------------------------------------------------------------------</p>
    <div id="orientation">
      <select v-model="orientation">
        <option value>---Orientation---</option>
        <option value="Landscape">Landscape</option>
        <option value="Portrait">Portrait</option>
      </select>
      <br />
      <br />
      <div id="output">The orientation selected: {{orientation}}</div>
    </div>
    <p>----------------------------------------------------------------------------------------------------------------------</p>
    <p />
    <button @click="Submit">Submit</button>
    <button @click="Cancel">Cancel</button>
  </div>
</template>
 
 
<script type="text/javascript">
import uuid from "uuid";

export default {
  data() {
    return {
      eventType: "",
      checked: false,
      checkedNames: [],
      inputContent: "",
      cardName: "",
      recipientName: "",
      textarea: "",
      orientation: ""
    };
  },
  props: ["GetCardInfoMenu"],
  methods: {
    Submit: function() {
      //console.log("Submitted!");
      this.$emit("submitChild", {
        msg1: true,
        msg2: false,
        cardID: uuid.v4(),
        cardName: this.cardName,
        recipientName: this.recipientName,
        eventType: this.eventType,
        //title: this.title,
        orientation: this.orientation
      });
    },
    Cancel: function() {
      //console.log("Cancelled!");
      this.$emit("cancelChild", { msg1: true, msg2: false });
    }
  }
};
</script>

<style scoped>
</style>