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
    <div id="app">
      <select v-model="selected">
        <option value>---Event Type---</option>
        <option value="Birthday">Birthday</option>
        <option value="Christmas">Christmas</option>
        <option value="Halloween">Halloween</option>
      </select>
      <br />
      <br />
      <div id="output">The event type selected: {{selected}}</div>
    </div>
    <p>----------------------------------------------------------------------------------------------------------------------</p>
    <p />
    <button @click="Submit">Submit</button>
    <button @click="Cancel">Cancel</button>
  </div>
</template>
 
 
<script type="text/javascript">
export default {
  data() {
    return {
      selected: "",
      picked: "是",
      checked: false,
      checkedNames: [],
      inputContent: "",
      cardName: "",
      recipientName: "",
      textarea: "",
      cardID: ""
    };
  },

  methods: {
    Submit: function() {
      console.log("Submitted!");
      this.$emit("submitChild", {
        msg1: true,
        msg2: false,
        cardID: this.cardID,
        cardName: this.inputContent,
        recipientName: this.recipientName
      });
    },
    Cancel: function() {
      console.log("Cancelled!");
      this.$emit("cancelChild", { msg1: true, msg2: false });
    }
  }
};
</script>

<style scoped>
</style>