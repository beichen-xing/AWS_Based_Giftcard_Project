<template>
  <div>
    <p>----------------------------------------------------------------------------------------------------------------------</p>
    <p />
    <!-- input 框: trim 自动过滤用户输入的首尾空格 -->
    <input v-model.trim="inputContent" placeholder=" 请输入 ... " />
    input 框内容为: {{ inputContent }}
    <p></p>
    <p />
    <!-- 文本域 -->
    <textarea v-model="textarea" placeholder="请输入 ... "></textarea>
    文本域 内容为： {{ textarea }}
    <p>----------------------------------------------------------------------------------------------------------------------</p>
    <p />
    <!-- 复选框 -->
    <p>单个复选框 （取逻辑值）：</p>
    <input type="checkbox" v-model="checked" />
    <label>{{ checked }}</label>

    <p>多个复选框 （绑定到同一个数组）：</p>
    <input type="checkbox" value="我" v-model="checkedNames" />
    <label>我</label>
    <input type="checkbox" value="喜欢" v-model="checkedNames" />
    <label>喜欢</label>
    <input type="checkbox" value="小熊" v-model="checkedNames" />
    <label>小熊</label>
    <br />
    <span>复选框数组的值为: {{ checkedNames }}</span>

    <p>----------------------------------------------------------------------------------------------------------------------</p>
    <p />
    <!-- 单选框 -->
    <input type="radio" value="是" v-model="picked" />
    <label>是</label>
    <br />
    <input type="radio" value="否" v-model="picked" />
    <label>否</label>
    <br />
    <span>单选框选中值为: {{ picked }}</span>
    <p>----------------------------------------------------------------------------------------------------------------------</p>
    <p />

    <!-- 下拉菜单 -->
    <div id="app">
      <select v-model="selected">
        <option value>---Event Type---</option>
        <option value="大柚子">大柚子</option>
        <option value="小柚子">小柚子</option>
        <option value="大大小小的柚子">大大小小的柚子</option>
      </select>
      <br />
      <br />
      <div id="output">选择的水果是: {{selected}} (啦啦啦，悄悄告诉你，反正我就是喜欢柚子 ...)</div>
    </div>
    <p>----------------------------------------------------------------------------------------------------------------------</p>
    <p />
  </div>
</template>
 
 
<script type="text/javascript">
export default {
  data() {
    return {
      selected: "",
      picked: "是",
      checked: false,
      checkedNames: [],
      inputContent: "",
      textarea: ""
    };
  }
};
</script>