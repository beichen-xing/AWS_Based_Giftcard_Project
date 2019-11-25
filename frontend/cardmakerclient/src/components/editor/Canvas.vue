<template>
  <div class="canvas">
    Text
    <!-- <div>First Page</div>
    <canvas id="frontPage" width="900" height="900" style="border:1px solid #ffff;"></canvas>-->
  </div>
</template>


<script>
export default {
  name: "Canvas",
  data() {
    textElement: [
      {
        id: "1",
        content: "Text1",
        size: "32",
        font: "Arial",
        bounds: "10,50"
      }
    ];
  },
  methods: {},
  created() {
    var canvas = document.getElementById("frontPage");
  }
};
</script>

<style scoped>
</style>