<template>
  <div class="home">
    <Banner />
    <Footer />
  </div>
</template>

<script>
// @ is an alias to /src
import Footer from "../components/home/footer";
import Banner from "../components/home/banner";
export default {
  name: "home",
  components: {
    Footer,
    Banner
  }
};
</script>
<style scoped>
.home {
  height: 100%;
  display: flex;
  flex-direction: column;
}
.banner-wrapper {
  flex: 1;
}
</style>
