<template>
  <div id="login" align="center">
    <el-row :gutter="20">
      <el-col :span="7" :offset="8">
        <div class="grid-content bg-purple">
          <h1 align="center">用户登录界面</h1><br>
        </div>
      </el-col>
      <el-col :span="6" :offset="8">
        <div class="grid-content bg-purple">

          <el-form :model="ruleForm2" status-icon :rules="rules2" ref="ruleForm2" label-width="100px"
                   class="demo-ruleForm">
            <el-form-item label="账号" prop="account">
              <el-input type="text" v-model="ruleForm2.username" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="pass">
              <el-input type="password" v-model="ruleForm2.password" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm('ruleForm2')">提交</el-button>
              <el-button @click="resetForm('ruleForm2')">重置</el-button>
              <el-button type="success" @click="register">注册</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import { mapMutations } from 'vuex';
  export default {
    name: 'login',
    data () {
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'))
        } else {

          callback()
        }
      }
      var validateAccount = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入账户名'))
        } else {
          callback()
        }
      }
      return {
        userToken:"",
        ruleForm2: {
          username: '',
          password: '',
        },
        rules2: {
          username: [
            {validator: validatePass, trigger: 'blur'}
          ],
          password: [
            {validator: validateAccount, trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      ...mapMutations(['changeLogin']),
      submitForm (formName) {
        let _this=this;
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.postRequest('/login',this.ruleForm2).then(res => {  //res是返回结果
              _this.userToken='Bearer '+res.data;
              _this.changeLogin({ Authorization: this.userToken });
              this.$router.push('/home');
              alert('登陆成功');
            }).catch(err => { //请求失败就会捕获报错信息
              console.log('服务器连接失败');
              console.log(err);
            })
          } else {
            console.log('用户信息错误')
            return false
          }
        })
      },
      resetForm (formName) {
        this.$refs[formName].resetFields()
      },
      register () {
        this.$router.push('/register')
      }
    },
  }
</script>

<style scoped>

</style>
