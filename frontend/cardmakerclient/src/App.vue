<template>
  <div id="app">
    <div class="nav">
      <el-menu :default-active="$route.path" router mode="horizontal">
        <el-menu-item
          v-for="(navItem,index) in navList"
          :key="index"
          :index="navItem.index"
        >{{navItem.name}}</el-menu-item>
      </el-menu>
    </div>
    <div class="view-wrapper">
      <router-view />
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      navList: [
        { name: "Home", index: "/" },
        { name: "About", index: "/about" },
        { name: "Gallery", index: "/gallery" },
        { name: "Card Editor", index: "/editor" }
      ]
    };
  },
  methods: {},
  mounted() {
  }
};
</script>
<style scoped>
#app {
  height: 100%;
  display: flex;
  flex-direction: column;
  overflow: hidden;
  background: #1b1d1f;
  color: #fff;
}

.icon {
  width: 1em;
  height: 1em;
  vertical-align: -0.15em;
  fill: currentColor;
  overflow: hidden;
}

.view-wrapper {
  flex: 1;
}

/* menu */
.el-menu--horizontal > .el-menu-item {
  height: 3vmax;
  line-height: 3vmax;
}
.el-menu-item {
  font-size: 1.2vmax;
}
</style>
