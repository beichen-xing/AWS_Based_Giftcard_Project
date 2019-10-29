<template>
  <div>
    <form action>
      <input type="submit" value="Add Visual Element" class="btn" />
      <input type="submit" value="Delete Visual Element" class="btn" />
      <input type="submit" value="Edit Visual Element" class="btn" />
      <input type="submit" value="Placeholer" class="btn" />
      <input type="submit" value="Save" class="btn" />
    </form>
    <div>
      <form action>
        <input type="submit" value="Add Image" class="btn" />
        <input type="submit" value="Add Text" class="btn" />
        <input type="submit" value="Edit Visual Element" class="btn" />
        <input type="submit" value="Placeholer" class="btn" />
        <input type="submit" value="Save" class="btn" />
      </form>
    </div>
  </div>
</template>


<script >
export default {
  name: "Menu"
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