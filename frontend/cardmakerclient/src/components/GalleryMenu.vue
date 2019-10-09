<template>
  <div>
    <form action>
      <input type="submit" value="Add Card" class="btn" />
      <input type="submit" value="Delete Card" class="btn" />
      <input type="submit" value="Edit Card" class="btn" />
      <input type="submit" value="Duplicate Card" class="btn" />
      <input type="submit" value="Display Card" class="btn" />
      <input type="submit" value="Generate Link" class="btn" />
    </form>
  </div>
</template>


<script >
export default {
  name: "GalleryMenu"
};
</script>

<style scoped>
.btn {
  display: inline-block;
  border: none;
  background: #555;
  color: #fff;
  padding: 7px 20px;
  cursor: pointer;
}
.btn:hover {
  background: #666;
}
</style>