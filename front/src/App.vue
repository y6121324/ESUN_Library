<script setup>
import HelloWorld from './components/HelloWorld.vue'
import TheWelcome from './components/TheWelcome.vue'
import Login from './components/Login.vue'
</script>


<template>
  <header>
    <img alt="Vue logo" class="logo" src="./assets/logo.svg" width="125" height="125" />

    <div class="wrapper">
      <Login msg="You did it!" />
    </div>
  </header>

  <main>
<!--    <TheWelcome />-->
  </main>
</template>
<style scoped>
header {
  line-height: 1.5;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    align-items: center; /* 將 place-items 屬性改為 align-items */
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  /* 將 div.wrapper 的 display 屬性改為 flex */
  header .wrapper {
    display: flex;
    align-items: center; /* 新增 align-items 屬性 */
  }
}
</style>
