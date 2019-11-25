<template>
  <div>
    <!-- <div>
      <span>First Page</span>
      <canvas
        id="frontPage"
        width="900"
        height="900"
        style="border:1px solid #ffff; background-color: ivory"
      ></canvas>

      <span>Inner Left Page</span>
      <canvas
        id="leftPage"
        width="900"
        height="900"
        style="border:1px solid #ffff; background-color: ivory"
      ></canvas>

      <span>Inner Right Page</span>
      <canvas
        id="rightPage"
        width="900"
        height="900"
        style="border:1px solid #ffff; background-color: ivory"
      ></canvas>

      <span>Back Page</span>
      <canvas
        id="backPage"
        width="900"
        height="900"
        style="border:1px solid #ffff; background-color: ivory"
      ></canvas>
    </div>-->
  </div>
</template>


<script>
//import Canvas from "../components/editor/Canvas";
export default {
  name: "CardPreview",
  components: {},
  data() {},
  methods() {},
  created() {
    console.log("SET");
    this.card_id = this.$route.query.id;
    console.log(this.card_id);
    //this.listText(this.card_id);
    //this.getCard(this.$route.query.id);
  }
};
</script>


<style scoped>
span {
  font-weight: bold;
  font-size: 16px;
  color: white;
}
</style>