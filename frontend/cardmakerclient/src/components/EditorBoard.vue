<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>Card ID:{{this.card_id}}</span>
        <el-button-group class="menu">
          <el-button type="primary" round @click="showAddTextDialog = true">Add Text</el-button>
          <el-button type="primary" round>Add Image</el-button>
          <el-button type="primary" round @click="displayCard">Display Card</el-button>
        </el-button-group>
      </div>

      <div>
        <span>Text Elements</span>
        <div class="Element" v-for="(text,index) in texts" :key="index">
          <span>{{text.content}}</span>
          <span>{{text.page}}</span>
          <el-button class="card-action-btn" type="text" @click="delText(text.text_id)">
            <i class="el-icon-delete"></i>
          </el-button>
        </div>
      </div>

      <div>
        <span>Images Elements</span>
        <div class="Element" v-for="(image,index) in images" :key="index">
          <span>{{image.content}}</span>
          <el-button class="card-action-btn" type="text" @click="delText(text.id)">
            <i class="el-icon-delete"></i>
          </el-button>
        </div>
      </div>
    </el-card>

    <div class="Canvas" :visible.sync="displayCardSwitch">
      <div>First Page</div>
      <canvas
        id="frontPage"
        width="900"
        height="900"
        style="border:1px solid #ffff; background-color: ivory"
      ></canvas>
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
            <el-option
              label="Comic Sans MS
                "
              value="Comic Sans MS
                "
            ></el-option>
            <el-option label="Monaco
                " value="Monaco
                "></el-option>
            <el-option
              label="Brush Script MT
                "
              value="Brush Script MT
                "
            ></el-option>
            <el-option
              label="Lucida Bright
                "
              value="Lucida Bright
                "
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="Color">
          <el-select v-model="addTextForm.color" placeholder="please choose a color">
            <el-option label="red" value="red"></el-option>
            <el-option label="black" value="black"></el-option>
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
      texts: [],
      images: [],
      addTextForm: {
        text_id: "",
        id: "",
        content: "",
        page: "",
        font: "",
        bounds: "",
        size: "",
        color: ""
      },
      emptyTextForm: {
        id: "",
        card_id: "",
        Text: "",
        Page: "",
        Font: "",
        Bounds: "",
        size: "",
        color: ""
      },
      currentTextForm: {
        id: "",
        card_id: "",
        content: "",
        page: "",
        font: "",
        bounds: "",
        size: "",
        color: ""
      },
      currentCardForm: {
        id: "",
        name: "",
        recipient: "",
        type: "",
        orientation: ""
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
      if (this.currentCardForm.orientation == "Landscape") {
        canvas.width = 900;
        canvas.height = 600;
      } else {
        canvas.width = 600;
        canvas.height = 900;
      }

      var ctx = canvas.getContext("2d");

      function drawText(content, centerX, centerY, size, font, color) {
        ctx.save();
        ctx.font = size + "px " + font;
        ctx.textAlign = "center";
        ctx.textBaseline = "middle";
        ctx.fillStyle = color;
        ctx.fillText(content, centerX, centerY);
        ctx.restore();
      }
      for (var j = 0; j < this.texts.length; j++) {
        var bound = this.texts[j].bounds.split(",");
        var boundx = parseInt(bound[0]);
        var boundy = parseInt(bound[1]);
        drawText(
          this.texts[j].content,
          boundx,
          boundy,
          this.texts[j].size,
          this.texts[j].font,
          this.texts[j].color
        );
        console.log(this.texts[j]);
        console.log("Text showed!");
      }
    },
    addText() {
      this.showAddTextDialog = false;
      this.addTextForm.id = this.card_id;
      this.addTextForm.text_id = uuid.v4();
      console.log(this.addCardForm);
      this.$http
        .post(
          "https://nkbemx30yf.execute-api.us-east-2.amazonaws.com/beta/AddText",
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
          this.listText(this.card_id);
        })
        .catch(err => {
          this.addCardForm = JSON.parse(JSON.stringify(this.emptyForm));
          this.$message.error(err);
        });
    },
    delText(id) {
      this.$confirm("Are you sure to delete", {
        confirmButtonText: "Confirm",
        cancelButtonText: "Cancel",
        type: "warning"
      }).then(() => {
        //console.log(id);
        this.$http
          .post(
            `https://nkbemx30yf.execute-api.us-east-2.amazonaws.com/beta/delText`,
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
              type: "success",
              message: "The text element is deleted!"
            });
            this.listText(this.card_id);
          })
          .catch(err => console.log(err));
      });
    },
    listText(id) {
      this.$http
        .post(
          "https://43rbvpu0bj.execute-api.us-east-2.amazonaws.com/beta/ListAllTexts",
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
          this.texts = JSON.parse(JSON.stringify(res.data.list));
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
    this.listText(this.card_id);
    this.getCard(this.$route.query.id);
  }
};
</script>

<style scoped>
.board {
  border: 1px solid #ffff;
}

span {
  font-weight: bold;
  font-size: 16px;
}

.page {
  height: 900px;
  width: 600px;
  background: #d3d3d3;
}

.menu {
  margin-left: 30px;
}

.Element {
  margin-left: 20px;
}

.box-card {
  width: 1000px;
  margin: 0 auto;
  box-shadow: #fff 0px 1px 6px;
}

.Canvas {
  text-align: center;
}
</style>