<template>
  <div class="about">
    <svg class="icon" aria-hidden="true">
      <use xlink:href="#icon-under-dev" />
    </svg>
    <p>UNDER DEVELOPMENT</p>
  </div>
</template>

<script>
export default {
  components: {},
  props: {},
  data() {
    return {};
  },
  watch: {},
  computed: {},
  methods: {},
  created() {},
  mounted() {}
};
</script>
<style scoped>
 .about {
   height: 100%;
   text-align: center;
   display: flex;
   flex-direction: column;
   justify-content: center
 }

.icon {
  margin: 0 auto;
  width: 15vmax;
  height: 15vmax;
}
 p {
   font-size: 3vmax;
   text-shadow: 1px 1px 6px #409eff;
 }
</style>
