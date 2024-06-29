const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
    publicPath: "./",
    assetsDir: "static",
    outputDir: 'dist',
  
    transpileDependencies: true,
    devServer: {
        client: {
          overlay: false,
        },

        port: 8081

      }
})

