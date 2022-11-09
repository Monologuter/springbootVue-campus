<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
  export default {
    data(){
      return{

      }
    },
    created() {

      let path = window.location.href;
      let index = path.indexOf("#");
      path = path.substring(index + 1)
      // if((navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i))) {
      //   if (path.indexOf('/m/') == -1){
      //     this.$router.push("m" + path)
      //   }
      // }else {
      //   if(path.indexOf('/m/') != -1){
      //     this.$router.push(path.substring(2))
      //   }
      // }
    }
  }
</script>
<style lang="less">
  *{margin: 0;
    padding: 0;box-sizing: border-box}
  html,body {
    font-family: 'Open Sans', Helvetica, Arial, sans-serif;
    background: #f5f7f9;
    height: 100%;
    width: 100%;
    overflow-x: hidden;
  }
  #app{
    width: 100%;
    height: 100%;
  }

  .el-menu{
    border-right: none !important;
  }

  .el-message{
    min-width: 300px !important;
    z-index: 9999 !important;
  }
  .el-notification {
    z-index: 9999 !important;
  }
</style>
