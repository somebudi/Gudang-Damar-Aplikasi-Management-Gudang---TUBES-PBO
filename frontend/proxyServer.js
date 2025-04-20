// proxyServer.js

const express = require('express');
const { createProxyMiddleware } = require('http-proxy-middleware');

const app = express();
const PORT = 3000;  // Server berjalan di port 3000

// Menyajikan file HTML di folder public
app.use(express.static('public'));  // Folder 'public' tempat menyimpan index.html dan file JS

// Mengatur proxy untuk '/users' yang diteruskan ke backend
app.use('/users', createProxyMiddleware({
  target: 'http://localhost:8080',  // Ganti dengan backend Anda yang berjalan di port 8080
  changeOrigin: true,
  pathRewrite: {
    '^/users': '/users',  // Mengarahkan permintaan '/users' ke '/users' di backend
  },
}));

// Menjalankan server di port 3000
app.listen(PORT, () => {
  console.log(`Proxy server berjalan di http://localhost:${PORT}`);
});
