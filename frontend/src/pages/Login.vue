<template>
  <div style="display:flex; justify-content:center; align-items:center; height:100%;">
    <el-card style="width:360px">
      <h2>登录</h2>
      <el-form :model="form" @submit.prevent="onSubmit">
        <el-form-item label="用户名">
          <el-input v-model="form.username" autocomplete="username" />
        </el-form-item>
        <el-form-item label="密码">
          <el-input type="password" v-model="form.password" autocomplete="current-password" />
        </el-form-item>
        <el-button type="primary" style="width:100%" @click="onSubmit">登录</el-button>
      </el-form>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import axios from 'axios';
import { reactive } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const form = reactive({ username: '', password: '' });

async function onSubmit() {
  const { data } = await axios.post('/api/auth/login', form);
  localStorage.setItem('token', data.accessToken);
  router.push('/');
}
</script>