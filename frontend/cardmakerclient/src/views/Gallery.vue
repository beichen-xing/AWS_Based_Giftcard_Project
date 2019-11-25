<template>
  <div class="gallery-wrapper">
    <div class="add-btn" @click="showAddDialog = true">
      <i class="iconfont icon-add"></i>
    </div>
    <!-- <div class="del-btn">
      <i class="iconfont icon-delete"></i>
    </div>-->

    <div class="card-view" v-loading="cardViewLoaded" element-loading-background="transparent">
      <el-card class="card-item" v-for="(card,index) in cards" :key="index">
        <div slot="header" class="card-header">
          <span>{{card.name}}</span>
          <!-- <router-link :to="{ path:'Editor', query: { id: card.id} }">
            <i class="el-icon-edit"></i>
          </router-link>-->
          <el-button class="card-action-btn" type="text" @click="editCard(card.id)">
            <i class="el-icon-edit"></i>
          </el-button>
          <el-button class="card-action-btn" type="text" @click="generateLink(card.id)">
            <i class="el-icon-share"></i>
          </el-button>
          <el-button class="card-action-btn" type="text" @click="previewCard(card.id)">
            <i class="el-icon-view"></i>
          </el-button>
          <el-button class="card-action-btn" type="text" @click="duplicateCard(card.id)">
            <i class="el-icon-document-copy"></i>
          </el-button>
          <el-button class="card-action-btn" type="text" @click="delCard(card.id)">
            <i class="el-icon-delete"></i>
          </el-button>
        </div>
        <p>To:{{card.recipient}}</p>
        <p>type:{{card.type}}</p>
        <p>orientation:{{card.orientation}}</p>
      </el-card>
    </div>
    <!-- add dialog -->
    <el-dialog title="Add a new card" :visible.sync="showAddDialog" :close-on-click-modal="false">
      <el-form :model="addCardForm" ref="addCardForm">
        <el-form-item label="Title" :label-width="formLabelWidth">
          <el-input v-model="addCardForm.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Recipient" :label-width="formLabelWidth">
          <el-input v-model="addCardForm.recipient" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Oritentation" :label-width="formLabelWidth">
          <el-select v-model="addCardForm.oritentation" placeholder="please choose the orientation">
            <el-option label="Landscape" value="Landscape"></el-option>
            <el-option label="Portrait" value="Portait"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="Type" :label-width="formLabelWidth">
          <el-select v-model="addCardForm.type" placeholder="please choose the type">
            <el-option label="Back To School" value="Back To School"></el-option>
            <el-option label="Birthday" value="Birthday"></el-option>
            <el-option label="Christmas" value="Christmas"></el-option>
            <el-option label="Thanksgiving" value="Thanksgiving"></el-option>
            <el-option label="Halloween" value="Halloween"></el-option>
            <el-option label="Anniversary" value="Anniversary"></el-option>
            <el-option label="Valentine's Day" value="Valentine's Day"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeAdd()">Cancel</el-button>
        <el-button type="primary" @click="addCard()">Add</el-button>
      </span>
    </el-dialog>
  </div>
</template>


