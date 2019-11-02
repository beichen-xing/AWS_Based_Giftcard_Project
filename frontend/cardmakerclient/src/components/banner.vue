<template>
  <div class="body">
    <div class="container">
      <h1>Card Maker</h1>
      <vue-typed-js :strings="['friends', 'loved ones!']">
        <h1>
          A place where you can make cards for
          <span class="typing"></span>
        </h1>
      </vue-typed-js>
    </div>
  </div>
</template>

<script>
export default {
  name: "Banner"
};
</script>

<style scoped>
.body {
  font-family: "Raleway", sans-serif;
  height: 100vh;
  background: url("../images/bg.jpg");
  color: #ccc;
  overflow: hidden;
}

.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  height: 100%;
  padding: 0 3rem;
}

h1,
h2 {
  font-weight: 200;
  margin: 0.4rem;
}

h1 {
  font-size: 3.5rem;
}

h2 {
  font-size: 2rem;
  color: #aaa;
}

/* Cursor */
.txt-type > .txt {
  border-right: 0.2rem solid #777;
}

@media (min-width: 1200px) {
  h1 {
    font-size: 5rem;
  }
}

@media (max-width: 800px) {
  .container {
    padding: 0 1rem;
  }

  h1 {
    font-size: 3rem;
  }
}

@media (max-width: 500px) {
  h1 {
    font-size: 2.5rem;
  }

  h2 {
    font-size: 1.5rem;
  }
}
</style>