<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>Card ID:{{this.card_id}}</span>
        <el-button class="menu" type="text" @click="showAddTextDialog = true">Add Text</el-button>
        <el-button class="menu" type="text">Add Image</el-button>
        <el-button class="menu" type="text">Display Card</el-button>
      </div>

      <div>
        <div class="Element" v-for="(text,index) in texts" :key="index">
          <span>{{text.content}}</span>
          <el-button class="card-action-btn" type="text" @click="delCard(card.id)">
            <i class="el-icon-delete"></i>
          </el-button>
        </div>
      </div>
    </el-card>

    <div class="canvas" :visible.sync="displayCardSwitch">
      <div>First Page</div>
      <canvas id="frontPage" width="900" height="900" style="border:1px solid #ffff;"></canvas>
    </div>

    <el-dialog
      title="Add a new Text Element"
      :visible.sync="showAddTextDialog"
      :close-on-click-modal="false"
    >
      <el-form :model="addTextForm" ref="addTextForm">
        <el-form-item label="content">
          <el-input type="textarea" v-model="addTextForm.content" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Size">
          <el-input v-model="addTextForm.size" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Bounds">
          <el-input v-model="addTextForm.bounds" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Font">
          <el-select v-model="addTextForm.font" placeholder="please choose a font">
            <el-option label="Helvetica" value="Helvetica"></el-option>
            <el-option label="Arial" value="Arial"></el-option>
            <el-option
              label="Times New Roman
                "
              value="Times New Roman
                "
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="Page">
          <el-select v-model="addTextForm.page" placeholder="please choose a page">
            <el-option label="Front page" value="Front page"></el-option>
            <el-option label="Inner left page" value="Inner Left page"></el-option>
            <el-option label="Inner right page" value="Inner right page"></el-option>
            <el-option label="Back page" value="Back page"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeAddText()">Cancel</el-button>
        <el-button type="primary" @click="addText()">Add</el-button>
      </span>
    </el-dialog>
  </div>
</template>


<script>
import uuid from "uuid";
//import Canvas from "../components/editor/Canvas";

export default {
  name: "EditorBoard",
  components: {},
  data() {
    return {
      showAddTextDialog: false,
      displayCardSwitch: false,
      texts: [
        {
          id: "1",
          content: "Text1",
          size: "32px",
          font: "Arial",
          bounds: "400,500"
        },
        {
          id: "2",
          content: "Text2",
          size: "32",
          font: "Arial",
          bounds: "10,50"
        }
      ],
      addTextForm: {
        id: "",
        color: "",
        card_id: "",
        content: "",
        page: "",
        font: "",
        bounds: "",
        size: ""
      },
      emptyTextForm: {
        id: "",
        color: "",
        card_id: "",
        Text: "",
        Page: "",
        Font: "",
        Bounds: "",
        size: ""
      },
      currentTextForm: {
        id: "",
        color: "",
        card_id: "",
        content: "",
        page: "",
        font: "",
        bounds: "",
        size: ""
      },
      currentCardForm: {
        id: "",
        name: "",
        recipient: "",
        type: "",
        oritentation: ""
      },
      card_id: ""
    };
  },
  methods: {
    closeAddText() {
      this.showAddTextDialog = false;
      this.addTextForm = JSON.parse(JSON.stringify(this.emptyTextForm));
    },
    displayCard() {
      this.displayCardSwitch = true;
      var canvas = document.getElementById("frontPage");

      var ctx = canvasElement.getContext("2d");
      for (j = 0; j < texts.length; j++) {
        ctx.font = text[j].size + " " + text[j].font;
        var bound = text[j].bounds.split(",");
        var boundx = parseInt(bounds[0]);
        var boundy = parseInt(bounds[1]);
        ctx.fileText(text[j].content, boundx, boundy);
        console.log("Text showed!");
      }
    },
    addText() {
      this.showAddTextDialog = false;
      this.addTextForm.id = uuid.v4();
      console.log(this.addCardForm);
      this.$http
        .post(
          "https://smrii41wj7.execute-api.us-east-2.amazonaws.com/beta/addText",
          {
            headers: {
              "Postman-Token": "ec539028-18ec-4376-8357-423b2d8d5c01",
              "cache-control": "no-cache",
              "Access-Control-Allow-Origin": "*",
              "Content-Type": "application/json"
            }
          },
          {
            data: this.addTextForm
          }
        )
        .then(() => {
          this.$message({
            message: "Congrats,a new text is added",
            type: "success"
          });
          this.addTextForm = JSON.parse(JSON.stringify(this.emptyTextForm));
          this.getCard(this.card_id);
        })
        .catch(err => {
          this.addCardForm = JSON.parse(JSON.stringify(this.emptyForm));
          this.$message.error(err);
        });
    },
    delText(id) {
      this.showAddTextDialog = false;
      console.log(this.addCardForm);
      this.$http
        .post(
          "https://smrii41wj7.execute-api.us-east-2.amazonaws.com/beta/addText",
          {
            headers: {
              "Postman-Token": "ec539028-18ec-4376-8357-423b2d8d5c01",
              "cache-control": "no-cache",
              "Access-Control-Allow-Origin": "*",
              "Content-Type": "application/json"
            }
          },
          {
            data: {
              id: id
            }
          }
        )
        .then(() => {
          this.$message({
            message: "Congrats,a new card is added",
            type: "success"
          });
          this.addCardForm = JSON.parse(JSON.stringify(this.emptyForm));
          this.getCard(this.card_id);
        })
        .catch(err => {
          this.addCardForm = JSON.parse(JSON.stringify(this.emptyForm));
          this.$message.error(err);
        });
    },
    getCard(id) {
      this.$http
        .post(
          "https://smrii41wj7.execute-api.us-east-2.amazonaws.com/beta/getCard",
          {
            headers: {
              "Postman-Token": "ec539028-18ec-4376-8357-423b2d8d5c01",
              "cache-control": "no-cache",
              "Access-Control-Allow-Origin": "*",
              "Content-Type": "application/json"
            }
          },
          {
            data: {
              id: id
            }
          }
        )
        .then(res => {
          this.currentCardForm = JSON.parse(JSON.stringify(res.data));
        })
        .catch(err => {
          //this.addCardForm = JSON.parse(JSON.stringify(this.emptyForm));
          this.$message.error(err);
        });
    }
  },
  getText(id) {
    this.$http
      .post(
        "https://smrii41wj7.execute-api.us-east-2.amazonaws.com/beta/getText",
        {
          headers: {
            "Postman-Token": "ec539028-18ec-4376-8357-423b2d8d5c01",
            "cache-control": "no-cache",
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json"
          }
        },
        {
          data: {
            id: id
          }
        }
      )
      .then(res => {
        this.currentTextForm = JSON.parse(JSON.stringify(res.data));
      })
      .catch(err => {
        //this.addCardForm = JSON.parse(JSON.stringify(this.emptyForm));
        this.$message.error(err);
      });
  },
  created() {
    this.card_id = this.$route.query.id;
    this.getCard(this.$route.query.id);
  }
};
</script>

<style scoped>
.board {
  border: 1px solid #ffff;
}
.page {
  height: 900px;
  width: 600px;
  background: #d3d3d3;
}

.menu {
  margin-left: 10px;
}

.Element {
  margin-left: 20px;
}
</style>