<script>
import uuid from "uuid";
export default {
  //inject: ["reload"],
  name: "Gallery",
  components: {
    // GalleryMenu,
    // Cards,
    // CardInfo
  },
  data() {
    return {
      showAddDialog: false,
      isCardViewLoading: true,
      // showCards: true,
      // showInputs: false,
      cards: [],
      addCardForm: {
        id: "",
        name: "",
        recipient: "",
        type: "",
        orientation: ""
      },
      emptyForm: {
        id: "",
        name: "",
        recipient: "",
        type: "",
        orientation: ""
      },
      formLabelWidth: "10vw"
      // isSelectedGallery: [],
      // selectedCard: "",
      // GetCardInfoGallery: [],
      // selectedCards: [],
      // isDelete: false
    };
  },
  methods: {
    getCards() {
      this.cardViewLoaded = true;
      this.$http
        .get(
          "https://smrii41wj7.execute-api.us-east-2.amazonaws.com/beta/listCard"
        )
        .then(res => {
          this.cards = JSON.parse(JSON.stringify(res.data.list));
          this.cardViewLoaded = false;
        })
        .catch(err => console.log(err));
    },
    addCard() {
      this.showAddDialog = false;
      this.addCardForm.id = uuid.v4();
      this.$http
        .post(
          "https://smrii41wj7.execute-api.us-east-2.amazonaws.com/beta/addCard",
          {
            headers: {
              "Postman-Token": "ec539028-18ec-4376-8357-423b2d8d5c01",
              "cache-control": "no-cache",
              "Access-Control-Allow-Origin": "*",
              "Content-Type": "application/json"
            }
          },
          {
            data: this.addCardForm
          }
        )
        .then(() => {
          this.$message({
            message: "Congrats,a new card is added",
            type: "success"
          });
          this.addCardForm = JSON.parse(JSON.stringify(this.emptyForm));
          this.getCards();
        })
        .catch(err => {
          this.addCardForm = JSON.parse(JSON.stringify(this.emptyForm));
          this.$message.error(err);
        });
    },
    closeAdd() {
      this.showAddDialog = false;
      this.addCardForm = JSON.parse(JSON.stringify(this.emptyForm));
    },
    delCard(id) {
      this.$confirm("Are you sure to delete", {
        confirmButtonText: "Confirm",
        cancelButtonText: "Cancel",
        type: "warning"
      }).then(() => {
        this.$http
          .post(
            `https://smrii41wj7.execute-api.us-east-2.amazonaws.com/beta/delCard`,
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
              message: "The card is deleted!"
            });
            this.getCards();
          })
          .catch(err => console.log(err));
      });
    },

    editCard(id) {
      this.$router.push({
        path: "Editor",
        query: {
          id: id
        }
      });
    },
    previewCard(id) {
      this.$router.push({
        path: "CardPreview",
        query: {
          id: id
        }
      });
    }
  },
  created() {},
  mounted() {
    this.getCards();
  }
};
</script>


<style scoped>
.add-btn {
  margin: 2vh 1vw 2vh 3vw;
  width: 3vmax;
  height: 3vmax;
  display: inline-block;
  line-height: 3vmax;
  text-align: center;
  background: #fff;
  border-radius: 50%;
  box-shadow: #409eff 0px 1px 0.5vmax;
  transition: 0.4s;
  cursor: pointer;
}

.add-btn:hover {
  border-radius: 10%;
  transform: rotate(180deg);
}

.add-btn i {
  color: #409eff;
  font-size: 1.5vmax;
  font-weight: bolder;
}

.del-btn {
  margin: 2vh 0;
  width: 3vmax;
  height: 3vmax;
  display: inline-block;
  line-height: 3vmax;
  text-align: center;
  background: #fff;
  color: #656b71;
  border-radius: 50%;
  box-shadow: #656b71 0px 1px 0.5vmax;
  transition: 0.4s;
  cursor: pointer;
}

.del-btn:hover {
  color: #fff;
  background: #f44336;
  box-shadow: #f44336 0px 1px 0.5vmax;
  transform: rotate(30deg);
}

.del-btn i {
  color: inherit;
  font-size: 1.5vmax;
  font-weight: bolder;
}

/* card */
.card-view {
  display: flex;
  flex-wrap: wrap;
}
.card-item {
  width: 15vmax;
  height: 10vmax;
  margin: 2vmax;
  display: flex;
  flex-direction: column;
  transition: 0.5s;
  box-shadow: #fff 0px 1px 6px;
}

.card-item:hover {
  box-shadow: #409eff 0px 1px 20px;
  transform: translate(10px, -10px);
}

.card-item >>> .el-card__header {
  padding: 0;
}

.card-item >>> .el-card__body {
  flex: 1;
  padding: 1vw 1vh;
  display: flex;
  flex-direction: column;
}

.card-item p {
  flex: 1;
  font-size: 1vmax;
}

.card-header {
  font-size: 1.2vmax;
  padding: 0.5vmin;
}
.card-action-btn {
  margin: 0 0.2vw;
  padding: 0;
}
.icon-edit {
  vertical-align: super;
}
.icon-delete {
  font-size: 1.5vmax;
}

.card-action-btn:last-child {
  float: right;
}
</style>