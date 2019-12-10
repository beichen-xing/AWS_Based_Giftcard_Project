<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>Card ID:{{this.card_id}}</span>
        <el-button-group class="menu">
          <el-button type="primary" round @click="showAddTextDialog = true">Add Text</el-button>
          <el-button type="primary" round @click="showAddImageDialog = true">Add Image</el-button>
          <el-button type="primary" round @click="displayCard">Display Card</el-button>
        </el-button-group>
      </div>

      <div>
        <span>Text Elements</span>
        <div class="Element" v-for="(text,index) in texts" :key="index">
          <span>{{text.content}}</span>
          <span>,</span>
          <span>{{text.page}}</span>
          <el-button class="card-action-btn" type="text" @click="showEditText(text.text_id)">
            <i class="el-icon-edit"></i>
          </el-button>
          <el-button class="card-action-btn" type="text" @click="delText(text.text_id_unique)">
            <i class="el-icon-delete"></i>
          </el-button>
        </div>
      </div>

      <div>
        <span>Images Elements</span>
        <div class="Element" v-for="(image,index) in images" :key="index">
          <span>{{image.image_id}}</span>
          <span>,</span>
          <span>{{image.page}}</span>
          <el-button class="card-action-btn" type="text" @click="delImage(image.image_id_unique)">
            <i class="el-icon-delete"></i>
          </el-button>
        </div>
      </div>
    </el-card>

    <div class="Canvas" :visible.sync="this.displayCardSwitch">
      <div>Front Page</div>
      <canvas
        id="frontPage"
        width="900"
        height="900"
        style="border:1px solid #ffff; background-color: ivory"
      ></canvas>
      <div>Back Page</div>
      <canvas
        id="backPage"
        width="900"
        height="900"
        style="border:1px solid #ffff; background-color: ivory"
      ></canvas>
      <div>Inner left Page</div>
      <canvas
        id="leftPage"
        width="900"
        height="900"
        style="border:1px solid #ffff; background-color: ivory"
      ></canvas>
      <div>Inner right Page</div>
      <canvas
        id="rightPage"
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

    <el-dialog
      title="Edit a Text Element"
      :visible.sync="showEditTextDialog"
      :close-on-click-modal="false"
    >
      <el-form :model="editTextForm" ref="editTextForm">
        <el-form-item label="content">
          <el-input type="textarea" v-model="editTextForm.content" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Size">
          <el-input v-model="editTextForm.size" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Bounds">
          <el-input v-model="editTextForm.bounds" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Font">
          <el-select v-model="editTextForm.font" placeholder="please choose a font">
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
          <el-select v-model="editTextForm.color" placeholder="please choose a color">
            <el-option label="red" value="red"></el-option>
            <el-option label="black" value="black"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="Page">
          <el-select v-model="editTextForm.page" placeholder="please choose a page">
            <el-option label="Front page" value="Front page"></el-option>
            <el-option label="Inner left page" value="Inner Left page"></el-option>
            <el-option label="Inner right page" value="Inner right page"></el-option>
            <el-option label="Back page" value="Back page"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeEditText()">Cancel</el-button>
        <el-button type="primary" @click="editText()">Add</el-button>
      </span>
    </el-dialog>

    <!-- add image form -->
    <el-dialog
      title="Add a new Image Element"
      :visible.sync="showAddImageDialog"
      :close-on-click-modal="false"
    >
      <el-form :model="addImageForm" ref="addTextForm">
        <el-form-item label="Bounds">
          <el-input v-model="addImageForm.bounds" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Page">
          <el-select v-model="addImageForm.page" placeholder="please choose a page">
            <el-option label="Front page" value="Front page"></el-option>
            <el-option label="Inner left page" value="Inner Left page"></el-option>
            <el-option label="Inner right page" value="Inner right page"></el-option>
            <el-option label="Back page" value="Back page"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <el-upload
        class="upload-demo"
        action="https://jsonplaceholder.typicode.com/posts/"
        :on-preview="handlePreview"
        :on-remove="handleRemove"
        :file-list="fileList"
        :limit="1"
        list-type="picture"
        :on-change="getFile"
      >
        <el-button size="small" type="primary">Upload</el-button>
        <div slot="tip" class="el-upload__tip">only supports jpg/png files，and size limit is 500kb</div>
      </el-upload>

      <span slot="footer" class="dialog-footer">
        <el-button @click="closeAddImage()">Cancel</el-button>
        <el-button type="primary" @click="addImage()">Add</el-button>
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
      showAddImageDialog: false,
      showEditTextDialog: false,
      texts: [],
      images: [],
      imagefiles: [],
      fileList: [],
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
      emptyImageForm: {
        image_id: "",
        card_id: "",
        Page: "",
        Content: "",
        Bounds: ""
      },
      addImageForm: {
        image_id: "",
        image_path: "",
        bounds: "",
        card_id: "",
        page: "",
        card_content: ""
      },
      currentImageForm: {
        image_id: "",
        card_id: "",
        Page: "",
        Content: "",
        Bounds: ""
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
      currentEditTextForm: {
        content: "",
        font: "",
        bounds: "",
        color: "",
        size: "",
        text_id: "",
        card_id: "",
        page: ""
      },
      editTextForm: {
        content: "",
        font: "",
        bounds: "",
        color: "",
        size: "",
        text_id: "",
        card_id: "",
        page: ""
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
    getFile(file, fileList) {
      //console.log(file.raw);
      this.getBase64(file.raw).then(res => {
        console.log(res);
        //var segments = res.base64Encoding.split(",");
        //this.addImageForm.card_content = segments[1];
        this.addImageForm.card_content = res;
        console.log(res);
        console.log(this.addImageForm.card_content);
      });
      this.addImageForm.image_id = file.uid;
      // this.addImageForm.image_path =
      //   "https://cs509finalinteration.s3.us-east-2.amazonaws.com/cs509finalinteration/" +
      //   image_id;
    },
    getBase64(file) {
      return new Promise(function(resolve, reject) {
        let reader = new FileReader();
        let imgResult = "";
        reader.readAsDataURL(file);
        reader.onload = function() {
          imgResult = reader.result;
        };
        reader.onerror = function(error) {
          reject(error);
        };
        reader.onloadend = function() {
          resolve(imgResult);
        };
      });
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    closeAddText() {
      this.showAddTextDialog = false;
      this.addTextForm = JSON.parse(JSON.stringify(this.emptyTextForm));
    },
    closeImageText() {
      this.showAddImageDialog = false;
      //this.addImageForm = JSON.parse(JSON.stringify(this.emptyImageForm));
    },
    addImage() {
      this.addImageForm.card_id = this.card_id;
      this.$http
        .post(
          "https://tpn0863cea.execute-api.us-east-2.amazonaws.com/beta/AddImage",
          {
            headers: {
              "Postman-Token": "ec539028-18ec-4376-8357-423b2d8d5c01",
              "cache-control": "no-cache",
              "Access-Control-Allow-Origin": "*",
              "Content-Type": "application/json"
            }
          },
          {
            data: this.addImageForm
          }
        )
        .then(() => {
          this.$message({
            message: "Congrats,a new image is added",
            type: "success"
          });
          //this.addTextForm = JSON.parse(JSON.stringify(this.emptyTextForm));
          this.listImagesFromDB(this.card_id);
          //console.log(this.addImageForm);
          this.showAddImageDialog = false;
          console.log(this.addImageForm);
        })
        .catch(err => {
          //this.addCardForm = JSON.parse(JSON.stringify(this.emptyForm));
          this.$message.error(err);
        });
    },
    displayCard() {
      console.log("triggered");
      this.displayCardSwitch = true;
      var canvas = document.getElementById("frontPage");
      var canvas_back = document.getElementById("backPage");
      var canvas_left = document.getElementById("leftPage");
      var canvas_right = document.getElementById("rightPage");

      if (this.currentCardForm.orientation == "Landscape") {
        canvas.width = 900;
        canvas.height = 600;
        canvas_back.width = 900;
        canvas_back.height = 600;
        canvas_left.width = 900;
        canvas_left.height = 600;
        canvas_right.width = 900;
        canvas_right.height = 600;
      } else {
        canvas.width = 600;
        canvas.height = 900;
        canvas_back.width = 600;
        canvas_back.height = 900;
        canvas_left.width = 600;
        canvas_left.height = 900;
        canvas_right.width = 600;
        canvas_right.height = 900;
      }

      var ctx = canvas.getContext("2d");
      var ctx_back = canvas_back.getContext("2d");
      var ctx_left = canvas_left.getContext("2d");
      var ctx_right = canvas_right.getContext("2d");

      function drawText(content, centerX, centerY, size, font, color) {
        ctx.save();
        ctx.font = size + "px " + font;
        ctx.textAlign = "center";
        ctx.textBaseline = "middle";
        ctx.fillStyle = color;
        ctx.fillText(content, centerX, centerY);
        ctx.restore();
      }
      function drawBackText(content, centerX, centerY, size, font, color) {
        ctx_back.save();
        ctx_back.font = size + "px " + font;
        ctx_back.textAlign = "center";
        ctx_back.textBaseline = "middle";
        ctx_back.fillStyle = color;
        ctx_back.fillText(content, centerX, centerY);
        ctx_back.restore();
      }
      function drawLeftText(content, centerX, centerY, size, font, color) {
        ctx_left.save();
        ctx_left.font = size + "px " + font;
        ctx_left.textAlign = "center";
        ctx_left.textBaseline = "middle";
        ctx_left.fillStyle = color;
        ctx_left.fillText(content, centerX, centerY);
        ctx_left.restore();
      }
      function drawRightText(content, centerX, centerY, size, font, color) {
        ctx_right.save();
        ctx_right.font = size + "px " + font;
        ctx_right.textAlign = "center";
        ctx_right.textBaseline = "middle";
        ctx_right.fillStyle = color;
        ctx_right.fillText(content, centerX, centerY);
        ctx_right.restore();
      }

      //var strDataURI =
      // "https://cs509finalinteration.s3.us-east-2.amazonaws.com/cs509finalinteration/0987654321";
      //  :image/jpeg;base64,/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAMCAgMCAgMDAwMEAwMEBQgFBQQEBQoHBwYIDAoMDAsKCwsNDhIQDQ4RDgsLEBYQERMUFRUVDA8XGBYUGBIUFRT/2wBDAQMEBAUEBQkFBQkUDQsNFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBT/wAARCAE7BEUDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD9U6KKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACii";
      //var img = new Image();
      //console.log(this.images);
      //console.log(this.images[0].page);
      for (var i = 0; i < this.images.length; i++) {
        //console.log(this.images.length);
        var bound = this.images[i].bounds.split(",");
        var boundx = parseInt(bound[0]);
        var boundy = parseInt(bound[1]);
        var img = new Image();
        //img.onload = function() {
        if (this.images[i].page == "Front page") {
          img.onload = function() {
            ctx.drawImage(img, boundx, boundy);
          };
        }
        if (this.images[i].page == "Back page") {
          img.onload = function() {
            ctx_back.drawImage(img, boundx, boundy);
          };
        }
        if (this.images[i].page == "Inner Left page") {
          img.onload = function() {
            ctx_left.drawImage(img, boundx, boundy);
          };
        }
        if (this.images[i].page == "Inner right page") {
          img.onload = function() {
            ctx_right.drawImage(img, boundx, boundy);
          };
        }
        //};
        img.src =
          "https://cs509finalinteration.s3.us-east-2.amazonaws.com/cs509finalinteration/" +
          this.images[i].image_id;
        //ctx.drawImage(img, 0, 0); // Or at whatever offset you like
      }
      //img.src = strDataURI;

      function drawImage(strDataURI) {}

      for (var j = 0; j < this.texts.length; j++) {
        var bound = this.texts[j].bounds.split(",");
        var boundx = parseInt(bound[0]);
        var boundy = parseInt(bound[1]);
        //console.log(this.texts[j].page);
        if (this.texts[j].page == "Front page") {
          drawText(
            this.texts[j].content,
            boundx,
            boundy,
            this.texts[j].size,
            this.texts[j].font,
            this.texts[j].color
          );
        }
        if (this.texts[j].page == "Back page") {
          drawBackText(
            this.texts[j].content,
            boundx,
            boundy,
            this.texts[j].size,
            this.texts[j].font,
            this.texts[j].color
          );
        }
        if (this.texts[j].page == "Inner Left page") {
          drawLeftText(
            this.texts[j].content,
            boundx,
            boundy,
            this.texts[j].size,
            this.texts[j].font,
            this.texts[j].color
          );
        }
        if (this.texts[j].page == "Inner right page") {
          drawRightText(
            this.texts[j].content,
            boundx,
            boundy,
            this.texts[j].size,
            this.texts[j].font,
            this.texts[j].color
          );
        }
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
    editText() {
      this.showEditTextDialog = false;
      console.log(this.editTextForm);
      this.$http
        .post(
          "https://r6m8zn2okh.execute-api.us-east-2.amazonaws.com/beta/EditText",
          {
            headers: {
              "Postman-Token": "ec539028-18ec-4376-8357-423b2d8d5c01",
              "cache-control": "no-cache",
              "Access-Control-Allow-Origin": "*",
              "Content-Type": "application/json"
            }
          },
          {
            data: this.editTextForm
          }
        )
        .then(() => {
          this.$message({
            message: "Congrats,text is modified",
            type: "success"
          });
          this.addTextForm = JSON.parse(JSON.stringify(this.emptyTextForm));
          this.listText(this.card_id);
        })
        .catch(err => {
          //this.addCardForm = JSON.parse(JSON.stringify(this.emptyForm));
          this.$message.error(err);
        });
    },
    closeEditText() {
      this.showEditTextDialog = false;
    },
    showEditText(id) {
      this.$http
        .post(
          "https://5u792g7yk2.execute-api.us-east-2.amazonaws.com/beta/getText",
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
          this.currentEditTextForm = JSON.parse(JSON.stringify(res.data));
          this.editTextForm.content = this.currentEditTextForm.content;
          this.editTextForm.font = this.currentEditTextForm.font;
          this.editTextForm.bounds = this.currentEditTextForm.bounds;
          this.editTextForm.color = this.currentEditTextForm.color;
          this.editTextForm.size = this.currentEditTextForm.size;
          this.editTextForm.text_id = this.currentEditTextForm.text_id;
          this.editTextForm.card_id = this.currentEditTextForm.card_id;
          this.editTextForm.page = this.currentEditTextForm.page;
        })
        .then(() => {
          this.showEditTextDialog = true;
        })
        .catch(err => {
          this.$message.error(err);
        });

      console.log(this.currentTextForm);
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
                text_id_unique: id
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
    delImage(id) {
      this.$confirm("Are you sure to delete", {
        confirmButtonText: "Confirm",
        cancelButtonText: "Cancel",
        type: "warning"
      }).then(() => {
        //console.log(id);
        this.$http
          .post(
            `https://r6m8zn2okh.execute-api.us-east-2.amazonaws.com/beta/DeleteImage`,
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
                image_id_unique: id
              }
            }
          )
          .then(() => {
            this.$message({
              type: "success",
              message: "The image element is deleted!"
            });
            this.listImagesFromDB(this.card_id);
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
    listImages(id) {
      this.$http
        .post(
          "https://kays5p3a93.execute-api.us-east-2.amazonaws.com/beta/ListImageOfCard",
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
          this.images = JSON.parse(JSON.stringify(res.data.list));
        })
        .catch(err => {
          //this.addCardForm = JSON.parse(JSON.stringify(this.emptyForm));
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
    },
    getText(id) {
      this.$http
        .post(
          "https://5u792g7yk2.execute-api.us-east-2.amazonaws.com/beta/getText",
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
          this.$message.error(err);
        });
    },
    listImagesFromDB(id) {
      //console.log("triggered");
      this.$http
        .post(
          "https://kays5p3a93.execute-api.us-east-2.amazonaws.com/beta/ListImageOfCard",
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
              card_id: id
            }
          }
        )
        .then(res => {
          //console.log(JSON.parse(JSON.stringify(res.data.images)));
          this.images = JSON.parse(JSON.stringify(res.data.images));
          console.log(images);
          //console.log(this.images[0]);
        })
        .catch(err => {
          //this.addCardForm = JSON.parse(JSON.stringify(this.emptyForm));
          this.$message.error(err);
        });
    }
  },
  created() {
    this.card_id = this.$route.query.id;
    this.listText(this.card_id);
    this.listImagesFromDB(this.card_id);
    this.getCard(this.$route.query.id);
    //console.log("Editor");
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