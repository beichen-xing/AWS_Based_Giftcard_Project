<template>
  <div class="home">
    <Banner />
    <Footer />
  </div>
</template>

<script>
// @ is an alias to /src
import Footer from "../components/footer";
import Banner from "../components/banner";
//import Header from "../components/header";
export default {
  name: "home",
  components: {
    Footer,
    Banner
    //Header
  }
};
</script>
