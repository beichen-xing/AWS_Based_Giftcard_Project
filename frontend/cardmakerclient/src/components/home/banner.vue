<template>
  <div class="banner-wrapper">
    <div class="text-wrapper">
      <div class="title-wrapper">
        <transition name="el-fade-in-linear">
          <div v-show="showBannerTitle">
            <h1>Card Maker</h1>
          </div>
        </transition>
      </div>
      <vue-typed-js :strings="['Lorem ipsum dolor sit amet, consectetur adipisicing elit.']">
        <p class="typed-wrapper">
          <span class="typing"></span>
        </p>
      </vue-typed-js>
    </div>
  </div>
</template>

<script>
export default {
  name: "Banner",
  data() {
    return {
      showBannerTitle: false
    };
  },
  methods: {},
  mounted() {
    setTimeout(() => {
      this.showBannerTitle = true;
    }, 0);
  }
};
</script>

<style scoped>
.banner-wrapper {
  font-family: "Raleway", sans-serif;
  background: url("../../assets/images/bg.jpg") no-repeat;
  background-size: cover;
  color: #ccc;
  height: 100%;
  min-height: 0;
}

.text-wrapper {
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.text-wrapper h1 {
  text-align: center;
  font-size: 5vmax;
  font-weight: lighter;
  /* padding: 10vh 0; */
}

.title-wrapper {
  height: 10vh;
  margin-bottom: 2vh;
}

.text-wrapper > p {
  text-align: center;
  font-size: 3vmax;
}
.typed-wrapper {
  text-align: center;
  font-size: 2vmax;
  width: 100%;
}
.typing {
  display: inline-block;
  min-height: 10vh;
}
/* Cursor */
.txt-type > .txt {
  border-right: 1vmax solid #777;
}
</style>