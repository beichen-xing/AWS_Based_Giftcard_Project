<template>
  <div>
    <div class="page">
      <v-stage ref="stage" :config="stageSize">
        <v-layer ref="layer">
          <v-image :config="{
          image: image
          }" />
          <v-text v-for="(text,index) in texts" :key="index" :config="text"></v-text>
        </v-layer>
      </v-stage>
    </div>
    <h1>Second Page</h1>
    <div class="page">
      <textarea> Test</textarea>
    </div>
  </div>
</template>


<script>
import VueKonva from "vue-konva";
const width = window.innerWidth;
const height = window.innerHeight;

export default {
  name: "EditorBoard",
  components: {},
  data() {
    return {
      stageSize: {
        width: 900,
        height: 600,
        fill: "red"
      },
      images: [],
      texts: []
    };
  },
  created() {
    //this.$refs.stage.getContainer().style.border = "1px solid black";
    //this.$refs.stage.style.backgroundColor = "red";
    const image = new window.Image();
    image.src = "https://konvajs.org/assets/yoda.jpg";
    const image2 = new window.Image();
    image2.src = "https://konvajs.org/assets/vader.jpg";
    this.images = [
      {
        image,
        image2
      }
    ];
    image.onload = () => {
      // set image only when it is loaded
      this.image = image;
    };

    var imageObj1 = new Image();
    var imageObj2 = new Image();
    imageObj1.src = "https://konvajs.org/assets/yoda.jpg";
    imageObj2.src = "https://konvajs.org/assets/vader.jpg";
    this.texts = [
      {
        x: 180,
        y: 180,
        fill: "white",
        fontSize: 40,
        text: "Text1",
        width: 250
      },
      {
        x: 180,
        y: 100,
        fill: "white",
        fontSize: 40,
        text: "Text2",
        width: 250
      }
    ];
    // this.images = [
    //   {
    //     x: 20,
    //     y: 20,
    //     width: 200,
    //     height: 137,
    //     image: imageObj1
    //   },
    //   {
    //     x: 240,
    //     y: 20,
    //     width: 93,
    //     height: 104,
    //     image: imageObj2
    //   }
    // ];
  }
};
</script>

<style scoped>
.board {
  border: 1px solid #ffff;
}
.page {
  height: 900px;
  width: 600px;
  background: #d3d3d3;
}
</style>