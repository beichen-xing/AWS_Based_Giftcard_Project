<template>
  <div id="app">
    <div id="nav" class="nav">
      <router-link to="/" class="routerlink">Home</router-link>|
      <router-link to="/about" class="routerlink">About</router-link>|
      <router-link to="/Gallery" class="routerlink">Gallery</router-link>|
      <router-link to="/Editor" class="routerlink">Card Editor</router-link>
    </div>
    <router-view />
  </div>
</template>


<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  /* margin-top: 60px; */
}

.nav {
  display: inline-block;
  width: 100%;
  text-align: center;
  background: #000;
  font-size: 1.25em;
  color: #fff;
}

.routerlink {
  display: inline-block;
  border: none;
  background: #000;
  color: #fff;
  padding: 7px 20px;
  cursor: pointer;
}
</style>
