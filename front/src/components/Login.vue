<template>
  <div class="login-container">
    <div class="form-container">
      <h1>登錄</h1>

      <label for="phoneNumber">手機號碼</label>
      <input type="text" id="phoneNumber" v-model="phoneNumber" placeholder="請輸入手機號碼">

      <label for="password">密碼</label>
      <input type="password" id="password" v-model="password" placeholder="請輸入密碼">

      <button @click="login">登錄</button>

      <p v-if="attemptedLogin && !loginSuccess">帳號密碼錯誤或尚未註冊</p>
      <p v-if="loginSuccess">登錄成功</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      phoneNumber: '',
      password: '',
      loginSuccess: false,
      attemptedLogin: false,
    };
  },
  methods: {
    login() {
      this.attemptedLogin = true;
      axios.post('http://localhost:8080/login', {
        phoneNumber: this.phoneNumber,
        password: this.password
      })
          .then(response => {
            console.log('Login successful', response.data);
            this.loginSuccess = true;
          })
          .catch(error => {
            console.error('Login failed', error.response.data);
            this.loginSuccess = false;
          });
    }
  }
}
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.form-container {
  width: 300px;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
}

h1 {
  text-align: center;
}

label {
  display: block;
  margin-bottom: 5px;
}

input {
  width: 100%;
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}

p {
  color: red;
  text-align: center;
}
</style>